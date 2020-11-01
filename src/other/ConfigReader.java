package other;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private String fileName;
    private Properties prop;

    public ConfigReader (String fileName) {
        this.fileName = fileName;
        prop = new Properties();
        readProperties();
    }

    private void readProperties() {
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getDesiredDesks() {
        return Integer.parseInt(prop.getProperty("desks"));
    }
}
