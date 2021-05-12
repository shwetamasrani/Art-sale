package in.ac.iiitb.speart.dao;


import in.ac.iiitb.speart.model.ArtCustomTrial;
import in.ac.iiitb.speart.model.ArtCustomizationDetails;
import in.ac.iiitb.speart.model.ArtistDetails;

import java.util.List;

public interface ArtCustomizationDao {
    List<ArtCustomTrial> get(Integer artistId);

    List<ArtCustomTrial> get(int id);

    ArtCustomTrial save(ArtCustomTrial artistDetails);

    void delete(int id);


    ArtCustomTrial saveCustomizedImageByCustomID(ArtCustomTrial customTrial);

    ArtCustomTrial getCustReq(Integer custom_id);

    ArtCustomTrial saveApprovedArtist(ArtCustomTrial artCustomTrial);
}
