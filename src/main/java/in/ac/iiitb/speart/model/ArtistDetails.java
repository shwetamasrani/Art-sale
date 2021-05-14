package in.ac.iiitb.speart.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Arrays;
import java.util.List;


@Entity
@Table(name = "artist_details")
public class ArtistDetails implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private Integer artist_id;


        @OneToOne
        @JoinColumn(name="artist_user_id")
        private UserDetails userDetails;

        @Column
        private String category_taught;
        @Column
        private Integer experience;
        @Column
        private String type_of_art;
//        @Lob
//        private byte[] sample_images;

        @Column
        private String sample_image_name;


        @OneToMany(mappedBy = "artistDetails3")
        List<ArtCustomTrial> artCustomizationDetails;



        public ArtistDetails() {
        }

        public ArtistDetails(UserDetails userDetails, String category_taught, Integer experience, String type_of_art, String sample_image_name) {
//                this.artist_id = artist_id;
                this.userDetails = userDetails;
                this.category_taught = category_taught;
                this.experience = experience;
                this.type_of_art = type_of_art;
                this.sample_image_name = sample_image_name;
        }

        public Integer getArtist_id() {
                return artist_id;
        }

        public void setArtist_id(Integer artist_id) {
                this.artist_id = artist_id;
        }

        public UserDetails getUserDetails() {
                return userDetails;
        }

        public void setUserDetails(UserDetails userDetails) {
                this.userDetails = userDetails;
        }

        public String getCategory_taught() {
                return category_taught;
        }

        public void setCategory_taught(String category_taught) {
                this.category_taught = category_taught;
        }

        public Integer getExperience() {
                return experience;
        }

        public void setExperience(Integer experience) {
                this.experience = experience;
        }

        public String getType_of_art() {
                return type_of_art;
        }

        public void setType_of_art(String type_of_art) {
                this.type_of_art = type_of_art;
        }

//        public byte[] getSample_images() {
//                return sample_images;
//        }
//
//        public void setSample_images(byte[] sample_images) {
//                this.sample_images = sample_images;
//        }

        public String getSample_image_name() {
                return sample_image_name;
        }

        public void setSample_image_name(String sample_image_name) {
                this.sample_image_name = sample_image_name;
        }

        @Override
        public String toString() {
                return "ArtistDetails{" +
                        "artist_id=" + artist_id +
                        ", userDetails=" + userDetails +
                        ", category_taught='" + category_taught + '\'' +
                        ", experience=" + experience +
                        ", type_of_art='" + type_of_art + '\'' +
//                        ", sample_images=" + Arrays.toString(sample_images) +
                        '}';
        }

        public void setUser(UserDetails user){
                if (!user.equals(this.userDetails)){
                        this.userDetails=user;
                }
        }

        public List<ArtCustomTrial> getArtCustomizationDetails() {
                return artCustomizationDetails;
        }

        public void setArtCustomizationDetails(List<ArtCustomTrial> artCustomizationDetails) {
                this.artCustomizationDetails = artCustomizationDetails;
        }
}
