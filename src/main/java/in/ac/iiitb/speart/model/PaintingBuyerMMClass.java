package in.ac.iiitb.speart.model;
import java.io.Serializable;
import java.util.Objects;

public class PaintingBuyerMMClass implements Serializable {

    private Integer p_id;
    private Integer user_id;



    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaintingBuyerMMClass that = (PaintingBuyerMMClass) o;
        return Objects.equals(p_id, that.p_id) && Objects.equals(user_id, that.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p_id, user_id);
    }
}


