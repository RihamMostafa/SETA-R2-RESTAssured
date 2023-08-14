import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class OptimizationExercise {

    //TAU Examples

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = given().baseUri("http://api.zippopotam.us");
    }

    @Test
    public void requestUsZipCode9021() {

        given().
                spec(requestSpec).
                when().
                get("us/90210").
                then().
                assertThat().
                statusCode(200);
    }

    @BeforeClass
    public static void createResponseSpecification() {

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void requestUsZipCode90210(){

        given().
                spec(requestSpec).
                when().
                get("http://zippopotam.us/us/90210").
                then().
                spec(responseSpec).
                and().
                assertThat().time(Matchers.lessThan(2000L)).
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode90210_extractPlaceNameFromResponseBody() {

        String placeName =
                given().
                        spec(requestSpec).
                        when().
                        get("http://zippopotam.us/us/90210")
                        .getBody()
                        .jsonPath().get("places[0].'place name'");

//                        .then().
//                        extract().
//                        path("places[0].'Beverly Hills'");

       Assert.assertEquals(placeName, "Beverly Hills");
    }

}
