package in.ac.iiitb.speart.model;


import javax.persistence.*;

@Entity
@Table(name = "art_custom_comments")
public class ArtCustomizationComments {

    @OneToOne
    private ArtistDetails artistDetails;
//    @AttributeOverrides(value = {
//            @AttributeOverride(name = "artist_id", column = @Column(name = "art_customizer_id")),
//    })
    @OneToOne
    private ArtCustomizationDetails artCustomizationDetails;
//    @AttributeOverrides(value = {
//            @AttributeOverride(name = "custom_id", column = @Column(name = "customizable_art_id")),
//    })

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comments_id;

    @Column
    private String comments;


    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private ReqStatus reqStatus;


    public ArtistDetails getArtistDetails() {
        return artistDetails;
    }

    public void setArtistDetails(ArtistDetails artistDetails) {
        this.artistDetails = artistDetails;
    }

    public ArtCustomizationDetails getArtCustomizationDetails() {
        return artCustomizationDetails;
    }

    public void setArtCustomizationDetails(ArtCustomizationDetails artCustomizationDetails) {
        this.artCustomizationDetails = artCustomizationDetails;
    }

    public Integer getComments_id() {
        return comments_id;
    }

    public void setComments_id(Integer comments_id) {
        this.comments_id = comments_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ReqStatus getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(ReqStatus reqStatus) {
        this.reqStatus = reqStatus;
    }
}
