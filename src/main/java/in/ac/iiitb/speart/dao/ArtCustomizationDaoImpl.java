package in.ac.iiitb.speart.dao;


import in.ac.iiitb.speart.model.ArtCustomTrial;
import in.ac.iiitb.speart.model.ArtCustomizationDetails;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ArtCustomizationDaoImpl implements ArtCustomizationDao{

    @Autowired
    EntityManager entityManager;

    @Override
    public List<ArtCustomTrial> get(Integer artistId) {
        Session session = entityManager.unwrap(Session.class);
        Query query = entityManager.createNativeQuery("select * from art_customization_trial where artist_customizer =:artist_id", ArtCustomTrial.class);
        query.setParameter("artist_id", artistId);
        return query.getResultList();
    }

    @Override
    public List<ArtCustomTrial> get(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = entityManager.createNativeQuery("select * from art_customization_trial, cust_req_status where buyer_id =:buy_id and " +
                "cust_req_status.custom_id = art_customization_trial.custom_id", ArtCustomTrial.class);
        query.setParameter("buy_id", id);
        List<ArtCustomTrial> list = query.getResultList();
        return list;
    }

    @Override
    public void save(ArtCustomTrial artCustom) {
        Session currSession = entityManager.unwrap(Session.class);
        currSession.saveOrUpdate(artCustom);
    }

    @Override
    public void delete(int id) {

    }
}
