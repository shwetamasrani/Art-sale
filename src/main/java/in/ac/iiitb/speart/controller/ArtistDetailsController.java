package in.ac.iiitb.speart.controller;


import in.ac.iiitb.speart.model.ArtistDetails;
import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.model.UserArtistAPI;
import in.ac.iiitb.speart.model.UserDetails;
import in.ac.iiitb.speart.service.ArtistDetailsService;
import in.ac.iiitb.speart.service.UserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/artist")
@Api(value="Artist Repository", description = "Operations pertaining to artists.")
public class ArtistDetailsController {

    private static final Logger logger = LogManager.getLogger(UserDetailsController.class);

    @Autowired
    private ArtistDetailsService artistDetailsService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/getArtists")
    @ApiOperation(value = "Views a list of available artists.", response = List.class)
    public List<ArtistDetails> get() {
        List<ArtistDetails> list = artistDetailsService.get();
        logger.info("List of all artists is:", list);
        return list;
    }

    //How to pass user_id?
    @RequestMapping(value = "/saveArtist", method = RequestMethod.POST)
    @ApiOperation(value = "Saves details of an artist.", response = ArtistDetails.class)
    public ResponseEntity<UserArtistAPI> saveArtist(@RequestBody UserArtistAPI userArtistAPI){
        UserDetails userDetails = new UserDetails();
        PaintingRepoDetails paintingRepoDetails = new PaintingRepoDetails();
        userDetails.setUser_id(userArtistAPI.getArtist_user_id());
        userDetails.setEmail_address(userArtistAPI.getEmail_address());
        paintingRepoDetails.setPrice(userArtistAPI.getPrice());
        paintingRepoDetails.setCategory(userArtistAPI.getCategory());
        Set<UserDetails> set = new HashSet<>();
        set.add(userDetails);
        UserDetails changeUserArtist = userDetailsService.get(userArtistAPI.getArtist_user_id());
        changeUserArtist.setUser_category("artist");
        userDetailsService.updateUserCategoryStatus(changeUserArtist);
        paintingRepoDetails.setUsers(set);
        ArtistDetails art = new ArtistDetails(userDetails,userArtistAPI.getCategory_taught(), userArtistAPI.getExperience(), userArtistAPI.getType_of_art(),
                userArtistAPI.getSample_image_name());
        ArtistDetails artist = artistDetailsService.saveArtist(art, paintingRepoDetails);
        userArtistAPI.setEmail_address(changeUserArtist.getEmail_address());
        return new ResponseEntity<>(userArtistAPI, HttpStatus.OK);
    }


    //To save a painting of an artist, call post method from usercontroller.


    @GetMapping(value = "/getArtistId/{emailAdd}")
    public Integer getArtistID(@PathVariable String emailAdd){
        System.out.println(artistDetailsService.getArtistID(emailAdd));
        return artistDetailsService.getArtistID(emailAdd);
    }

}
