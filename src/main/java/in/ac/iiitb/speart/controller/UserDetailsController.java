package in.ac.iiitb.speart.controller;

import in.ac.iiitb.speart.model.ArtistDetails;
import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.model.UserArtistAPI;
import in.ac.iiitb.speart.model.UserDetails;
import in.ac.iiitb.speart.service.ArtistDetailsService;
import in.ac.iiitb.speart.service.PaintingRepoDetailsService;
import in.ac.iiitb.speart.service.UserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/addUser")
@Api(value="User Repository", description = "Operations pertaining to users.")
public class UserDetailsController {
    private static final Logger logger = LogManager.getLogger(UserDetailsController.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ArtistDetailsService artistDetailsService;

    @Autowired
    private PaintingRepoDetailsService paintingRepoDetailsService;

    @GetMapping("/getUsers")
    @ApiOperation(value = "Views a list of available users.", response = List.class)
    public List<UserDetails> get() {
        return userDetailsService.get();
    }

//    @PostMapping("/userDetails")
    @RequestMapping(value = "/userDetails",method = RequestMethod.POST)
    @ApiOperation(value = "Saves details of a user.", response = UserDetails.class)
    public UserArtistAPI save(@RequestBody UserArtistAPI userArtistAPI) throws IOException {
//        System.out.println("Fname:"+ userDetails1.getFirst_name());
        logger.info("Userdetails are:"+ userArtistAPI);
        //unique check pending
        UserDetails userDetails = new UserDetails(userArtistAPI.getFirst_name(), userArtistAPI.getLast_name(), userArtistAPI.getEmail_address(),
                userArtistAPI.getPassword(), userArtistAPI.getContact_no(), userArtistAPI.getAddress(), userArtistAPI.getUser_category());
        userDetailsService.save(userDetails);
        if(userDetails.getUser_category().equals("artist")) {
            ArtistDetails artistDetails = new ArtistDetails();
            PaintingRepoDetails paintingRepoDetails = new PaintingRepoDetails();
            artistDetails.setUser(userDetails);
            artistDetails.setCategory_taught(userArtistAPI.getCategory_taught());
            artistDetails.setExperience(userArtistAPI.getExperience());
            artistDetails.setType_of_art(userArtistAPI.getType_of_art());
            artistDetails.setSample_image_name(userArtistAPI.getSample_image_name());
            System.out.println(artistDetails.getCategory_taught());
            artistDetailsService.save(artistDetails);
            paintingRepoDetails.setPainting_image(userArtistAPI.getPainting_image());
            paintingRepoDetails.setPainting_name(userArtistAPI.getSample_image_name());
            paintingRepoDetails.setArtistDetails(artistDetails);
            HashSet<UserDetails> hs1 = new HashSet<>();
            hs1.add(userDetails);
            paintingRepoDetails.setUsers(hs1);
            paintingRepoDetails.setPrice(userArtistAPI.getPrice());
            paintingRepoDetails.setCategory(userArtistAPI.getCategory());
            paintingRepoDetails.setPainting_image(userArtistAPI.getPainting_image());
            paintingRepoDetails.setContentType("image/png");
            String str="2015-03-31";
            Date date=Date.valueOf(str);//converting string into sql date
            paintingRepoDetails.setDate_of_purchase(date);
            if(Status.SUCCESS.equals(paintingRepoDetailsService.save(paintingRepoDetails))){
                int artist_user_id = userDetailsService.getUserID(userArtistAPI.getEmail_address());
                int artist_id = artistDetailsService.getArtistArtistID(userArtistAPI.getEmail_address());
                userArtistAPI.setArtist_user_id(artist_user_id);
                userArtistAPI.setArtist_id(artist_id);
                return userArtistAPI;
            }
        }

        return userArtistAPI;
    }

    @RequestMapping(value = "/saveArtistSampleImage", method = RequestMethod.POST)
    public Status saveArtistSampleImage(@RequestParam("file") MultipartFile file, @RequestParam("artist_id") Integer artist_id) throws IOException {
        PaintingRepoDetails paintingRepoDetails = paintingRepoDetailsService.getPaintingByArtistID(artist_id);
        System.out.println(paintingRepoDetails.getPainting_name());
        paintingRepoDetails.setPainting_image(file.getBytes());
        paintingRepoDetailsService.save(paintingRepoDetails);
        return Status.SUCCESS;
    }

    //Retn user details, response entity
    @RequestMapping(value="/login", method = RequestMethod.POST)
    @ApiOperation(value = "Validates user login details.", response = Status.class)
    public ResponseEntity<?> login(@RequestBody UserDetails user){
       String email = user.getEmail_address();
       String pass = user.getPassword();
       System.out.println("email"+ email+" "+pass);
       List<UserDetails> li = userDetailsService.get();
       System.out.println("Size" + li.size());

       for(UserDetails check : li){
           if(check.getEmail_address().equals(email) && check.getPassword().equals(pass)){
               System.out.println("flag before:"+ check.isLog_status());
               check.setLog_status(true);
               UserDetails checkLoggedin = userDetailsService.updateLoginStatus(check);
//               System.out.println("flag after:"+ check.isLog_status());
               logger.info("User found with details:"+ email+" and "+pass);
               return new ResponseEntity<>(checkLoggedin, HttpStatus.OK);
           }
       }
       logger.error("User not found with details:"+ email+" and "+pass);
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value="/logout", method = RequestMethod.POST)
    @ApiOperation(value = "Logs user out.", response = Status.class)
    public ResponseEntity<?> logout(@RequestBody UserDetails user){
        String email = user.getEmail_address();
        String pass = user.getPassword();
        List<UserDetails> li = userDetailsService.get();
        for(UserDetails check : li){
            if(check.getEmail_address().equals(email) && check.getPassword().equals(pass)){
                System.out.println("flag before:"+ check.isLog_status());
                check.setLog_status(Boolean.FALSE);
                System.out.println("flag after:"+ check.isLog_status());
                UserDetails checkLoggedout = userDetailsService.updateLoginStatus(check);
                logger.info("User successfully logged out with details:"+ email+" and "+pass);
                return new ResponseEntity<>(checkLoggedout, HttpStatus.OK);
            }

        }
        logger.error("Couldn't logout the user with details:"+ email+" and "+pass);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }





}
