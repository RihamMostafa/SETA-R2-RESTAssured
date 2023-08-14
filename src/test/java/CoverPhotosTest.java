import objects.CoverPhotoObject;
import org.testng.annotations.Test;
import services.CoverPhotos;


public class CoverPhotosTest {

    CoverPhotos cph= new CoverPhotos();

    @Test
    void getCoverPhotosTest()
    {
        CoverPhotoObject[] obj = cph.getAllCoverPhotos().as(CoverPhotoObject[].class);
    }

}
