package in.ac.iiitb.speart.dao;

import java.util.List;

import in.ac.iiitb.speart.model.UserDetails;

public interface UserDetailsDao {

    List<UserDetails> get();

    UserDetails get(int id);

    void save(UserDetails userDetails);

    void delete(int id);

    public Integer getArtistID(String emailAddress);

    public Integer getID(String name) ;
}
