import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Exercise1Test {

    RequestSpecification rS= given().baseUri("https://jsonplaceholder.typicode.com").relaxedHTTPSValidation();

    @Test
    public void getZippopotam()
    {
        given().
                when().get("http://api.zippopotam.us/us/90210").
                then().assertThat().statusCode(200).contentType(ContentType.JSON).log().all()
                .body("places[0].'place name'",equalTo("Beverly Hills"));

    }

    @Test
    public void getJsonPlaceholder()
    {
        rS.
                when().log().all().get("posts/1").
                then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().body().asPrettyString();

    }

    //https://jsonplaceholder.typicode.com/guide/

    @Test
    public void getJsonPlaceholder1()
    {
        rS.
                when().get("/posts/1").
                then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().body().asPrettyString();

    }

    @Test
    public void postJsonPlaceholder2()
    {
        rS.body("""
                        
                        {"title": "foo",
                        "body": "bar",
                        "userId": 1
                        }"""
        ).
                when().post("posts").
                then().assertThat().statusCode(201).contentType(ContentType.JSON).extract().body().asPrettyString();

    }

    @Test
    public void putJsonPlaceholder3()
    {
        rS.body("""
                {
                  "id": 1,
                  "title": "foo",
                  "body": "bar",
                  "userId": 1
                }
                """).
                when().put("posts/1").
                then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().body().asPrettyString();

    }

    @Test
    public void deleteJsonPlaceholder3()
    {
        rS.
                when().delete("/posts/1").
                then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().body().asPrettyString();

    }
}
