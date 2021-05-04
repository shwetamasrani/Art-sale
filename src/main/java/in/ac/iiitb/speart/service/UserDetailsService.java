package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.model.UserDetails;

import java.util.List;

public interface UserDetailsService {
    List<UserDetails> get();

    UserDetails get(int id);

    void save(UserDetails userDetails);

    void delete(int id);

    Integer getUserID(String email);

    void updateUserCategoryStatus(UserDetails userDetails);

    UserDetails updateLoginStatus(UserDetails userDetails);

    UserDetails getProfileUseEmail(String email_add);
}
