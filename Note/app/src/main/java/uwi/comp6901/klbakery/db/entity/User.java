package uwi.comp6901.klbakery.db.entity;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user",
        indices = {@Index(value = "user_email",unique = true)}
    )
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String user_type;
    private String user_name;

    private String user_email;
    private String user_password;
    private String address;

    public User(String user_type, String user_name, String user_email, String user_password, String address) {
        this.user_type = user_type;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public String getAddress() {
        return address;
    }
}
