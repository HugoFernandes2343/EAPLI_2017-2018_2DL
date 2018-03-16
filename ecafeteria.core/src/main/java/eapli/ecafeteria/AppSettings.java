package eapli.ecafeteria;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * the application settings.
 *
 * @author Paulo Gandra Sousa
 */
public class AppSettings {

    private final static String PROPERTIES_RESOURCE = "ecafeteria.properties";
    private final static String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
    private final static String UI_MENU_LAYOUT_KEY = "ui.menu.layout";
    private final static String PERSISTENCE_UNIT_KEY = "persistence.persistenceUnit";
    private final static String SCHEMA_GENERATION_KEY = "javax.persistence.schema-generation.database.action";
    private final Properties applicationProperties = new Properties();

    public AppSettings() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream propertiesStream = this.getClass().getClassLoader()
                .getResourceAsStream(PROPERTIES_RESOURCE)) {
            if (propertiesStream != null) {
                this.applicationProperties.load(propertiesStream);
            } else {
                throw new FileNotFoundException(
                        "property file '" + PROPERTIES_RESOURCE + "' not found in the classpath");
            }
        } catch (final IOException exio) {
            setDefaultProperties();

            Logger.getLogger(AppSettings.class.getName()).log(Level.SEVERE, null, exio);
        }
    }

    private void setDefaultProperties() {
        this.applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
                "eapli.ecafeteria.persistence.jpa.JpaRepositoryFactory");
        this.applicationProperties.setProperty(UI_MENU_LAYOUT_KEY, "horizontal");
        this.applicationProperties.setProperty(PERSISTENCE_UNIT_KEY, "eapli.eCafeteriaPU");
    }

    public Boolean isMenuLayoutHorizontal() {
        return "horizontal"
                .equalsIgnoreCase(this.applicationProperties.getProperty(UI_MENU_LAYOUT_KEY));
    }

    public String getPersistenceUnitName() {
        return this.applicationProperties.getProperty(PERSISTENCE_UNIT_KEY);
    }

    public String getRepositoryFactory() {
        return this.applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
    }

    @SuppressWarnings("rawtypes")
    public Map getExtendedPersistenceProperties() {
        final Map ret = new HashMap();
        ret.put(SCHEMA_GENERATION_KEY,
                this.applicationProperties.getProperty(SCHEMA_GENERATION_KEY));
        return ret;
    }

    public String getProperty(String prop) {
        return this.applicationProperties.getProperty(prop);
    }
}
