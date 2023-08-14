package services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CoverPhotos {

    private RequestSpecification requestSpec;
    private String requestBody_Create = """
            {
              "id": $ID,
              "idBook": $ID_BOOK,
              "url": "$URL"
            }
            """;

    public CoverPhotos() {
        requestSpec= given().baseUri("https://fakerestapi.azurewebsites.net/api/v1")
                .basePath("CoverPhotos")
                .relaxedHTTPSValidation();
    }

    public Response getAllCoverPhotos() {
        return requestSpec.get();
    }

    public Response createCoverPhoto() {
        return
                requestSpec.contentType(ContentType.JSON)
                        .body(requestBody_Create
                                .replace("$ID", "5")
                                .replace("$ID_BOOK", "10")
                                .replace("$URL", "www.any.com"))
                        .post();
    }

    public Response getBookById()
    {
        return requestSpec.pathParam("idBook",5)
                .get("/books/covers/{idBook}");
    }


}
