package spring.boot.ibatis;

/**
 * Created by chokst on 12/1/14.
 */

//@Alias("USER") // You can use this annotation instead of <typeAlias> inside mybatis-config.xml
public class User {
    private Integer id;
    private String firstName, lastName, email;
    public User() {

    }
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "spring.boot.ibatis.User[id=%d, firstName='%s', lastName='%s', email='%s']",
                id, firstName, lastName, email);
    }
}
