package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.controller.Status;
import in.ac.iiitb.speart.dao.PaintingRepoDetailsDao;
import in.ac.iiitb.speart.model.ArtistDetails;
import in.ac.iiitb.speart.model.PaintingBuyerMM;
import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.model.UserDetails;
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

}
