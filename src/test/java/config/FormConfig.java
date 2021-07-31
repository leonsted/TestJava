package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:FormData/formdata.properties"
})
public interface FormConfig extends Config {

    @Key("first.name")
    String firstName();

    @Key("last.name")
    String lastName();

    @Key("email")
    String email();

    @Key("gender")
    String gender();

    @Key("mobile.phone")
    String mobilePhone();

    @Key("current.address")
    String currentAddress();

    @Key("state")
    String state();

    @Key("city")
    String city();
}