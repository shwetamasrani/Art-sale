package in.ac.iiitb.speart.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "painting_buyer_trial")
@IdClass(PaintingBuyerMMClass.class)
public class PaintingBuyerMM implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "p_id", referencedColumnName="p_id")
    private PaintingRepoDetails p_id;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName="user_id")
    private UserDetails user_id;

    @JoinColumn(name = "bidded_price")
    private Float bidded_price;

    @JoinColumn(name = "bidded_date")
    private Date bidded_date;

    public PaintingBuyerMM() {
    }

    public PaintingRepoDetails getP_id() {
        return p_id;
    }

    public void setP_id(PaintingRepoDetails p_id) {
        this.p_id = p_id;
    }

    public UserDetails getUser_id() {
        return user_id;
    }

    public void setUser_id(UserDetails user_id) {
        this.user_id = user_id;
    }

    public Float getBidded_price() {
        return bidded_price;
    }

    public void setBidded_price(Float bidded_price) {
        this.bidded_price = bidded_price;
    }

    public Date getBidded_date() {
        return bidded_date;
    }

    public void setBidded_date(Date bidded_date) {
        this.bidded_date = bidded_date;
    }


}
