package simplepojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(value="id", allowSetters = true)
public class UserRoot {
    private String name;
    private String username;
    private String email;
    private Address address;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT) //ignore default value for ID = 0 when sending request
    private int id;

    public UserRoot() {
    }

    public UserRoot(String name, String username, String email, Address address) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
