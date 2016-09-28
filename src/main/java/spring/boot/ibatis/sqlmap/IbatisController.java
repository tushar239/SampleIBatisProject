package spring.boot.ibatis.sqlmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.ibatis.User;

import java.util.List;

/**
 * Created by chokst on 12/1/14.
 */


@RestController
@RequestMapping("/sqlmap")
public class IbatisController {
    @Autowired
    private SqlMapDAO myIbatisDAO;

    // http://localhost:8083/sqlmap/createUserTable

    @RequestMapping("/createUserTable")
    public void createUserTable()  throws Exception {
        myIbatisDAO.create();
    }

    // http://localhost:8083/sqlmap/dropUserTable

    @RequestMapping("/dropUserTable")
    public void dropUserTable()  throws Exception {
        myIbatisDAO.drop();
    }

    // http://localhost:8083/sqlmap/addUser

    @RequestMapping("/addUser")
    public String addUser() throws Exception {

        //dropUserTable();
        //createUserTable();

        User user = new User("Tushar", "Chokshi", "tushar.chokshi@cdk.com");
        User createdUser = myIbatisDAO.add(user);
        return createdUser.toString();
    }

    // http://localhost:8083/sqlmap/getAllUsers

    @RequestMapping("/getAllUsers")
    public List<User> getAllUsers() throws Exception {
        return myIbatisDAO.getAllUsers();
    }

    // http://localhost:8083/sqlmap/getAllUsersWithIdAndName
    @RequestMapping("/getAllUsersWithIdAndName")
    public List<User> getAllUsersWithIdAndName() throws Exception {
        User user = new User();
        user.setId(1);
        user.setFirstName("Tushar");
        return myIbatisDAO.getAllUsers(user);
    }



}
