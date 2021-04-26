package in.ac.iiitb.speart.dao;

import in.ac.iiitb.speart.controller.UserDetailsController;
import in.ac.iiitb.speart.model.ArtistDetails;
import in.ac.iiitb.speart.model.UserDetails;
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
    public ArtistDetails get(int id) {
        return null;
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

}
