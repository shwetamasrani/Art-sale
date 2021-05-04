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
    public ArtistDetails saveArtist(ArtistDetails art, PaintingRepoDetails paintingRepoDetails) {
        try{
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        ArtistDetails artistDetails = new ArtistDetails();
//        UserDetails userDetails = new UserDetails();
//        PaintingRepoDetails paintingRepoDetails = new PaintingRepoDetails();
//        ArtistDetailsController artistDetailsController = new ArtistDetailsController();
//        int idArtist = userDetailsDao.getArtistID(email);
//        //Update buyer as an art.
//        userDetails.setUser_id(idArtist);
//        artistDetails.setUserDetails(userDetails);
////        artistDetails.setSample_images(file.getBytes());
//        artistDetails.setExperience(art.getExperience());
//        artistDetails.setCategory_taught(art.getCategory_taught());
//        artistDetails.setSample_image_name(art.getSample_image_name());
//        artistDetails.setType_of_art(art.getType_of_art());
            ArtistDetails retArtist = artistDetailsDao.save(art);
//        Set<UserDetails> hs = new HashSet<>();
//        hs.add(userDetails);
            paintingRepoDetails.setUsers(paintingRepoDetails.getUsers());
//        paintingRepoDetails.setPainting_image(file.getBytes());
            paintingRepoDetails.setPainting_name(art.getSample_image_name());
            paintingRepoDetails.setArtistDetails(art);
            paintingRepoDetails.setPrice(paintingRepoDetails.getPrice());
            paintingRepoDetails.setCategory(paintingRepoDetails.getCategory());
//        paintingRepoDetails.setContentType(file.getContentType());
            String str="2015-03-31";
            Date date=Date.valueOf(str);//converting string into sql date
            paintingRepoDetails.setDate_of_purchase(date);
            paintingRepoDetailsService.save(paintingRepoDetails);
            System.out.println(art);
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

    @Override
    public Object getArtistProfile(Integer artist_user_id) {
        return artistDetailsDao.get(artist_user_id);
    }

    @Transactional
    @Override
    public int getArtistArtistID(String email_address){

        return artistDetailsDao.getArtistID(email_address);
    }

}
