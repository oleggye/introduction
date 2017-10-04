package by.epam.dao.util;

import java.util.ResourceBundle;

public class PropertyLoader {

    private static final String DEFAULT_PROPERTY_FILE_PATH = "reader";

    private static final PropertyLoader INSTANCE = new PropertyLoader();
    private final ResourceBundle bundle;

    private PropertyLoader() {
        bundle = ResourceBundle.getBundle(DEFAULT_PROPERTY_FILE_PATH);
    }

    public static PropertyLoader getInstance() {
        return INSTANCE;
    }

    public String getString(String key) {
        return bundle.getString(key);
    }
}
