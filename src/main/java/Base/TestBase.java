package Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    protected Properties prop;

    public TestBase() throws IOException {
        //Read data from property file
        prop = new Properties();
        FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Configuration/config.properties");
        prop.load(ip);
    }
}
