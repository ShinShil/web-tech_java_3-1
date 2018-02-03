package resources.locale.strings;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class LocalMap {
    static String russian = "ru";
    static String english = "en";

    private static Map<String, ILocale> localesMap = new HashMap<String, ILocale>();

    static {
        localesMap.put(russian, new ru());
        localesMap.put(english, new en());
    }

    public static Map<String, ILocale> getMap() {
        return localesMap;
    }
}
