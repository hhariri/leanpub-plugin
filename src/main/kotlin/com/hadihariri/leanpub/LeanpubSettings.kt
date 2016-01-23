package com.hadihariri.leanpub

import com.intellij.openapi.options.SearchableConfigurable
import javax.swing.JComponent
import com.intellij.psi.PsiManager
import com.intellij.openapi.project.Project

class LeanpubSettings(val project: Project): SearchableConfigurable {
    val panel = LeanpubSettingsPanel()

    override fun getDisplayName(): String? {
        return "Leanpub"
    }
    override fun getHelpTopic(): String? {
        return "Leanpub"
    }
    override fun getId(): String {
        return "Leanpub"
    }
    override fun enableSearch(p0: String?): Runnable? {
        return null
    }
    override fun createComponent(): JComponent? {
        val leanPubSettingsProvider = LeanpubSettingsProvider.create(project)
        return panel.createPanel(leanPubSettingsProvider)
    }
    override fun isModified(): Boolean {
        return panel.isModified
    }
    override fun apply() {
        panel.apply()

    }
    override fun reset() {
        panel.reset()
    }

    override fun disposeUIResources() {
    }

}