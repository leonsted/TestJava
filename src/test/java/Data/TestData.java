package Data;

import config.DataConfig;
import org.aeonbits.owner.ConfigFactory;

public class TestData {
    private static DataConfig getTestData() {
        return ConfigFactory.newInstance().create(DataConfig.class, System.getProperties());
    }

    public static String getWebUrl() {
        return getTestData().webUrl();
    }

    public static String getApiUrl() {
        return getTestData().apiUrl();
    }

}
