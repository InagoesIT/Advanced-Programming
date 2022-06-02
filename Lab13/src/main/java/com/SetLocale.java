package com;

import app.CommandsLocale;

public class SetLocale {

    public SetLocale(String languageTag) {
        CommandsLocale.setLocale(languageTag);
    }
}

