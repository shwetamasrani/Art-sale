package in.ac.iiitb.speart.dao;

import in.ac.iiitb.speart.model.ArtCustomizationComments;
import in.ac.iiitb.speart.model.ArtCustomizationCommentsTrial;
import in.ac.iiitb.speart.model.ReqStatus;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import static in.ac.iiitb.speart.model.ReqStatus.APPROVED;

@Repository
public class RequestCommentsDaoImpl implements RequestCommentsDao{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ArtCustomizationCommentsTrial> getAllCommentsCustomID(Integer custom_id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createNativeQuery("select * from art_custom_comments_trial where status_custom_id =:customid", ArtCustomizationCommentsTrial.class);
        query.setParameter("customid", custom_id);
        List<ArtCustomizationCommentsTrial> list = query.getResultList();

        return list;
    }
//UPDATE table_name
//SET column1 = value1, column2 = value2, ...
//WHERE condition;
    @Override
    public ArtCustomizationCommentsTrial saveCommentsByArtist(ArtCustomizationCommentsTrial commentsObj) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(commentsObj);
        session.update(commentsObj);
        return commentsObj;
    }

    @Override
    public int getCommentID(Integer artist_id, Integer customization_id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createNativeQuery("select * from art_custom_comments_trial where status_custom_id =:customid and status_artist_id =:artistid", ArtCustomizationCommentsTrial.class);
        query.setParameter("customid", customization_id);
        query.setParameter("artistid", artist_id);
        List<ArtCustomizationCommentsTrial> li = query.getResultList();
        int id = li.get(0).getComments_id();
        return id;
    }

    @Override
    public void updateStatusApproved(int comment_id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createNativeQuery("update art_custom_comments_trial set request_status=:approved where " +
                "comments_id =:customid", ArtCustomizationCommentsTrial.class);
        query.setParameter("customid", comment_id);
        query.setParameter("approved", APPROVED.name());
        int result = query.executeUpdate();

    }

    @Override
    public void putUpdateReq() {

    }

    @Override
    public ArtCustomizationCommentsTrial findCommId(Integer comm_id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createNativeQuery("select * from art_custom_comments_trial where comments_id =:customid", ArtCustomizationCommentsTrial.class);
        query.setParameter("customid", comm_id);
        List<ArtCustomizationCommentsTrial> list = query.getResultList();
        if(list != null)
            return list.get(0);
        return null;
    }
}
