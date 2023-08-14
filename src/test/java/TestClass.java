import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestClass {
    @Test
    public void test() {
        given().
                when().get("http://api.zippopotam.us/AD/AD100").
                then().statusCode(201)
                .contentType("Application/Json");
    }

}
