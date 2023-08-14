import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import services.Demo;

import static org.hamcrest.Matchers.*;

public class DemoTest {

    //https://reqres.in/api/login
    String accessToken;

    @BeforeClass
    public void loginTest()
    {
        accessToken= Demo.login("eve.holt@reqres.in","cityslicka").getBody().jsonPath().get("token");
    }

    @Test
    public void updateTest()
    {
        System.out.println(accessToken);
        Demo.update("Ahmed","SW Eng", accessToken).then().statusCode(allOf(greaterThan(199),lessThan(300)));
    }
}
