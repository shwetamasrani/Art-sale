package in.ac.iiitb.speart.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "painting_repo")
public class PaintingRepoDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "p_id")
    private Integer p_id;

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @OneToMany(mappedBy = "painting")
//    private List<PaintingRepoDetails> p_id_copy;
//    @OneToMany(mappedBy = "p_id")
//    private List<PaintingBuyerMM> activities = new ArrayList<>();

    //Check this if manytoone or onetoone
    @OneToOne()
    @JoinColumn(name="artist_id")
    private ArtistDetails artistDetails;

    @Column
    private String category;

    @Column
    private Float price;

    @Column
    private Float highest_price;

    //Remove this
//    @OneToMany()
//    @JoinColumn(name="highest_bidder_id")
//    private List<UserDetails> artistDetails1;

    @OneToOne()
    @JoinColumn(name="highest_bidder_id_conf")
    private UserDetails artistDetails2;

    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date_of_purchase;

    @Lob
    private byte[] painting_image;

    @Column
    private String painting_name;

    @Column
    private String contentType;

//    @ManyToMany
//    @JsonIgnoreProperties(value = {"painting"})
//    @JoinTable(name = "painting_buyer", joinColumns = @JoinColumn(name = "p_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private Set<UserDetails> users;



    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date bidding_end_date;
//
//    @OneToMany(mappedBy = "paintings")
//    private List<PaintingUserDate> painting;



    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public ArtistDetails getArtistDetails() {
        return artistDetails;
    }

    public void setArtistDetails(ArtistDetails artistDetails) {
        this.artistDetails = artistDetails;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getHighest_price() {
        return highest_price;
    }

    public void setHighest_price(Float highest_price) {
        this.highest_price = highest_price;
    }
//
//    public UserDetails getUserDetails() {
//        return userDetails;
//    }
//
//    public void setUserDetails(UserDetails userDetails) {
//        this.userDetails = userDetails;
//    }

    public Date getDate_of_purchase() {
        return date_of_purchase;
    }

    public void setDate_of_purchase(Date date_of_purchase) {
        this.date_of_purchase = date_of_purchase;
    }

    public byte[] getPainting_image() {
        return painting_image;
    }

    public void setPainting_image(byte[] painting_image) {
        this.painting_image = painting_image;
    }

    public String getPainting_name() {
        return painting_name;
    }

    public void setPainting_name(String painting_name) {
        this.painting_name = painting_name;
    }

//    public List<UserDetails> getArtistDetails1() {
//        return artistDetails1;
//    }
//
//    public void setArtistDetails1(List<UserDetails> artistDetails1) {
//        this.artistDetails1 = artistDetails1;
//    }

//    public Set<UserDetails> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<UserDetails> users) {
//        this.users = users;
//    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getBidding_end_date() {
        return bidding_end_date;
    }

    public void setBidding_end_date(Date bidding_end_date) {
        this.bidding_end_date = bidding_end_date;
    }

    public UserDetails getArtistDetails2() {
        return artistDetails2;
    }

    public void setArtistDetails2(UserDetails artistDetails2) {
        this.artistDetails2 = artistDetails2;
    }


    // public List<PaintingBuyerMM> getActivities() {
    //     return activities;
    // }

    // public void setActivities(List<PaintingBuyerMM> activities) {
    //     this.activities = activities;
    // }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaintingRepoDetails that = (PaintingRepoDetails) o;
        return p_id.equals(that.p_id)&& Objects.equals(artistDetails, that.artistDetails) && Objects.equals(category, that.category) && Objects.equals(price, that.price) && Objects.equals(highest_price, that.highest_price) && Objects.equals(artistDetails2, that.artistDetails2) && Objects.equals(date_of_purchase, that.date_of_purchase) && Arrays.equals(painting_image, that.painting_image) && Objects.equals(painting_name, that.painting_name) && Objects.equals(contentType, that.contentType) && Objects.equals(bidding_end_date, that.bidding_end_date);


    }

    @Override
    public int hashCode() {
        int result = Objects.hash(p_id, artistDetails, category, price, highest_price, artistDetails2, date_of_purchase, painting_name, contentType, bidding_end_date);
        result = 31 * result + Arrays.hashCode(painting_image);
        return result;
    }
}
