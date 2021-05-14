package in.ac.iiitb.speart.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "art_customization_trial")
public class ArtCustomTrial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer custom_id;

//    //Remove this
//    @OneToMany
//    @JoinColumn(name = "buyer_id_OM")
//    private List<UserDetails> userDetails1;


//    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id_MO")
    private UserDetails userMO;

    //    //Remove this
    @OneToOne
    @JoinColumn(name = "buyer_id", unique = false)
    private UserDetails userDetails;

    //Remove this
    @OneToOne
    @JoinColumn(name = "artist_customizer", unique = false)
    private ArtistDetails artistDetails;

//    @JsonManagedReference(value = "artistBack")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_MO")
    private ArtistDetails artistDetails3;

    @Column
    private String type_of_art;

    @Column(nullable = true)
    private String paper_canvas;

    @Column
    private Integer quantity;

    @Column
    private String art_location;

    @Column(length = 100)
    private String art_use;

    @Lob
    private byte[] ref_art_image;

    @Column(length = 200)
    private String description;


    public Integer getCustom_id() {
        return custom_id;
    }

    public void setCustom_id(Integer custom_id) {
        this.custom_id = custom_id;
    }
    //
    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public ArtistDetails getArtistDetails() {
        return artistDetails;
    }

    public void setArtistDetails(ArtistDetails artistDetails) {
        this.artistDetails = artistDetails;
    }

    public String getType_of_art() {
        return type_of_art;
    }

    public void setType_of_art(String type_of_art) {
        this.type_of_art = type_of_art;
    }

    public String getPaper_canvas() {
        return paper_canvas;
    }

    public void setPaper_canvas(String paper_canvas) {
        this.paper_canvas = paper_canvas;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getArt_location() {
        return art_location;
    }

    public void setArt_location(String art_location) {
        this.art_location = art_location;
    }

    public String getArt_use() {
        return art_use;
    }

    public void setArt_use(String art_use) {
        this.art_use = art_use;
    }

    public byte[] getRef_art_image() {
        return ref_art_image;
    }

    public void setRef_art_image(byte[] ref_art_image) {
        this.ref_art_image = ref_art_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArtistDetails getArtistDetails3() {
        return artistDetails3;
    }

    public void setArtistDetails3(ArtistDetails artistDetails3) {
        this.artistDetails3 = artistDetails3;
    }


//    public List<UserDetails> getUserDetails1() {
//        return userDetails1;
//    }
//
//    public void setUserDetails1(List<UserDetails> userDetails1) {
//        this.userDetails1 = userDetails1;
//    }
//
    public UserDetails getUserMO() {
        return userMO;
    }

    public void setUserMO(UserDetails userMO) {
        this.userMO = userMO;
    }
}
