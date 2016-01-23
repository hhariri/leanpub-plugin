package com.hadihariri.leanpub

import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project


@State(name = "LeanpubSettingsProvider", storages = arrayOf(
                        Storage(file = StoragePathMacros.PROJECT_FILE),
                        Storage(file = StoragePathMacros.PROJECT_CONFIG_DIR + "/leanpub.xml", scheme = StorageScheme.DIRECTORY_BASED)))
class LeanpubSettingsProvider : PersistentStateComponent<LeanpubSettingsProvider> {
    var apikey = ""
    var slug = ""

    companion object {
        fun create(project: Project) = ServiceManager.getService(project, LeanpubSettingsProvider::class.java)

    }

    override fun getState(): LeanpubSettingsProvider? {
        return this
    }

    override fun loadState(state: LeanpubSettingsProvider?) {
        this.apikey = state?.apikey ?: ""
        this.slug = state?.slug ?: ""
    }
}