package com.hadihariri.leanpub

import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project


@State(name = "LeanpubSettingsProvider", storages = arrayOf(
                        Storage(file = StoragePathMacros.PROJECT_FILE),
                        Storage(file = StoragePathMacros.PROJECT_CONFIG_DIR + "/leanpub.xml", scheme = StorageScheme.DIRECTORY_BASED)))
public class LeanpubSettingsProvider : PersistentStateComponent<LeanpubSettingsProvider> {
    var apiKey: String? = null
    var bookSlug: String? = null

    companion object {
        fun create(project: Project) = ServiceManager.getService(project, javaClass<LeanpubSettingsProvider>())

    }

    override fun getState(): LeanpubSettingsProvider? {
        return this
    }

    override fun loadState(state: LeanpubSettingsProvider?) {
        this.apiKey = state?.apiKey
        this.bookSlug = state?.bookSlug
    }
}