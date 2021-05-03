package in.ac.iiitb.speart.model;

public class ArtCustomizationAPI {

    private Integer custom_id;

    private Integer buyer_id;

    private Integer artist_customizer;

    private String type_of_art;

    private String paper_canvas;

    private String art_location;

    private Integer quantity;

    private String art_use;

    private String description;

    private byte[] ref_art_image;

    public Integer getCustom_id() {
        return custom_id;
    }

    public void setCustom_id(Integer custom_id) {
        this.custom_id = custom_id;
    }

    public Integer getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(Integer buyer_id) {
        this.buyer_id = buyer_id;
    }

    public Integer getArtist_customizer() {
        return artist_customizer;
    }

    public void setArtist_customizer(Integer artist_customizer) {
        this.artist_customizer = artist_customizer;
    }

    public String getPaper_canvas() {
        return paper_canvas;
    }

    public void setPaper_canvas(String paper_canvas) {
        this.paper_canvas = paper_canvas;
    }


    public String getArt_use() {
        return art_use;
    }

    public void setArt_use(String art_use) {
        this.art_use = art_use;
    }


    public String getType_of_art() {
        return type_of_art;
    }

    public void setType_of_art(String type_of_art) {
        this.type_of_art = type_of_art;
    }

    public String getArt_location() {
        return art_location;
    }

    public void setArt_location(String art_location) {
        this.art_location = art_location;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getRef_art_image() {
        return ref_art_image;
    }

    public void setRef_art_image(byte[] ref_art_image) {
        this.ref_art_image = ref_art_image;
    }
}
