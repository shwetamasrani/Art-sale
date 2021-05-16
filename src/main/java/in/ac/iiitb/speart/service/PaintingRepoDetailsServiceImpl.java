package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.controller.Status;
import in.ac.iiitb.speart.dao.PaintingRepoDetailsDao;
import in.ac.iiitb.speart.model.*;
import in.ac.iiitb.speart.utils.HelperFunctions;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Transactional;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PaintingRepoDetailsServiceImpl implements PaintingRepoDetailsService{

    @Autowired
    PaintingRepoDetailsDao paintingRepoDetailsDao;

    @Autowired
    ArtistDetailsService artistDetailsService;

    @Override
    public List<PaintingRepoDetails> get() {
       return paintingRepoDetailsDao.get();
    }

    @Override
    public PaintingRepoDetails get(String id) {
        return paintingRepoDetailsDao.get(id);
    }


    @Override
    @org.springframework.transaction.annotation.Transactional
    public Status save(PaintingRepoDetails paintingRepoDetails) {
        try{

            paintingRepoDetailsDao.save(paintingRepoDetails);
            return Status.SUCCESS;
        }
        catch(Exception e){
            System.out.println("rwFGWR "+e.getStackTrace());
            e.printStackTrace();
        }
        return Status.FAILURE;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<PaintingRepoDetails> getAllDashboardDetails() {
        return paintingRepoDetailsDao.getAllDashboardDetails();
    }

    @Override
    public String getArtistName(int id) {
        return paintingRepoDetailsDao.getArtistName(id);
    }

    @Override
    public void bidArtPieceByUser(Integer buyer_id, Integer p_id) {
        paintingRepoDetailsDao.bidAnArtPieceByUser(buyer_id, p_id);
    }

    @Override
    public Object getBiddingDetailsArtPiece(Integer painting_id) {
        return paintingRepoDetailsDao.getBiddingDetailsArtPiece(painting_id);
    }

    @Override
    @Transactional
    public PaintingRepoDetails getPaintingByArtistID(Integer id) {
        return paintingRepoDetailsDao.getPaintingDetailsByArtistId(id);
    }

    @Override
    public PaintingRepoAPI addExtraArtistImage(PaintingRepoAPI paintingRepoAPI) {
        PaintingRepoDetails paintingRepoDetails = new PaintingRepoDetails();
        paintingRepoDetails.setCategory(paintingRepoAPI.getCategory());
        paintingRepoDetails.setPrice(paintingRepoAPI.getPrice());
        paintingRepoDetails.setContentType("image/png");
        paintingRepoDetails.setPainting_name(paintingRepoAPI.getPainting_name());
        ArtistDetails artistDetails = artistDetailsService.getApprovedArtistProfile(paintingRepoAPI.getArtist_id());
        paintingRepoDetails.setArtistDetails(artistDetails);
        paintingRepoDetailsDao.addExtraArtistImage(paintingRepoDetails);
        paintingRepoAPI.setArtistDetails(artistDetails);
        return paintingRepoAPI;
    }

    @Override
    public PaintingRepoDetails getPaintingByPaintingID(Integer painting_id) {
        return paintingRepoDetailsDao.getPaintingDetailsByPID(painting_id);
    }

    @Override
    public List<PaintingRepoDetails> getAllBiddingConf(Integer bidder_conf_id) {
        return paintingRepoDetailsDao.getAllConfBidsByUserID(bidder_conf_id);
    }

    @Override
    public List<Object> getAllBiddingsActive(Integer bidder_id) {
//        PaintingRepoAPI
        return paintingRepoDetailsDao.getAllBiddingsActive(bidder_id);
    }

}
