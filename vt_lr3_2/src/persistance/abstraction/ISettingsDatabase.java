package persistance.abstraction;

public interface ISettingsDatabase {
    void setLocale(String locale);
    String getLocale();
}
