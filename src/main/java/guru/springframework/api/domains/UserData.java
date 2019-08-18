package guru.springframework.api.domains;

import java.io.Serializable;
import java.util.List;

public class UserData implements Serializable {

    List<User> data;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }


}
