package com.hadihariri.leanpub

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataConstants
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.MessageType
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.wm.WindowManager
import com.intellij.ui.awt.RelativePoint
import org.apache.http.NameValuePair
import org.apache.http.StatusLine
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair


public class PreviewAction : AnAction() {
    override fun actionPerformed(actionEvent: AnActionEvent) {
        val project = actionEvent.getDataContext().getData(DataConstants.PROJECT) as Project
        val settings = LeanpubSettingsProvider.create(project)
        val apiKey = settings.apiKey
        val slug = settings.bookSlug
        if (apiKey != null && slug != null) {
            val response = requestPreview(apiKey, slug)

            var message = "Preview Generation request successful"
            var messageType = MessageType.INFO
            if (response.getStatusCode() != 200) {
                message = response.getReasonPhrase().toString()
                messageType = MessageType.ERROR
            }
            val statusBar = WindowManager.getInstance()
                    ?.getStatusBar(project)
            JBPopupFactory.getInstance()
                    ?.createHtmlTextBalloonBuilder(message, messageType, null)
                    ?.setFadeoutTime(6000)
                    ?.createBalloon()
                    ?.show(RelativePoint.getCenterOf(statusBar?.getComponent()!!), Balloon.Position.above)
        } else {
            Messages.showMessageDialog("Please define your API key and URL slug under Tools | Leanpub before generating a preview", "Leanpub", null)
        }
    }

    private fun requestPreview(apiKey: String, slug: String): StatusLine {
        val httpClient = HttpClients.createDefault()!!
        val httpPost = HttpPost("https://leanpub.com$slug/preview.json")
        val previewData = arrayListOf<NameValuePair>()
        previewData.add(BasicNameValuePair("api_key", apiKey))
        httpPost.setEntity(UrlEncodedFormEntity(previewData))
        val response = httpClient.execute(httpPost)
        return response?.getStatusLine()!!
    }
}
