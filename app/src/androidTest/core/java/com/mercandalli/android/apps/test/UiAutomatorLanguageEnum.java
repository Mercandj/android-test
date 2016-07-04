package com.mercandalli.android.apps.test;

import android.support.annotation.Nullable;

import java.util.Locale;

public enum UiAutomatorLanguageEnum {

    ENGLISH("United States", "en", "Lang"),
    RUSSIAN("Русск", "ru", "Язы"),
    ESPANOL("Español", "es", "Idiomas"),
    PORTUGES("Portugu", "pt", "Idiomas"),
    FRENCH("Français", "fr", "Lang");

    public final String name;
    public final String iso;
    public final String settingName;

    UiAutomatorLanguageEnum(String name, String iso, String settingName) {
        this.name = name;
        this.iso = iso;
        this.settingName = settingName;
    }

    @Nullable
    public static UiAutomatorLanguageEnum getCurrentLanguage() {
        final String language = Locale.getDefault().getLanguage();
        for (UiAutomatorLanguageEnum uiAutomatorLanguage : UiAutomatorLanguageEnum.values()) {
            if (uiAutomatorLanguage.iso.equals(language)) {
                return uiAutomatorLanguage;
            }
        }
        return null;
    }
}
