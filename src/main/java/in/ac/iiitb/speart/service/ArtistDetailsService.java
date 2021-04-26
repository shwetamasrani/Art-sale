package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.model.ArtistDetails;
import in.ac.iiitb.speart.model.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArtistDetailsService {

    List<ArtistDetails> get() ;

    ArtistDetails saveArtist(MultipartFile file, String category, Integer exp, String name, String type, Float price, String painting_category, String email) ;

    Integer getArtistID(String emailAdd);

    ArtistDetails save(ArtistDetails artistDetails);

}
