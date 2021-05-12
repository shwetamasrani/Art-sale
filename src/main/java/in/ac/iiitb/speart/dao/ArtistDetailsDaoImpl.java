package in.ac.iiitb.speart.dao;

import in.ac.iiitb.speart.model.ArtistDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ArtistDetailsDaoImpl implements ArtistDetailsDao {


    @Autowired
    private EntityManager entityManager;

    private static final Logger logger = LogManager.getLogger(ArtistDetailsDaoImpl.class);

    @Override
    public List<ArtistDetails> get() {
        Session currSession = entityManager.unwrap(Session.class);
        try {
            Query<ArtistDetails> query = currSession.createNativeQuery("select * from artist_details", ArtistDetails.class);
            List<ArtistDetails> list = query.getResultList();
            if (list == null || list.size() == 0)
                throw new Exception();
            return list;
        }
        catch(Exception e){
            logger.error("No list of painting images found.");
        }
        return null;
    }

    @Override
    public Object get(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createNativeQuery("select artist_details.artist_id, artist_details.category_taught, artist_details.experience," +
                "artist_details.type_of_art, artist_details.artist_user_id, painting_repo.p_id, painting_repo.bidding_end_date," +
                "painting_repo.category, painting_repo.painting_image from artist_details, painting_repo where artist_details.artist_user_id =:artistUserid " +
                "and artist_details.artist_id = painting_repo.artist_id");
        query.setParameter("artistUserid", id);
        return query.getSingleResult();
    }

    @Override
    public ArtistDetails save(ArtistDetails artistDetails) {
        Session currSession = entityManager.unwrap(Session.class);
        currSession.saveOrUpdate(artistDetails);
        return artistDetails;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int getArtistID(String email_address){
        Session currSession = entityManager.unwrap(Session.class);
        Query query = currSession.createNativeQuery("select artist_id from artist_details, user_details where user_details.email_address = :email and user_details.user_id = artist_details.artist_user_id");
        query.setParameter("email", email_address);
        return (int) query.getSingleResult();
    }

    @Override
    public ArtistDetails getApprovedArtistProfile(Integer status_artist_id) {
        Session currSession = entityManager.unwrap(Session.class);
        Query query = currSession.createNativeQuery("select * from artist_details where artist_id = :artistID", ArtistDetails.class);
        query.setParameter("artistID", status_artist_id);
        ArtistDetails artistDetails = (ArtistDetails) query.getSingleResult();
        return artistDetails;
    }

}
