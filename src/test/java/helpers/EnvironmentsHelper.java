package helpers;

public class EnvironmentsHelper {

    //platform config
    public static final String platformWeb = System.getProperty("platform", "web");

       //Web config
    public static final boolean isWeb = platformWeb.equals("web");
}