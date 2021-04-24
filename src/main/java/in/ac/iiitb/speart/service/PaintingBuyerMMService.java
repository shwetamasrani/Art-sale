package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.model.PaintingBuyerMM;

public interface PaintingBuyerMMService {

    public PaintingBuyerMM postBiddingReqBuyerPrice(Integer painting_id, Float bidder_price, Integer bidder_id) ;
}
