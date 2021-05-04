package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.dao.PaintingBuyerMMDao;
import in.ac.iiitb.speart.dao.PaintingRepoDetailsDao;
import in.ac.iiitb.speart.dao.UserDetailsDao;
import in.ac.iiitb.speart.model.PaintingBuyerMM;
import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.model.UserDetails;
import in.ac.iiitb.speart.utils.HelperFunctions;
import in.ac.iiitb.speart.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaintingBuyerMMServiceImpl implements PaintingBuyerMMService{

    @Autowired
    PaintingBuyerMMDao paintingBuyerMMDao;

    @Autowired
    PaintingRepoDetailsDao paintingRepoDetailsDao;

    @Autowired
    UserDetailsDao userDetailsDao;

    @Override
    @Transactional
    public PaintingBuyerMM postBiddingReqBuyerPrice(Integer painting_id, Float bidder_price, Integer bidder_id) {
        PaintingBuyerMM paintingBuyerMM = new PaintingBuyerMM();
        PaintingRepoDetails paintingRepoDetails = paintingRepoDetailsDao.get(painting_id.toString());
        UserDetails userDetails = userDetailsDao.get(bidder_id);
//        userDetails.setUser_id(bidder_id);
//        paintingRepoDetails.setP_id(painting_id);
        paintingBuyerMM.setP_id(paintingRepoDetails);
        paintingBuyerMM.setBidded_price(bidder_price);
        HelperFunctions helper = new HelperFunctions();
        java.util.Date utilDate = new java.util.Date(); // your util date
        java.sql.Date sqlDate = helper.returnSQLDate(utilDate);
        paintingBuyerMM.setBidded_date(sqlDate);
        paintingBuyerMM.setUser_id(userDetails);
        paintingBuyerMM = paintingBuyerMMDao.save(paintingBuyerMM);
        if(paintingRepoDetails.getHighest_price() == null){
            paintingRepoDetails.setHighest_price(bidder_price);
            java.sql.Date endDate = java.sql.Date.valueOf(sqlDate.toLocalDate().plusDays(5));
            paintingRepoDetails.setBidding_end_date(endDate);
        }
        else if (paintingRepoDetails.getHighest_price() < bidder_price){

            int daysLeft = sqlDate.compareTo(paintingRepoDetails.getBidding_end_date());

            paintingRepoDetails.setHighest_price(bidder_price);
            List<PaintingBuyerMM> biddersList = paintingBuyerMMDao.getAllBidders(painting_id, bidder_id, bidder_price);
            String emailReceivers[] = new String[biddersList.size()];
            for(int i = 0; i < biddersList.size(); i++){
                System.out.println(biddersList.get(i).getUser_id());
                UserDetails userID = biddersList.get(i).getUser_id();
                userID = userDetailsDao.get(userID.getUser_id());
                System.out.println(userID);
                emailReceivers[i] = userID.getEmail_address();
                System.out.println(userID.getEmail_address());

            }
            String paintingName = paintingRepoDetails.getPainting_name().substring(0,paintingRepoDetails.getPainting_name().indexOf("."));
            String message = "Some other interested bidder has raised the bid on art piece named"+paintingName+ ".Immediately take an action.";
            SendEmail.sendMail(emailReceivers, "Bidding price raised up by another bidder", message);
            System.out.println(biddersList);
        }
        paintingRepoDetailsDao.save(paintingRepoDetails);

        return paintingBuyerMM;
    }
}
