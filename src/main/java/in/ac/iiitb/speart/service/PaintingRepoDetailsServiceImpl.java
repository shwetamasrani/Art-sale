package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.dao.PaintingRepoDetailsDao;
import in.ac.iiitb.speart.model.ArtistDetails;
import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.model.UserDetails;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Stream;

@Service
public class PaintingRepoDetailsServiceImpl implements PaintingRepoDetailsService{

    @Autowired
    PaintingRepoDetailsDao paintingRepoDetailsDao;

    @Override
    public List<PaintingRepoDetails> get() {
       return paintingRepoDetailsDao.get();
    }

    @Override
    public PaintingRepoDetails get(String id) {
        return paintingRepoDetailsDao.get(id);
    }


    @Override
    public void save(PaintingRepoDetails paintingRepoDetails) {
        try{

            paintingRepoDetailsDao.save(paintingRepoDetails);
        }
        catch(Exception e){
            System.out.println("rwFGWR "+e.getStackTrace());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<PaintingRepoDetails> getAllDashboardDetails() {
        return paintingRepoDetailsDao.getAllDashboardDetails();
    }

    @Override
    public String getArtistName(int id) {
        return paintingRepoDetailsDao.getArtistName(id);
    }

}
