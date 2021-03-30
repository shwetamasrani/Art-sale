package in.ac.iiitb.speart.dao;

import java.util.List;
import javax.crypto.Cipher;
import javax.persistence.EntityManager;

import in.ac.iiitb.speart.modal.UserDetails;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao{

    @Autowired
    private EntityManager entityManager;
    @Override
    public List<UserDetails> get() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<UserDetails> query = currSession.createNativeQuery("select * from user_details");
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
        Session currSession = entityManager.unwrap(Session.class);
        currSession.saveOrUpdate(userDetails);
    }

    @Override
    public void delete(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        UserDetails user = currSession.get(UserDetails.class, id);
        currSession.delete(user);
    }
}
