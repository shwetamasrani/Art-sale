package in.ac.iiitb.speart.dao;

import in.ac.iiitb.speart.model.PaintingBuyerMM;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

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
}
