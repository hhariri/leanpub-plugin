package com.hadihariri.leanpub

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.MessageType
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.wm.WindowManager
import com.intellij.ui.awt.RelativePoint

/**
 * Created by hadihariri on 23/01/16.
 */
fun displayBalloon(project: Project, message: String, isError: Boolean) {
    val statusBar = WindowManager.getInstance()
            ?.getStatusBar(project)
    JBPopupFactory.getInstance()
            ?.createHtmlTextBalloonBuilder(message, if (isError) MessageType.ERROR else MessageType.INFO, null)
            ?.setFadeoutTime(6000)
            ?.createBalloon()
            ?.show(RelativePoint.getCenterOf(statusBar?.component!!), Balloon.Position.above)
}