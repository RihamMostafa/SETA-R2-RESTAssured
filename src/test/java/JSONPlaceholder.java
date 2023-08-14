import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JSONPlaceholder {

    RequestSpecification rs= given().baseUri("https://fakerestapi.azurewebsites.net").basePath("api/v1/Activities").port(8080);
    ResponseSpecification resS= expect().statusCode(200).contentType(ContentType.JSON);

    //https://fakerestapi.azurewebsites.net/api/v1/Activities
    @Test
    public void createResourceTest() {
        /*
        fetch('https://jsonplaceholder.typicode.com/posts', {
          method: 'POST',
          body: JSON.stringify({
            title: 'foo',
            body: 'bar',
            userId: 1,
          }),
          headers: {
            'Content-type': 'application/json; charset=UTF-8',
          },
        })
          .then((response) => response.json())
          .then((json) => console.log(json));
        * */

        /*
        given().body("""
                        {
                            "title": "foo",
                            "body": "bar",
                            "userId": 1
                        }
                        """).contentType(ContentType.JSON).log().body().
                when().post("https://jsonplaceholder.typicode.com/posts")
                .then().log().all().statusCode(allOf(greaterThan(199), lessThan(300)))
                .body("userId", equalTo(1));*/

        rs.get("/1")
                .then().spec(resS).body("title",equalTo("Activity 1"));

    }

    @Test
    void getAllActivities()
    {
        rs.get()
                .then().body("title",hasSize(30)).statusCode(200);
    }

    void getZippopotam_12345_main(String countryCode, String zipCode, String expectedPlaceName)
    {
        given().
                pathParam("country",countryCode ).pathParam("zipCode",zipCode ).
                when().log().all().
                get("http://zippopotam.us/us/12345").
                then().
                assertThat().
                body("places[0].'place name'", equalTo(expectedPlaceName));
    }

    @Test
    void getZippopotam_12345_mainTest1()
    {
        // { "us", "90210", "Beverly Hills" },
        String countryCode= "us",
                zipCode="90210",
                expectedPlaceName="Beverly Hills";
        getZippopotam_12345_main(countryCode,zipCode,expectedPlaceName);
    }

    @Test
    void getZippopotam_12345_mainTest2()
    {
        //"us", "12345", "Schenectady" },
        getZippopotam_12345_main("us","12345","Schenectady");
    }

    @Test
    void getZippopotam_12345_mainTest3()
    {
        //{ "ca", "B2R", "Waverley"}
        getZippopotam_12345_main("ca","B2R","Waverley");
    }


}
