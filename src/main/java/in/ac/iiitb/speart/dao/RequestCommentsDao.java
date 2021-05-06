package in.ac.iiitb.speart.dao;

import in.ac.iiitb.speart.model.ArtCustomizationComments;
import in.ac.iiitb.speart.model.ArtCustomizationCommentsTrial;

import java.util.List;

public interface RequestCommentsDao {

    List<ArtCustomizationCommentsTrial> getAllCommentsCustomID(Integer custom_id);

    ArtCustomizationCommentsTrial saveCommentsByArtist(ArtCustomizationCommentsTrial commentsObj);

    int getCommentID(Integer artist_id, Integer customization_id);

    void updateStatusApproved(int comment_id);

    void putUpdateReq();

    ArtCustomizationCommentsTrial findCommId(Integer comm_id);
}
