package in.ac.iiitb.speart.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import in.ac.iiitb.speart.controller.UserDetailsController;
import in.ac.iiitb.speart.model.ArtistDetails;
import in.ac.iiitb.speart.model.UserDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao{

    @Autowired
    private EntityManager entityManager;

    private static final Logger logger = LogManager.getLogger(UserDetailsDaoImpl.class);

    @Override
    public List<UserDetails> get() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<UserDetails> query = currSession.createNativeQuery("select * from user_details", UserDetails.class);
        List<UserDetails> list = query.getResultList();
        return list;
    }

    @Override
    public UserDetails get(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        UserDetails user = currSession.get(UserDetails.class, id);
        return user;

    }

    @Override
    public void save(UserDetails userDetails) {
        try{
        Session currSession = entityManager.unwrap(Session.class);
        if(checkUserAvail(userDetails.getEmail_address())) {
            currSession.saveOrUpdate(userDetails);
            logger.info("User created with:"+ userDetails);
        }
        else
            throw new Exception();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean checkUserAvail(String email_address) {
        try{
            Session currSession = entityManager.unwrap(Session.class);
            Query query = currSession.createNativeQuery("select * from art_spe3.user_details where email_address =:email", UserDetails.class);
            query.setParameter("email", email_address);
            List<UserDetails> list = query.getResultList();
            if(list == null || list.size() == 0)
                return true;
            else
                throw new Exception();
        }
         catch (Exception e) {
            logger.error("User with this username already exists", email_address);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        UserDetails user = currSession.get(UserDetails.class, id);
        currSession.delete(user);
    }


    @Override
    public Integer getArtistID(String name) {

        Session currSession = entityManager.unwrap(Session.class);
//        Query<UserDetails> query = currSession.createNativeQuery( "SELECT * FROM user_details e WHERE e.email_address = :emailAddress", UserDetails.class).setParameter("emailAddress", emailAddress);
//        System.out.println("Query:"+query.getQueryString()+"--"+emailAddress);
//        List<UserDetails> employee = query.getResultList();
//        System.out.println("Query:"+query.getQueryString()+"--"+emailAddress);
//        System.out.println(employee.get(0).getAddress());
//        Integer userID = employee.get(0).getUser_id();
//        return userID;

//        UserDetails person = null;
//
//          try{
//            person = (UserDetails)entityManager.createNativeQuery("select entity.user_id from user_details entity where entity.email_address = :name", UserDetails.class)
//                    .setParameter("name",name)
//                    .getSingleResult();
//            System.out.println(person.toString());
//        }
//          catch(Exception e){
//            //TO-DO do something if query retrieves nothing
//              System.out.println("Exception");
//        }
//
//          System.out.println(person.getUser_id());
//          return person.getUser_id();
        String sql = "SELECT * FROM user_details WHERE email_address = :employee_id";
        SQLQuery query = currSession.createSQLQuery(sql);
        query.addEntity(UserDetails.class);
        query.setParameter("employee_id", name);
        List<UserDetails> results = query.list();
        System.out.println(results.get(0).getUser_id());
        return results.get(0).getUser_id();
    }


    @Override
    public Integer getID(String email) {
        Session currSession = entityManager.unwrap(Session.class);
//        UserDetails emp = currSession.get(UserDetails.class, name);
        Query query = currSession.createNativeQuery("select artist_user_id from user_details, artist_details where email_address =:name and user_details.user_id = artist_details.artist_id", UserDetails.class);
        query.setParameter("name", email);
        System.out.println(query.getSingleResult());
        return (Integer) query.getSingleResult();
    }


}
