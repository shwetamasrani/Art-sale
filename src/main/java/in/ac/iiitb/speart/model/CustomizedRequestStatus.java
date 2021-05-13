package in.ac.iiitb.speart.model;

import javax.persistence.*;

//@Entity
//@Table(name = "cust_req_status")
public class CustomizedRequestStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer req_id;

    @OneToOne
    @JoinColumn(name = "custom_id")
    private ArtCustomizationDetails artCustomizationDetails;

    @OneToOne
    @JoinColumn(name = "req_buyer_id", referencedColumnName = "buyer_id")
    private ArtCustomizationDetails artCustomizationDetails1;

    @OneToOne
    @JoinColumn(name = "req_artist_id", referencedColumnName = "artist_customizer")
    private ArtCustomizationDetails artCustomizationDetails2;

    @Column
    private String comments;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private ReqStatus reqStatus;


    public Integer getReq_id() {
        return req_id;
    }

    public void setReq_id(Integer req_id) {
        this.req_id = req_id;
    }

    public ArtCustomizationDetails getArtCustomizationDetails() {
        return artCustomizationDetails;
    }

    public void setArtCustomizationDetails(ArtCustomizationDetails artCustomizationDetails) {
        this.artCustomizationDetails = artCustomizationDetails;
    }

    public ArtCustomizationDetails getArtCustomizationDetails1() {
        return artCustomizationDetails1;
    }

    public void setArtCustomizationDetails1(ArtCustomizationDetails artCustomizationDetails1) {
        this.artCustomizationDetails1 = artCustomizationDetails1;
    }

    public ArtCustomizationDetails getArtCustomizationDetails2() {
        return artCustomizationDetails2;
    }

    public void setArtCustomizationDetails2(ArtCustomizationDetails artCustomizationDetails2) {
        this.artCustomizationDetails2 = artCustomizationDetails2;
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
