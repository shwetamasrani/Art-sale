package in.ac.iiitb.speart.utils;

import in.ac.iiitb.speart.controller.Status;
import in.ac.iiitb.speart.dao.PaintingBuyerMMDao;
import in.ac.iiitb.speart.dao.PaintingRepoDetailsDao;
import in.ac.iiitb.speart.model.PaintingBuyerMM;
import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.service.PaintingBuyerMMService;
import in.ac.iiitb.speart.service.PaintingRepoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
//    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    PaintingRepoDetailsService paintingRepoDetailsService;

    @Autowired
    PaintingRepoDetailsDao paintingRepoDetailsDao;

    @Autowired
    PaintingBuyerMMService paintingBuyerMMService;

    @Autowired
    PaintingBuyerMMDao paintingBuyerMMDao;


    @Scheduled(fixedRate = 90000)
    public void scheduleTaskWithFixedRate() throws ParseException {
        System.out.println("Inside Scheduled Method.");
        List<PaintingRepoDetails> paintings = paintingRepoDetailsService.get();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        long millis=System.currentTimeMillis();
//        java.sql.Date date=new java.sql.Date(millis);
//        System.out.println(date);

        Date currentDate = new Date();
        String date = sdf.format(currentDate);
        currentDate = sdf.parse(date);
        for(int i = 0; i < paintings.size(); i++){
            Date bid_end_date = paintings.get(i).getBidding_end_date();
            if(bid_end_date != null){
                int compareDate = bid_end_date.compareTo(currentDate);
                if(compareDate < 0){
                    PaintingRepoDetails paintingRepoDetails = paintings.get(i);
                    PaintingBuyerMM paintBuyer = paintingBuyerMMDao.getAllBiddersPID(paintingRepoDetails.getP_id());
                    paintingRepoDetails.setArtistDetails2(paintBuyer.getUser_id());
                    paintingRepoDetails.setDate_of_purchase(currentDate);
                    Status status = paintingRepoDetailsService.save(paintingRepoDetails);


                }
            }

        }

    }

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
}