package com.hadihariri.leanpub

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent


class PreviewAction : AnAction() {
    override fun actionPerformed(actionEvent: AnActionEvent) {
        val leanpub = LeanpubSettingsProvider.create(actionEvent.project!!)
        val leanpubApi = LeanpubAPI()
        val response = leanpubApi.requestPreview(leanpub.apikey, leanpub.slug)
        displayBalloon(actionEvent.project!!, response.message, response.isError)
    }
}

