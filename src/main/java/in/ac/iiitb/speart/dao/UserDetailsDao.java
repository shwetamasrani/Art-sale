package in.ac.iiitb.speart.dao;

import java.util.List;

import in.ac.iiitb.speart.modal.UserDetails;

public interface UserDetailsDao {

    List<UserDetails> get();

    UserDetails get(int id);

    void save(UserDetails userDetails);

    void delete(int id);
}
