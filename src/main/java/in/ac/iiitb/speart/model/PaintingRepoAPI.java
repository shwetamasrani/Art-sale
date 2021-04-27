package in.ac.iiitb.speart.model;

import org.springframework.web.bind.annotation.RequestParam;

public class PaintingRepoAPI {

    private Integer buyer_id;

    private  Integer p_id;

    private Integer painting_id;

    private Float bidded_price;

    public Integer getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(Integer buyer_id) {
        this.buyer_id = buyer_id;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public Integer getPainting_id() {
        return painting_id;
    }

    public void setPainting_id(Integer painting_id) {
        this.painting_id = painting_id;
    }

    public Float getBidded_price() {
        return bidded_price;
    }

    public void setBidded_price(Float bidded_price) {
        this.bidded_price = bidded_price;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    private Integer user_id;


}
