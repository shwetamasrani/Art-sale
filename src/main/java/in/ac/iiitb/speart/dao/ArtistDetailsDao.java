package in.ac.iiitb.speart.dao;

import in.ac.iiitb.speart.model.ArtistDetails;
import java.util.List;

public interface ArtistDetailsDao {
    List<ArtistDetails> get();

    Object get(int id);

    ArtistDetails save(ArtistDetails artistDetails);

    void delete(int id);

}
