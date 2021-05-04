package in.ac.iiitb.speart.controller;

import in.ac.iiitb.speart.model.*;
import in.ac.iiitb.speart.service.ArtCustomizationService;
import in.ac.iiitb.speart.service.ArtistDetailsService;
import in.ac.iiitb.speart.service.ReqStatusService;
import in.ac.iiitb.speart.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    ArtistDetailsService artistDetailsService;
    //User Profile: Use get(id) of UserController

    //Register as an artist: Use saveArtist(...) of ArtistDetailsController

    //Currentbids

    //Purchases

    //Customized Art
    //Check req table save method.
    @RequestMapping(value = "/getArtCustomized", method = RequestMethod.POST)
    public ResponseEntity<?> getCustomizationDetails(@RequestBody ArtCustomizationAPI artCustomizationAPI) throws IOException {

        ArtCustomTrial saved = artCustomizationService.save(artCustomizationAPI.getBuyer_id(), artCustomizationAPI.getArtist_customizer(), artCustomizationAPI.getType_of_art(),
                artCustomizationAPI.getPaper_canvas(), artCustomizationAPI.getArt_location(), artCustomizationAPI.getQuantity(), artCustomizationAPI.getArt_use(),
                artCustomizationAPI.getDescription());
//        artCustomizationService.save(buyer_id, artist_customizer, art_type, paper_canvas, art_loc, qty, art_use, ref_img, desc);
        return new ResponseEntity<ArtCustomTrial>(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/getArtCustmizedSampleImage", method = RequestMethod.POST)
    public ResponseEntity<?> getCustomizedSampleImage(@RequestParam("file") MultipartFile file, @RequestParam("custom_id") Integer custom_id,
                                                      @RequestParam("buyer_id") Integer buyer_id) throws IOException{
        System.out.println("Inside");
        ArtCustomTrial customTrial = artCustomizationService.getCustReq(custom_id);
        customTrial.setRef_art_image(file.getBytes());
        ArtCustomTrial artCustomTrial = artCustomizationService.saveImage(customTrial);
        return new ResponseEntity<ArtCustomTrial>(artCustomTrial, HttpStatus.OK);
//        return custom_id;
    }

    //Remove this
    @RequestMapping(value = "/getArtCustomizedtrial", method = RequestMethod.POST)
    public ArtCustomTrial getCustomizationDetails(ArtCustomTrial art){
        artCustomizationService.save(art);
        return art;
    }

    @RequestMapping(value = "/getAllCustomizedOrdersUser/{buyerId}", method = RequestMethod.GET)
    public List<ArtCustomTrial> getAllUserCustOrders(@PathVariable Integer buyerId){
        return artCustomizationService.get(buyerId);
    }

    //Check query
    @RequestMapping(value = "/getAllCustOrdersArtist/{artistId}", method = RequestMethod.GET)
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


    @RequestMapping(value = "/getMyProfile/{email_add}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserProfile(@PathVariable String email_add){
        UserDetails userprofile = userDetailsService.getProfileUseEmail(email_add);
        UserArtistPaintingAPI userArtistPaintingAPI = new UserArtistPaintingAPI();
        if(userprofile.getUser_category().equals("artist")){
            Object artist = artistDetailsService.getArtistProfile(userprofile.getUser_id());
            userArtistPaintingAPI.setObject(artist);
        }
        userArtistPaintingAPI.setUserDetails(userprofile);
        return new ResponseEntity<UserArtistPaintingAPI>(userArtistPaintingAPI, HttpStatus.ACCEPTED);
    }



}
