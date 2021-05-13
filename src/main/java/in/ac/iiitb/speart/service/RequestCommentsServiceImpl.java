package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.dao.RequestCommentsDao;
import in.ac.iiitb.speart.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RequestCommentsServiceImpl implements RequestCommentsService{

    @Autowired
    RequestCommentsDao requestCommentsDao;

    @Override
    public List<ArtCustomizationCommentsTrial> getAllCommentsCustomID(Integer custom_id) {
        return requestCommentsDao.getAllCommentsCustomID(custom_id);
    }

    @Override
    public ArtCustomizationCommentsTrial saveCommentsByAnArtist(String comments, Integer customization_id, Integer artist_id) {
        ArtCustomizationComments artCustomizationComments = new ArtCustomizationComments();
        ArtCustomizationCommentsTrial artCustomizationCommentsTrial = new ArtCustomizationCommentsTrial();
        artCustomizationComments.setComments(comments);
        artCustomizationCommentsTrial.setComments(comments);
        ArtistDetails artist = new ArtistDetails();
        artist.setArtist_id(artist_id);
        artCustomizationComments.setArtistDetails(artist);
        artCustomizationCommentsTrial.setArtistDetails(artist);
        ArtCustomTrial artCustomizationDetails = new ArtCustomTrial();
        artCustomizationDetails.setCustom_id(customization_id);
        artCustomizationCommentsTrial.setArtCustomizationDetails(artCustomizationDetails);
        artCustomizationCommentsTrial.setReqStatus(ReqStatus.PENDING);
        return requestCommentsDao.saveCommentsByArtist(artCustomizationCommentsTrial);
    }

    @Transactional
    @Override
    public Integer updateStatusAsApproved(Integer artist_id, Integer customization_id) {
        ArtCustomizationComments artCustomizationComments = new ArtCustomizationComments();
        int comment_id = requestCommentsDao.getCommentID(artist_id, customization_id);
        requestCommentsDao.updateStatusApproved(comment_id);
        ArtCustomizationCommentsTrial artCustomizationCommentsTrial = requestCommentsDao.findCommId(comment_id);
        return comment_id;
    }

    @Transactional
    @Override
    public void putUpdateReqApproved(Integer comm_id) {
        try{
            if(requestCommentsDao.findCommId(comm_id) == null)
                throw new Exception();
            else{
                ArtCustomizationCommentsTrial update = requestCommentsDao.findCommId(comm_id);
                update.setReqStatus(ReqStatus.APPROVED);
//                requestCommentsDao.saveCommentsByArtist(update);
                requestCommentsDao.updateStatusApproved(comm_id);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
