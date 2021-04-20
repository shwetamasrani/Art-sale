package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.model.ArtCustomizationComments;
import in.ac.iiitb.speart.model.ArtCustomizationCommentsTrial;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface RequestCommentsService {


    List<ArtCustomizationCommentsTrial> getAllCommentsCustomID(Integer custom_id);

    void saveCommentsByAnArtist(String comments, Integer customization_id, Integer artist_id);

    void updateStatusAsApproved(Integer artist_id, Integer customization_id);

    void putUpdateReqApproved(Integer comm_id);
}
