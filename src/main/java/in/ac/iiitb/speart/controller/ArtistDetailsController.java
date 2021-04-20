package in.ac.iiitb.speart.controller;


import in.ac.iiitb.speart.model.ArtistDetails;
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

import java.util.List;

@RestController
@RequestMapping("/artist")
@Api(value="Artist Repository", description = "Operations pertaining to artists.")
public class ArtistDetailsController {

    private static final Logger logger = LogManager.getLogger(ArtistDetailsController.class);

    @Autowired
    private ArtistDetailsService artistDetailsService;

    @GetMapping("/getArtists")
    @ApiOperation(value = "Views a list of available artists.", response = List.class)
    public List<ArtistDetails> get() {
        List<ArtistDetails> list = artistDetailsService.get();
        System.out.println(list);
        logger.info("List of all artists is:"+list);
        return list;
    }


    //How to pass user_id?
    @RequestMapping(value = "/saveArtist", method = RequestMethod.POST)
    @ApiOperation(value = "Saves details of an artist.", response = ArtistDetails.class)
    public ResponseEntity<ArtistDetails> saveArtist(@RequestParam("file") MultipartFile file, @RequestParam("category_taught") String category,
                             @RequestParam("eerience") Integer exp, @RequestParam(value = "sample_img_nm", required = false) String name,
                             @RequestParam("type_art") String type,
                             @RequestParam(value="price", required = false) Float price,
                             @RequestParam(value="painting_category", required = false) String painting_category,
                              @RequestParam(value="email", required = false) String email
    ){
        UserDetails userDetails = new UserDetails();
        ArtistDetails art = artistDetailsService.saveArtist(file, category, exp, name, type, price, painting_category, email);

        return new ResponseEntity<>(art, HttpStatus.OK);
    }


    @GetMapping(value = "/getArtistId/{emailAdd}")
    public Integer getArtistID(@PathVariable String emailAdd){
        System.out.println(artistDetailsService.getArtistID(emailAdd));
        return artistDetailsService.getArtistID(emailAdd);
    }

}
