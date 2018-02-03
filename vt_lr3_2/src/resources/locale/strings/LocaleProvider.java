package resources.locale.strings;

import business.configuration.PhysicalDatabaseProvider;
import database.constants.ISettingsConstants;

public class LocaleProvider {
    public static  ILocale locale;
    static {
        locale = setLocale();
    }

    public static ILocale setLocale() {
        String locale = PhysicalDatabaseProvider.settingsDatabase.GetLocale();
        return  LocalMap.getMap().get(locale);
    }
}
