package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.dao.UserDetailsDao;
import in.ac.iiitb.speart.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDetailsDao userDetailsDao;

    @Transactional
    @Override
    public List<UserDetails> get() {
       return userDetailsDao.get();
    }

    @Transactional
    @Override
    public UserDetails get(int id) {
        return userDetailsDao.get(id);
    }

    @Transactional
    @Override
    public void save(UserDetails userDetails) {

        userDetailsDao.save(userDetails);
    }

    @Transactional
    @Override
    public void updateUserCategoryStatus(UserDetails userDetails){
        userDetailsDao.updateUserCategoryStatus(userDetails);
    }

    @Transactional
    @Override
    public UserDetails updateLoginStatus(UserDetails userDetails) {
        return userDetailsDao.updateUserLoginStatus(userDetails);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDetailsDao.delete(id);
    }

    @Override
    public Integer getUserID(String email) {
        return userDetailsDao.getID(email);
    }



}
