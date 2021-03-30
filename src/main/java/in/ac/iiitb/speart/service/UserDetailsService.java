package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.modal.UserDetails;

import java.util.List;

public interface UserDetailsService {
    List<UserDetails> get();

    UserDetails get(int id);

    void save(UserDetails userDetails);

    void delete(int id);

}
