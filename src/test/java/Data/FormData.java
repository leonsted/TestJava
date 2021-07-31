package Data;

import config.DataConfig;
import config.FormConfig;
import org.aeonbits.owner.ConfigFactory;

public class FormData {
    private static FormConfig getFormData() {
        return ConfigFactory.newInstance().create(FormConfig.class, System.getProperties());
    }

    public static String getFirstName(){
        return getFormData().firstName();

    }

    public static String getLastName(){
        return getFormData().lastName();
    }

    public static String getEmail(){
        return getFormData().email();
    }

    public static String getGender(){
        return getFormData().gender();
    }

    public static String getMobilePhone(){
        return getFormData().mobilePhone();
    }

    public static String getCurrentAddress(){
        return getFormData().currentAddress();
    }

    public static String getState(){
        return getFormData().state();
    }

    public static String getCity(){
        return getFormData().city();
    }
}
