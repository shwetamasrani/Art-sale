package in.ac.iiitb.speart.controller;

import in.ac.iiitb.speart.model.*;
import in.ac.iiitb.speart.service.ArtCustomizationService;
import in.ac.iiitb.speart.service.ReqStatusService;
import in.ac.iiitb.speart.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/dashNavigationMenu")
public class DashboardNavigationMenu {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    ArtCustomizationService artCustomizationService;

    @Autowired
    ReqStatusService reqStatusService;
    //User Profile: Use get(id) of UserController

    //Register as an artist: Use saveArtist(...) of ArtistDetailsController

    //Currentbids

    //Purchases

    //Customized Art
    //Check req table save method.
    @RequestMapping(value = "/getArtCustomized", method = RequestMethod.POST)
    public ArtCustomTrial getCustomizationDetails(@RequestParam(value = "buyer_id") Integer buyer_id,
       @RequestParam(value = "artist_customizer") Integer artist_customizer, @RequestParam(value = "art_type" ,required = false) String art_type,
       @RequestParam(value = "paper_canvas", required = false) String paper_canvas, @RequestParam(value = "art_loc", required = false) String art_loc,
       @RequestParam(value = "qty", required = false) Integer qty, @RequestParam(value = "art_use", required = false) String art_use, @RequestParam(value = "ref_img", required = false) MultipartFile ref_img,
       @RequestParam(value = "desc", required = false) String desc) throws IOException {

        artCustomizationService.save(buyer_id, artist_customizer, art_type, paper_canvas, art_loc, qty, art_use, ref_img, desc);
        return null;
    }

    //Remove this
    @RequestMapping(value = "/getArtCustomizedtrial", method = RequestMethod.POST)
    public ArtCustomTrial getCustomizationDetails(ArtCustomTrial art){
        artCustomizationService.save(art);
        return art;
    }

    @RequestMapping(value = "/getAllCustomizedOrdersUser/{buyerId}")
    public List<ArtCustomTrial> getAllUserCustOrders(@PathVariable Integer buyerId){
        return artCustomizationService.get(buyerId);
    }

    //Check query
    @RequestMapping(value = "/getAllCustOrdersArtist/{artistId}")
    public List<ArtCustomTrial> getAllCustOrdersArtist(@PathVariable Integer artistId){
        return artCustomizationService.get(artistId);
    }

    //Check this --> Not needed
    @RequestMapping(value = "/updateReqStatus/{custom_id}", method = RequestMethod.PUT)
    public List<CustomizedRequestStatusTrial> updateReqStatus(@PathVariable Integer custom_id){
        List<CustomizedRequestStatusTrial> list = reqStatusService.getAll(custom_id);
        reqStatusService.updateReqStatus(list);
        return null;
    }





}
