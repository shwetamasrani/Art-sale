package in.ac.iiitb.speart.dao;

import java.util.List;

import in.ac.iiitb.speart.model.UserDetails;

public interface UserDetailsDao {

    List<UserDetails> get();

    UserDetails get(int id);

    void save(UserDetails userDetails);

    void delete(int id);

    Integer getArtistID(String emailAddress);

    Integer getID(String email) ;

    void updateUserCategoryStatus(UserDetails userDetails);

    UserDetails updateUserLoginStatus(UserDetails userDetails);

    UserDetails getUserProfile(String email_add);
}
