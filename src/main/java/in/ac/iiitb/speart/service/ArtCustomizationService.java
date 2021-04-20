package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.model.ArtCustomTrial;
import in.ac.iiitb.speart.model.ArtCustomizationDetails;
import in.ac.iiitb.speart.model.ArtistDetails;
import in.ac.iiitb.speart.model.CustomizedRequestStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArtCustomizationService {
    List<ArtCustomTrial> get(Integer artistId) ;

    List<ArtCustomTrial> get(int id) ;

    void saveArtist(MultipartFile file, String category, Integer exp, String name, String type, Float price, String painting_category, String email) ;

    Integer getArtistID(String emailAdd);

    void save(ArtCustomTrial artistDetails);

    void save(Integer buyer_id, Integer artist_customizer, String art_type, String paper_canvas, String art_loc, Integer qty, String art_use, MultipartFile ref_img, String desc) throws IOException;
}
