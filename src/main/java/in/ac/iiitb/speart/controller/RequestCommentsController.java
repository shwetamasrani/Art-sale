package in.ac.iiitb.speart.controller;

import in.ac.iiitb.speart.dao.RequestCommentsDao;
import in.ac.iiitb.speart.model.*;
import in.ac.iiitb.speart.service.ArtCustomizationService;
import in.ac.iiitb.speart.service.ArtistDetailsService;
import in.ac.iiitb.speart.service.RequestCommentsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/artistComments")
public class RequestCommentsController {

    private static final Logger logger = LogManager.getLogger(RequestCommentsController.class);


    @Autowired
    RequestCommentsService requestCommentsService;

    @Autowired
    RequestCommentsDao requestCommentsDao;

    @Autowired
    ArtCustomizationService artCustomizationService;

    @Autowired
    ArtistDetailsService artistDetailsService;

    public RequestCommentsController(RequestCommentsService requestCommentsService) {
        this.requestCommentsService = requestCommentsService;
    }

    @RequestMapping("/getCommentsByCustomID/{custom_id}")
    public List<ArtCustomizationCommentsTrial> getAllCommentsByCustomID(@PathVariable Integer custom_id){
        logger.info("Custom_id: "+ custom_id);
        List<ArtCustomizationCommentsTrial> list = requestCommentsService.getAllCommentsCustomID(custom_id);
        logger.info("Custom comments list is: "+ list);
        return list;
    }

    //artist_custom should get changed after approved.
    @RequestMapping(value = "/saveCommentsByArtist", method = RequestMethod.POST)
    public ResponseEntity<?> saveCommentsByArtist(@RequestBody RequestCommentsAPI requestCommentsAPI){

        ArtCustomizationCommentsTrial artCustomizationCommentsTrial = requestCommentsService.saveCommentsByAnArtist(
                requestCommentsAPI.getComments(), requestCommentsAPI.getStatus_custom_id(), requestCommentsAPI.getStatus_artist_id());
        logger.info("Custom updated comments are:"+ artCustomizationCommentsTrial);
        return new ResponseEntity<ArtCustomizationCommentsTrial>(artCustomizationCommentsTrial, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/approveCustRequestStatusTrial", method = RequestMethod.POST)
    public ResponseEntity<?> approveRequestByUser(@RequestBody RequestCommentsAPI requestCommentsAPI){
        System.out.println("edwwf");
        int comment_id = requestCommentsService.updateStatusAsApproved(requestCommentsAPI.getStatus_artist_id(), requestCommentsAPI.getStatus_custom_id());
        ArtCustomizationCommentsTrial artCustomizationCommentsTrial1 = requestCommentsDao.findCommId(comment_id);
        RequestCommentsAPI requestCommentsAPI1 = new RequestCommentsAPI();
        requestCommentsAPI1.setComments(artCustomizationCommentsTrial1.getComments());
        requestCommentsAPI1.setStatus_artist_id(artCustomizationCommentsTrial1.getArtistDetails().getArtist_id());
        requestCommentsAPI1.setStatus_custom_id(artCustomizationCommentsTrial1.getArtCustomizationDetails().getUserDetails().getUser_id());
        requestCommentsAPI1.setComments_id(artCustomizationCommentsTrial1.getComments_id());
        System.out.println(requestCommentsAPI.getComments());
        ArtCustomTrial artCustomTrial = artCustomizationService.getCustReq(requestCommentsAPI.getStatus_custom_id());
        ArtistDetails approvedArtist = artistDetailsService.getApprovedArtistProfile(requestCommentsAPI.getStatus_artist_id());
        artCustomTrial.setArtistDetails(approvedArtist);
        artCustomTrial.setArtistDetails3(approvedArtist);
        artCustomTrial = artCustomizationService.saveApprovedArtist(artCustomTrial);
        return new ResponseEntity<RequestCommentsAPI>(requestCommentsAPI1, HttpStatus.ACCEPTED);
    }

    //Check why it is doing for all records. Checked
    @RequestMapping(value = "/approveCustRequestStatus/{comm_id}", method = RequestMethod.PUT)
    public void approveRequestByUserTrial(@PathVariable Integer comm_id){
        requestCommentsService.putUpdateReqApproved(comm_id);
    }

//    @RequestMapping(value = "/getCommentIdWithBuyerArtist", method = RequestMethod.GET)
//    public ResponseEntity<Integer> getCommIDWithBuyerArtistID(){
//        int commentID = requestCommentsService.getCommentID(buyer);
//        return new ResponseEntity<Integer>(commentID, HttpStatus.ACCEPTED);
//    }
}
