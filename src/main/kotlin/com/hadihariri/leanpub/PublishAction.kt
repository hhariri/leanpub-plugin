package com.hadihariri.leanpub

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * Created by hadihariri on 23/01/16.
 */
class PublishAction: AnAction() {
    override fun actionPerformed(actionEvent: AnActionEvent) {
        val leanpub = LeanpubSettingsProvider.create(actionEvent.project!!)
        val leanpubApi = LeanpubAPI()
        val response = leanpubApi.requestPublish(leanpub.apikey, leanpub.slug)
        displayBalloon(actionEvent.project!!, response.message, response.isError)
    }

}
