package in.ac.iiitb.speart.controller;

import in.ac.iiitb.speart.model.ArtCustomizationComments;
import in.ac.iiitb.speart.model.ArtCustomizationCommentsTrial;
import in.ac.iiitb.speart.service.RequestCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/artistComments")
public class RequestCommentsController {

    @Autowired
    RequestCommentsService requestCommentsService;

    @RequestMapping("/getCommentsByCustomID/{custom_id}")
    public List<ArtCustomizationCommentsTrial> getAllCommentsByCustomID(@PathVariable Integer custom_id){
        return requestCommentsService.getAllCommentsCustomID(custom_id);
    }

    @RequestMapping(value = "/saveCommentsByArtist", method = RequestMethod.POST)
    public void saveCommentsByArtist(@RequestParam(value = "Comments") String comments,
    @RequestParam(value = "customization_id") Integer customization_id,
                                     @RequestParam(value = "artist_id") Integer artist_id){
        requestCommentsService.saveCommentsByAnArtist(comments, customization_id, artist_id);

    }

    @RequestMapping(value = "/approveCustRequestStatus", method = RequestMethod.PUT)
    public void approveRequestByUser(@RequestParam(value = "artist_id") Integer artist_id,
    @RequestParam(value = "customization_id") Integer customization_id){
        requestCommentsService.updateStatusAsApproved(artist_id, customization_id);
    }

    //Check why it is doing for all records. Checked
    @RequestMapping(value = "/approveCustRequestStatusTrial/{comm_id}", method = RequestMethod.PUT)
    public void approveRequestByUserTrial(
    @PathVariable Integer comm_id){
        requestCommentsService.putUpdateReqApproved(comm_id);
    }

//    @RequestMapping(value = "/getCommentIdWithBuyerArtist", method = RequestMethod.GET)
//    public ResponseEntity<Integer> getCommIDWithBuyerArtistID(){
//        int commentID = requestCommentsService.getCommentID(buyer);
//        return new ResponseEntity<Integer>(commentID, HttpStatus.ACCEPTED);
//    }
}
