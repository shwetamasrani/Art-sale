package in.ac.iiitb.speart.controller;

import in.ac.iiitb.speart.dao.PaintingRepoDetailsDao;
import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.service.PaintingRepoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class Dashboard {
    @Autowired
    PaintingRepoDetailsService paintingRepoDetailsService;

    @Autowired
    PaintingRepoDetailsDao paintingRepoDetailsDao;

    //Works in browser, not in postman. Try and see if this works in React.
    @RequestMapping(value = "/getPaintings", method = RequestMethod.GET)
    public List<PaintingRepoDetails> getDashboardPaintings(){
        List<PaintingRepoDetails> li = paintingRepoDetailsDao.getAllDashboardDetails();
        return li;
    }


    @RequestMapping(value = "/getArtistName/{id}", method = RequestMethod.GET)
    public String getArtistName(@PathVariable int id){
        return paintingRepoDetailsService.getArtistName(id);
    }
}
