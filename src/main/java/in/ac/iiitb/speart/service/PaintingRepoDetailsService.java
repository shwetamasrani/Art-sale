package in.ac.iiitb.speart.service;


import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.model.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.DoubleStream;

public interface PaintingRepoDetailsService {

    List<PaintingRepoDetails> get();

    PaintingRepoDetails get(String id);

    void save(PaintingRepoDetails paintingRepoDetails);

    void delete(int id);

    List<PaintingRepoDetails> getAllDashboardDetails();

    public String getArtistName(int id);

}
