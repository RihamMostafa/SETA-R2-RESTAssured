package services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.CoverPhotoObject;


import static io.restassured.RestAssured.given;

public class CoverPhotos2 {

    private RequestSpecification rs;
    private CoverPhotoObject obj;

    public CoverPhotos2() {
        rs = given().baseUri("https://fakerestapi.azurewebsites.net")
                .basePath("/api/v1/CoverPhotos").contentType(ContentType.JSON);
        obj= new CoverPhotoObject();
    }

    private String setRequestBody(String id, String idBook, String url) {
        return ("""
                {
                     "id": $ID,
                     "idBook": $IdBook ,
                     "url": "$URL"
                     }
                """ )
                .replace("$ID", id)
                .replace("$IdBook", idBook)
                .replace("$URL", url);
    }

    private CoverPhotoObject setRequestBody(int id, int idBook, String url) {
        obj.setId(id);
        obj.setIdBook(idBook);
        obj.setUrl(url);
        return obj;
    }

    public Response getAllCoverPhotos() {
        return given().spec(rs).get("");
    }

    public Response createCoverPhoto(String id, String idBook, String url) {
        return
                given().spec(rs).body(setRequestBody(id, idBook, url))
                .post();
    }

    public Response createCoverPhoto(int id, int idBook, String url) {
        return
                given().spec(rs).body(setRequestBody(id, idBook, url))
                        .post();
    }

    public Response getCoverPhotoByBookId(String id) {

        return given().spec(rs).pathParam("id",id).get("/books/covers/{id}");
    }

    public Response getCoverPhotoById(String id) {
        return given().spec(rs).pathParam("id",id).get("/{id}");
    }

    public Response updateCoverPhotoById( String id, String idBook, String url) {
        return given().spec(rs).pathParam("id",id).body(setRequestBody(id, idBook, url))
                .put("/{id}");
    }

    public Response deleteCoverPhotoById(String id) {
        return given().spec(rs).pathParam("id",id).delete("/{id}");
    }
}
