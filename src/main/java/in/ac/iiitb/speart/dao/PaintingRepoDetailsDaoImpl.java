package in.ac.iiitb.speart.dao;

import in.ac.iiitb.speart.model.PaintingBuyerMM;
import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.model.UserDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Repository
public class PaintingRepoDetailsDaoImpl implements PaintingRepoDetailsDao{

    @Autowired
    private EntityManager entityManager;

    private static final Logger logger = LogManager.getLogger(PaintingRepoDetailsDaoImpl.class);


    @Override
    public List<PaintingRepoDetails> get() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<PaintingRepoDetails> query = currSession.createNativeQuery("select * from painting_repo", PaintingRepoDetails.class);
        try{
            List<PaintingRepoDetails> list = query.getResultList();
            if(list == null || list.size() == 0)
                throw new Exception("No paintings exist!");
            else{
                logger.info("List of all paintings found from painting_repo", list);
                return list;
            }
        } catch(Exception e){
            logger.error("No paintings exist in painting_repo.");
        }
        return Collections.emptyList();
    }

    @Override
    public PaintingRepoDetails get(String p_id) {
        Session currSession = entityManager.unwrap(Session.class);
//        List<String> selectedValues = Arrays.asList(id);
//        final String parameterizedQuery = "select * from painting_repo where p_id in (:selectedValues)";
//        List<PaintingRepoDetails> list= currSession.createNativeQuery(parameterizedQuery)
//                .setParameter("selectedValues", selectedValues)
//                .getResultList();
//        System.out.println(parameterizedQuery);
//        System.out.println(list.size());
        Integer idInt = Integer.parseInt(p_id);
        String sql = "select * from painting_repo where p_id = :employee_id";
        SQLQuery query = currSession.createSQLQuery(sql);
        query.addEntity(PaintingRepoDetails.class);
        query.setParameter("employee_id", idInt);
        System.out.println(query.getQueryString());
       try{
        List<PaintingRepoDetails> results = query.list();
        if(results == null || results.size() == 0)
            throw new Exception();
        System.out.println(results.get(0));
        return results.get(0);
       } catch(Exception e){
           logger.error("No painting exists with given id/name.");
           return null; //check what to return
       }


    }

    @Override
    public void save(PaintingRepoDetails userDetails) {
        Session currSession = entityManager.unwrap(Session.class);
//        Transaction transaction = currSession.getTransaction();
        currSession.saveOrUpdate(userDetails);
//        transaction.commit();

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<PaintingRepoDetails> getAllDashboardDetails() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<PaintingRepoDetails> query = currSession.createNativeQuery("select * from painting_repo", PaintingRepoDetails.class);
        try{
            List<PaintingRepoDetails> list = query.getResultList();
            if(list.size() == 0)
                throw new Exception();
            System.out.println(list.size());
            logger.info("List of all paintings for dashboard is:", list.get(0));
            return list;
        }
        catch(Exception e){
            logger.error("Couldn't return the list of all paintings for dashboard", e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public String getArtistName(int id){
        Session currSession = entityManager.unwrap(Session.class);
        Query<UserDetails> query = currSession.createNativeQuery("select * from user_details where user_id = :employee_id", UserDetails.class);
        query.setParameter("employee_id", id);
        List<UserDetails> details = query.getResultList();
        System.out.println(details.size());
        String artist_name = details.get(0).getFirst_name() +" " +details.get(0).getLast_name();
        return artist_name;
    }

    @Override
    public void bidAnArtPieceByUser(Integer buyer_id, Integer p_id) {

    }

    @Override
    public Object getBiddingDetailsArtPiece(Integer painting_id) {
        Session currSession = entityManager.unwrap(Session.class);
        Query query = currSession.createNativeQuery("select * from painting_repo, artist_details, user_details where p_id =:pid and " +
                "                painting_repo.artist_id =artist_details.artist_id and artist_details.artist_user_id = user_details.user_id  ", PaintingRepoDetails.class);
        query.setParameter("pid", painting_id);
        return query.getSingleResult();


    }

    @Override
    public PaintingRepoDetails getPaintingDetailsByArtistId(Integer artist_id){

        Session currSession = entityManager.unwrap(Session.class);
        Query query = currSession.createNativeQuery("select * from painting_repo where artist_id =:pid " ,PaintingRepoDetails.class);
        query.setParameter("pid", artist_id);
        return (PaintingRepoDetails) query.getSingleResult();

    }

    @Override
    public void addExtraArtistImage(PaintingRepoDetails paintingRepoDetails) {
        Session currSession = entityManager.unwrap(Session.class);
        currSession.saveOrUpdate(paintingRepoDetails);
    }

    @Override
    public PaintingRepoDetails getPaintingDetailsByPID(Integer painting_id) {
        Session currSession = entityManager.unwrap(Session.class);
        Query query = currSession.createNativeQuery("select * from painting_repo where p_id =:pid " ,PaintingRepoDetails.class);
        query.setParameter("pid", painting_id);
        return (PaintingRepoDetails) query.getSingleResult();
    }

    @Override
    public List<PaintingRepoDetails> getAllConfBidsByUserID(Integer bidder_conf_id) {
        Session currSession = entityManager.unwrap(Session.class);
        Query query = currSession.createNativeQuery("select * from painting_repo where highest_bidder_id_conf =:pid " ,PaintingRepoDetails.class);
        query.setParameter("pid", bidder_conf_id);
        return query.getResultList();
    }

}
