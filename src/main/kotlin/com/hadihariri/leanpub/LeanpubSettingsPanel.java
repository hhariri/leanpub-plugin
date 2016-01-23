package com.hadihariri.leanpub;

import com.intellij.openapi.util.*;

import javax.swing.*;

public class LeanpubSettingsPanel {

    private JPanel panel1;
    private JTextField bookSlug;
    private JTextField apiKey;
    private LeanpubSettingsProvider leanpubSettingsProvider;
    public JComponent createPanel(LeanpubSettingsProvider leanpubSettingsProvider) {

        this.leanpubSettingsProvider = leanpubSettingsProvider;
        return panel1;
    }

    public boolean isModified() {
        return !Comparing.equal(apiKey.getText(), leanpubSettingsProvider.getApiKey())
                || !(Comparing.equal(bookSlug.getText(), leanpubSettingsProvider.getBookSlug()));
    }

    public void apply() {
        leanpubSettingsProvider.setApiKey(apiKey.getText());
        leanpubSettingsProvider.setBookSlug(bookSlug.getText());
    }

    public void reset() {
        apiKey.setText(leanpubSettingsProvider.getApiKey());
        bookSlug.setText(leanpubSettingsProvider.getBookSlug());
    }


}
