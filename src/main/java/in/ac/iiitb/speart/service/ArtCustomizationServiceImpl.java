package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.dao.ArtCustomizationDao;
import in.ac.iiitb.speart.dao.ReqStatusDao;
import in.ac.iiitb.speart.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArtCustomizationServiceImpl implements ArtCustomizationService{

    @Autowired
    ArtCustomizationDao artCustomizationDao;

    @Autowired
    ReqStatusDao reqStatusDao;

    @Override
    public List<ArtCustomTrial> get(Integer artistId) {
        return artCustomizationDao.get(artistId);
    }

    @Override
    public List<ArtCustomTrial> get(int id) {
        return artCustomizationDao.get(id);
    }

    @Override
    public void saveArtist(MultipartFile file, String category, Integer exp, String name, String type, Float price, String painting_category, String email) {

    }

    @Override
    public Integer getArtistID(String emailAdd) {
        return null;
    }

    @Override
    public void save(ArtCustomTrial artistDetails) {
        CustomizedRequestStatus cust = new CustomizedRequestStatus();
        CustomizedRequestStatusTrial cust_trial = new CustomizedRequestStatusTrial();
        artCustomizationDao.save(artistDetails);
        cust_trial.setArtCustomizationDetails(artistDetails);
        cust_trial.setArtCustomizationDetails1(artistDetails);
        cust_trial.setArtCustomizationDetails2(artistDetails);
        cust_trial.setReqStatus(ReqStatus.PENDING);
        reqStatusDao.save(cust_trial);
    }
    //

    //Many artists for a request in req table or cust table?
    @Transactional
    @Override
    public ArtCustomTrial save(Integer buyer_id, Integer artist_customizer, String art_type, String paper_canvas, String art_loc, Integer qty, String art_use, String desc) throws IOException {
        ArtCustomizationDetails art = new ArtCustomizationDetails();
        ArtCustomTrial artCustomTrial = new ArtCustomTrial();
        UserDetails user = new UserDetails();
        CustomizedRequestStatus cust = new CustomizedRequestStatus();
        CustomizedRequestStatusTrial cust_trial = new CustomizedRequestStatusTrial();
        user.setUser_id(buyer_id);
        art.setUserMO(user);
        artCustomTrial.setUserDetails(user);
        ArtistDetails artist = new ArtistDetails();
        artist.setArtist_id(artist_customizer);
        List<ArtistDetails> li = new ArrayList<>();
        li.add(artist);
        art.setArtistDetails3(artist);
        artCustomTrial.setArtistDetails(artist);
        art.setArt_location(art_loc);
        artCustomTrial.setArt_location(art_loc);
        art.setArt_use(art_use);
        artCustomTrial.setArt_use(art_use);
        art.setType_of_art(art_type);
        artCustomTrial.setType_of_art(art_type);
        art.setDescription(desc);
        artCustomTrial.setDescription(desc);
//        art.setRef_art_image(ref_img.getBytes());
//        artCustomTrial.setRef_art_image(ref_img.getBytes());
        art.setPaper_canvas(paper_canvas);
        artCustomTrial.setPaper_canvas(paper_canvas);
//        art.setArtistDetails3(artist);
        ArtCustomTrial saved = artCustomizationDao.save(artCustomTrial);
        cust_trial.setArtCustomizationDetails(artCustomTrial);
        cust_trial.setArtCustomizationDetails1(artCustomTrial);
        cust_trial.setArtCustomizationDetails2(artCustomTrial);
        cust_trial.setReqStatus(ReqStatus.PENDING);
        reqStatusDao.save(cust_trial);
        return saved;
    }

    @Transactional
    @Override
    public ArtCustomTrial saveImage(ArtCustomTrial customTrial) throws IOException {
        ArtCustomTrial artCustomTrial = artCustomizationDao.saveCustomizedImageByCustomID(customTrial);
        return artCustomTrial;
    }

    @Transactional
    @Override
    public ArtCustomTrial getCustReq(Integer custom_id) {
        return artCustomizationDao.getCustReq(custom_id);
    }
}
