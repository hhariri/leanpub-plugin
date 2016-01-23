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
        return !Comparing.equal(apiKey.getText(), leanpubSettingsProvider.getApikey())
                || !(Comparing.equal(bookSlug.getText(), leanpubSettingsProvider.getSlug()));
    }

    public void apply() {
        leanpubSettingsProvider.setApikey(apiKey.getText());
        leanpubSettingsProvider.setSlug(bookSlug.getText());
    }

    public void reset() {
        apiKey.setText(leanpubSettingsProvider.getApikey());
        bookSlug.setText(leanpubSettingsProvider.getSlug());
    }


}
