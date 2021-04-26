package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.controller.ArtistDetailsController;
import in.ac.iiitb.speart.dao.ArtistDetailsDao;
import in.ac.iiitb.speart.dao.UserDetailsDao;
import in.ac.iiitb.speart.model.ArtistDetails;
import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ArtistDetailsServiceImpl implements ArtistDetailsService{

    @Autowired
    private ArtistDetailsDao artistDetailsDao;
    @Autowired
    private UserDetailsDao userDetailsDao;

    @Autowired
    PaintingRepoDetailsService paintingRepoDetailsService;
    @Transactional
    @Override
    public List<ArtistDetails> get() {
        return artistDetailsDao.get();
    }

    @Override
    public ArtistDetails saveArtist(MultipartFile file, String category, Integer exp, String name, String type, Float price, String painting_category, String email) {
        try{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ArtistDetails artistDetails = new ArtistDetails();
        UserDetails userDetails = new UserDetails();
        PaintingRepoDetails paintingRepoDetails = new PaintingRepoDetails();
        ArtistDetailsController artistDetailsController = new ArtistDetailsController();
        int idArtist = userDetailsDao.getArtistID(email);
        //Update buyer as an art.
        userDetails.setUser_id(idArtist);
        artistDetails.setUserDetails(userDetails);
//        artistDetails.setSample_images(file.getBytes());
        artistDetails.setExperience(exp);
        artistDetails.setCategory_taught(category);
        artistDetails.setSample_image_name(name);
        artistDetails.setType_of_art(type);
        ArtistDetails retArtist = artistDetailsDao.save(artistDetails);
        Set<UserDetails> hs = new HashSet<>();
        hs.add(userDetails);
        paintingRepoDetails.setUsers(hs);
        paintingRepoDetails.setPainting_image(file.getBytes());
        paintingRepoDetails.setPainting_name(name);
        paintingRepoDetails.setArtistDetails(artistDetails);
        paintingRepoDetails.setPrice(price);
        paintingRepoDetails.setCategory(painting_category);
        paintingRepoDetails.setContentType(file.getContentType());
        String str="2015-03-31";
        Date date=Date.valueOf(str);//converting string into sql date
        paintingRepoDetails.setDate_of_purchase(date);
        paintingRepoDetailsService.save(paintingRepoDetails);
        System.out.println(artistDetails);
        return retArtist;
        }
        catch(Exception e){
            System.out.println("wdaw"+e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getArtistID(String emailAdd) {

        Integer userID = userDetailsDao.getArtistID(emailAdd);
        return userID;
    }

    @Override
    public ArtistDetails save(ArtistDetails artistDetails) {
        return artistDetailsDao.save(artistDetails);
    }


}
