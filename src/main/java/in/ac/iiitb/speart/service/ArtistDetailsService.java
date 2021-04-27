package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.model.ArtistDetails;
import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.model.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArtistDetailsService {

    List<ArtistDetails> get() ;

    public ArtistDetails saveArtist(ArtistDetails art, PaintingRepoDetails paintingRepoDetails);

    Integer getArtistID(String emailAdd);

    ArtistDetails save(ArtistDetails artistDetails);

}
