package in.ac.iiitb.speart.dao;

import in.ac.iiitb.speart.model.CustomizedRequestStatus;
import in.ac.iiitb.speart.model.CustomizedRequestStatusTrial;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ReqStatusDaoImpl implements ReqStatusDao{

    @Autowired
    EntityManager entityManager;

    @Override
    public void save(CustomizedRequestStatusTrial customizedRequestStatus) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(customizedRequestStatus);
    }

    @Override
    public List<CustomizedRequestStatusTrial> getAll(Integer custom_id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createNativeQuery("select * from cust_req_status where custom_id =:id", CustomizedRequestStatusTrial.class);
        query.setParameter("id", custom_id);
        return query.getResultList();
    }

    @Override
    public void updateReqStatus(List<CustomizedRequestStatusTrial> list) {

    }
}
