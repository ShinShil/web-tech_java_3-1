package persistance.implementation;

import business.configuration.PhysicalDatabaseProvider;
import persistance.abstraction.ISettingsDatabase;

public class Settings implements ISettingsDatabase {
    @Override
    public void setLocale(String locale) {
        PhysicalDatabaseProvider.settingsDatabase.SetLocale(locale);
    }

    @Override
    public String getLocale() {
        return null;
    }
}
