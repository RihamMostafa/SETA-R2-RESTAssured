package services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import objects.UserObject;

import static io.restassured.RestAssured.given;

public class Demo {

    public static Response login(String username, String password)
    {
        return given().contentType(ContentType.JSON)
                .body(new UserObject().setLoginData(username,password)).post("https://reqres.in/api/login");
    }

    public static Response update(String name, String job, String accessToken)
    {
        return given().header("Authorization","Bearer "+accessToken)
                .body(new UserObject().setUpdateData(name,job)).put("https://reqres.in/api/users/2");
    }

}
