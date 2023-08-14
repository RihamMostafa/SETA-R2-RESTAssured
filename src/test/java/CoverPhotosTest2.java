import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import objects.CoverPhotoObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.CoverPhotos2;

import static io.restassured.RestAssured.expect;


public class CoverPhotosTest2 {


    CoverPhotos2 cphServiceObj= new CoverPhotos2();
    ResponseSpecification res = expect().statusCode(200).contentType(ContentType.JSON);


    @Test
    public void getAllCoverPhotoTest() {

        CoverPhotoObject[] array_CoverPhoto= cphServiceObj.getAllCoverPhotos().as(CoverPhotoObject[].class);

//        cphServiceObj.getAllCoverPhotos()
//                .then().spec(res)
//                .log().body();
    }

    @Test
    public void postCoverPhoto() {

        cphServiceObj.createCoverPhoto("1","15","www.any.com")
                .then().spec(res);
    }

    @Test
    public void postCoverPhoto2() {

        CoverPhotoObject obj= cphServiceObj.createCoverPhoto(5,17,"www.any2.com")
                .as(CoverPhotoObject.class);
    }

    @Test
    public void getCoverPhotoByBookId() {
        cphServiceObj.getCoverPhotoByBookId("15")
                .then().spec(res);
    }

    @Test
    public void getCoverPhotoById() {

        Response response= cphServiceObj.getCoverPhotoById("1");
        response.then().spec(res);
        CoverPhotoObject obj= response.as(CoverPhotoObject.class);
        Assert.assertEquals(obj.getId(),1);

    }

    @Test
    public void putCoverPhotoById() {
        cphServiceObj.updateCoverPhotoById("1","67","www.url.com")
                .then().spec(res);
    }

    @Test
    public void deleteCoverPhoto() {
        cphServiceObj.deleteCoverPhotoById("2")
                .then().statusCode(200);
    }
}
