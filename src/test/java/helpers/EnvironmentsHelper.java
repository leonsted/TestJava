package helpers;

public class EnvironmentsHelper {

    //platform config
    public static final String platformWallet = System.getProperty("platform", "wallet");
    public static final String platformAdmin = System.getProperty("platform", "admin");
    public static final String platformAndroid = System.getProperty("platform", "android");
    public static final String platformIOS = System.getProperty("platform", "iOS");
    public static final String platformWebMobile = System.getProperty("platform", "webMobile");

       //Web config
    public static final boolean isWallet = platformWallet.equals("wallet");
    public static final boolean isAdmin = platformAdmin.equals("admin");
    public static final boolean isWebMobile = platformAdmin.equals("webMobile");

    //mobile config
    public static final boolean isAndroid = platformAndroid.equals("android");
    public static final boolean isIOS = platformIOS.equals("iOS");
}