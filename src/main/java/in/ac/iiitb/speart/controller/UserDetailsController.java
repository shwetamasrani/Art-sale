package in.ac.iiitb.speart.controller;

import in.ac.iiitb.speart.model.ArtistDetails;
import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.model.UserDetails;
import in.ac.iiitb.speart.service.ArtistDetailsService;
import in.ac.iiitb.speart.service.PaintingRepoDetailsService;
import in.ac.iiitb.speart.service.UserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserDetails save(UserDetails userDetails, @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value="category_taught", required = false) String category,
                            @RequestParam(value="eerience", required = false) Integer exp, @RequestParam(value="sample_img_nm", required = false) String name,
                            @RequestParam(value="type_art", required = false) String type,
                            @RequestParam(value="price", required = false) Float price,
                            @RequestParam(value="painting_category", required = false) String painting_category) throws IOException {
        System.out.println("Fname:"+ userDetails.getFirst_name());
        logger.info("Userdetails are:"+ userDetails);
        //unique check pending
        userDetailsService.save(userDetails);
        if(userDetails.getUser_category().equals("artist")) {
            ArtistDetails artistDetails = new ArtistDetails();
            PaintingRepoDetails paintingRepoDetails = new PaintingRepoDetails();
            artistDetails.setUser(userDetails);
            artistDetails.setCategory_taught(category);
            artistDetails.setExperience(exp);
            artistDetails.setType_of_art(type);
            artistDetails.setSample_image_name(name);
//            artistDetails.setSample_images(file.getBytes());
            System.out.println(artistDetails.getCategory_taught());
            artistDetailsService.save(artistDetails);
            paintingRepoDetails.setPainting_image(file.getBytes());
            paintingRepoDetails.setPainting_name(name);
            paintingRepoDetails.setArtistDetails(artistDetails);
            HashSet<UserDetails> hs1 = new HashSet<>();
            hs1.add(userDetails);
            paintingRepoDetails.setUsers(hs1);
            paintingRepoDetails.setPrice(price);
            paintingRepoDetails.setCategory(painting_category);
            paintingRepoDetails.setContentType(file.getContentType());
            String str="2015-03-31";
            Date date=Date.valueOf(str);//converting string into sql date
            paintingRepoDetails.setDate_of_purchase(date);
            paintingRepoDetailsService.save(paintingRepoDetails);
        }

        return userDetails;
    }

    //Retn user details, response entity
    @RequestMapping(value="/login", method = RequestMethod.POST)
    @ApiOperation(value = "Validates user login details.", response = Status.class)
    public Status login(UserDetails user){
       String email = user.getEmail_address();
       String pass = user.getPassword();
       System.out.println("email"+ email+" "+pass);
       List<UserDetails> li = userDetailsService.get();
       System.out.println("Size" + li.size());

       for(UserDetails check : li){
           if(check.getEmail_address().equals(email) && check.getPassword().equals(pass)){
               System.out.println("flag before:"+ check.isLog_status());
               check.setLog_status(true);
               userDetailsService.save(check);
//               System.out.println("flag after:"+ check.isLog_status());
               logger.info("User found with details:"+ email+" and "+pass);
               return Status.SUCCESS;
           }
       }
       logger.error("User not found with details:"+ email+" and "+pass);
       return Status.FAILURE;
    }


    @RequestMapping(value="/logout", method = RequestMethod.POST)
    @ApiOperation(value = "Logs user out.", response = Status.class)
    public Status logout(UserDetails user){
        String email = user.getEmail_address();
        String pass = user.getPassword();
        List<UserDetails> li = userDetailsService.get();
        for(UserDetails check : li){
            if(check.getEmail_address().equals(email) && check.getPassword().equals(pass)){
                System.out.println("flag before:"+ check.isLog_status());
                check.setLog_status(Boolean.FALSE);
                System.out.println("flag after:"+ check.isLog_status());
                userDetailsService.save(check);
                logger.info("User successfully logged out with details:"+ email+" and "+pass);
                return Status.SUCCESS;
            }

        }
        logger.error("Couldn't logout the user with details:"+ email+" and "+pass);
        return Status.FAILURE;
    }





}
