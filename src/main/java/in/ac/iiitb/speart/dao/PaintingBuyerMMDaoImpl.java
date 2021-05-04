package in.ac.iiitb.speart.dao;

import in.ac.iiitb.speart.model.PaintingBuyerMM;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
public class PaintingBuyerMMDaoImpl implements PaintingBuyerMMDao{

    @Autowired
    private EntityManager entityManager;


    @Override
    public PaintingBuyerMM save(PaintingBuyerMM paintingBuyerMM) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(paintingBuyerMM);
                return paintingBuyerMM;
    }

    @Override
    public List<PaintingBuyerMM> getAllBidders(Integer p_id, Integer user_id, Float bidder_price){
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createNativeQuery("select * from painting_buyer_trial where p_id =:pid and bidded_price <= :priceBid", PaintingBuyerMM.class);
        query.setParameter("pid", p_id);
//        query.setParameter("userId", user_id);
        query.setParameter("priceBid", bidder_price);
        List<PaintingBuyerMM> returnList = (List<PaintingBuyerMM>)query.getResultList();
        return returnList ;
    }

    @Override
    public PaintingBuyerMM getAllBiddersPID(Integer p_id){
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createNativeQuery("select * from painting_buyer_trial  where p_id = :pid order by bidded_price desc", PaintingBuyerMM.class);
        query.setParameter("pid", p_id);
        return (PaintingBuyerMM) query.getResultList().get(0);
    }
}
