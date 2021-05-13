package in.ac.iiitb.speart.service;


import in.ac.iiitb.speart.controller.Status;
import in.ac.iiitb.speart.model.PaintingBuyerMM;
import in.ac.iiitb.speart.model.PaintingRepoAPI;
import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.model.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.DoubleStream;

public interface PaintingRepoDetailsService {

    List<PaintingRepoDetails> get();

    PaintingRepoDetails get(String id);

    Status save(PaintingRepoDetails paintingRepoDetails);

    void delete(int id);

    List<PaintingRepoDetails> getAllDashboardDetails();

    public String getArtistName(int id);

    void bidArtPieceByUser(Integer buyer_id, Integer p_id);

    Object getBiddingDetailsArtPiece(Integer painting_id);

    PaintingRepoDetails getPaintingByArtistID(Integer id);

    PaintingRepoAPI addExtraArtistImage(PaintingRepoAPI paintingRepoAPI);

    PaintingRepoDetails getPaintingByPaintingID(Integer painting_id);

    List<PaintingRepoDetails> getAllBiddingConf(Integer bidder_conf_id);
}
