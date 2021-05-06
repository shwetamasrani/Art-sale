package in.ac.iiitb.speart.model;

public class RequestCommentsAPI {

    private String comments;

    private ArtistDetails artistDetails;

    private ArtCustomTrial artCustomizationDetails;

    private Integer status_custom_id;

    private Integer status_artist_id;

    private Integer comments_id;

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

    public ArtistDetails getArtistDetails() {
        return artistDetails;
    }

    public void setArtistDetails(ArtistDetails artistDetails) {
        this.artistDetails = artistDetails;
    }

    public ArtCustomTrial getArtCustomizationDetails() {
        return artCustomizationDetails;
    }

    public void setArtCustomizationDetails(ArtCustomTrial artCustomizationDetails) {
        this.artCustomizationDetails = artCustomizationDetails;
    }

    public Integer getStatus_custom_id() {
        return status_custom_id;
    }

    public void setStatus_custom_id(Integer status_custom_id) {
        this.status_custom_id = status_custom_id;
    }

    public Integer getStatus_artist_id() {
        return status_artist_id;
    }

    public void setStatus_artist_id(Integer status_artist_id) {
        this.status_artist_id = status_artist_id;
    }
}
