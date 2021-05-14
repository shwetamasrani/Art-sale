package in.ac.iiitb.speart.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;


//Added datasource
@Entity
@Table(name = "user_details")
public class UserDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer user_id;

//    @OneToMany(mappedBy = "user_id")
//    private List<PaintingBuyerMM> activities = new ArrayList<>();

    @Column
    private String first_name;
    @Column
    private String last_name;
    @Column
    private String email_address;
    @Column
    private String password;
    @Column
    private Integer contact_no;
    @Column
    private String address;
    @Column
    private String user_category;
    @Column
    private boolean log_status = false;

//    @JsonIgnoreProperties(value = {"users"})
//    @ManyToMany(mappedBy = "users")
//    Set<PaintingRepoDetails> painting;

    @OneToMany(mappedBy = "userMO")
    List<ArtCustomTrial> artCustomizationDetails;


    public UserDetails() {
    }

    public UserDetails(String first_name, String last_name, String email_address,
                       String password, Integer contact_no, String address, String user_category) {
//        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_address = email_address;
        this.password = password;
        this.contact_no = contact_no;
        this.address = address;
        this.user_category = user_category;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "user_id=" + user_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email_address='" + email_address + '\'' +
                ", password='" + password + '\'' +
                ", contact_no=" + contact_no +
                ", address='" + address + '\'' +
                ", user_category='" + user_category + '\'' +
                ", log_status=" + log_status +
                '}';
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getContact_no() {
        return contact_no;
    }

    public void setContact_no(Integer contact_no) {
        this.contact_no = contact_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser_category() {
        return user_category;
    }

    public void setUser_category(String user_category) {
        this.user_category = user_category;
    }

    public boolean isLog_status() {
        return log_status;
    }

    public void setLog_status(boolean log_status) {
        this.log_status = log_status;
    }

//
//    public Set<PaintingRepoDetails> getPainting() {
//        return painting;
//    }
//
//    public void setPainting(Set<PaintingRepoDetails> painting) {
//        this.painting = painting;
//    }

//    public List<PaintingBuyerMM> getActivities() {
//        return activities;
//    }
//
//    public void setActivities(List<PaintingBuyerMM> activities) {
//        this.activities = activities;
//    }


    public List<ArtCustomTrial> getArtCustomizationDetails() {
        return artCustomizationDetails;
    }

    public void setArtCustomizationDetails(List<ArtCustomTrial> artCustomizationDetails) {
        this.artCustomizationDetails = artCustomizationDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetails)) return false;
        UserDetails that = (UserDetails) o;
        return isLog_status() == that.isLog_status() && Objects.equals(getUser_id(), that.getUser_id()) && Objects.equals(getFirst_name(), that.getFirst_name()) && Objects.equals(getLast_name(), that.getLast_name()) && Objects.equals(getEmail_address(), that.getEmail_address()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getContact_no(), that.getContact_no()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getUser_category(), that.getUser_category()) && Objects.equals(artCustomizationDetails, that.artCustomizationDetails);

    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getFirst_name(), getLast_name(), getEmail_address(), getPassword(), getContact_no(), getAddress(), getUser_category(), isLog_status(), artCustomizationDetails);

    }
}