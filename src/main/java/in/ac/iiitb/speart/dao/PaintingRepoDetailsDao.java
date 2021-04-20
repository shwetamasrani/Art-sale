package in.ac.iiitb.speart.dao;

import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.model.UserDetails;

import java.util.Arrays;
import java.util.List;

public interface PaintingRepoDetailsDao {

    List<PaintingRepoDetails> get();

    PaintingRepoDetails get(String id);

    void save(PaintingRepoDetails userDetails);

    void delete(int id);

    List<PaintingRepoDetails> getAllDashboardDetails();

    public String getArtistName(int id);
}
