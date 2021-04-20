package in.ac.iiitb.speart.dao;

import in.ac.iiitb.speart.controller.UserDetailsController;
import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.model.UserDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Arrays;
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
    public PaintingRepoDetails get(String id) {
        Session currSession = entityManager.unwrap(Session.class);
//        List<String> selectedValues = Arrays.asList(id);
//        final String parameterizedQuery = "select * from painting_repo where p_id in (:selectedValues)";
//        List<PaintingRepoDetails> list= currSession.createNativeQuery(parameterizedQuery)
//                .setParameter("selectedValues", selectedValues)
//                .getResultList();
//        System.out.println(parameterizedQuery);
//        System.out.println(list.size());
        Integer idInt = Integer.parseInt(id);
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
        currSession.saveOrUpdate(userDetails);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<PaintingRepoDetails> getAllDashboardDetails() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<PaintingRepoDetails> query = currSession.createNativeQuery("select p_id, painting_image, artist_id, highest_price, price, bidding_end_date from painting_repo");
        try{
            List<PaintingRepoDetails> list = query.getResultList();
            if(list.size() == 0)
                throw new Exception();
            System.out.println(list.size());
            logger.info("List of all paintings for dashboard is:", list);
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

}
