package in.ac.iiitb.speart.dao;


import in.ac.iiitb.speart.model.ArtCustomTrial;
import in.ac.iiitb.speart.model.ArtCustomizationDetails;
import in.ac.iiitb.speart.model.ArtistDetails;

import java.util.List;

public interface ArtCustomizationDao {
    List<ArtCustomTrial> get(Integer artistId);

    List<ArtCustomTrial> get(int id);

    void save(ArtCustomTrial artistDetails);

    void delete(int id);


}
