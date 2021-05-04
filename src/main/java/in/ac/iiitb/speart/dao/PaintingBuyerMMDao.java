package in.ac.iiitb.speart.dao;

import in.ac.iiitb.speart.model.PaintingBuyerMM;

import java.util.List;

public interface PaintingBuyerMMDao {

    PaintingBuyerMM save(PaintingBuyerMM paintingBuyerMM);

    List<PaintingBuyerMM> getAllBidders(Integer p_id, Integer user_id, Float bidder_price);
}
