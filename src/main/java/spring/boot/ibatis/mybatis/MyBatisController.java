package spring.boot.ibatis.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.ibatis.User;

import java.util.List;

/**
 * Created by chokst on 12/2/14.
 */


@RestController
@RequestMapping("/mybatis")
public class MyBatisController {
    @Autowired
    private MyBatisDAO myBatisDAO;

    // http://localhost:8083/mybatis/getAllUsersUsingMyBatis
    @RequestMapping("/getAllUsersUsingMyBatis")
    public List<User> getAllUsersUsingSession() throws Exception {
        return myBatisDAO.getAllUsersUsingMyBatis();
    }

    // http://localhost:8083/mybatis/getAllUsersUsingMyBatisMapperInterface
    @RequestMapping("/getAllUsersUsingMyBatisMapperInterface")
    public List<User> getAllUsersUsingMyBatisMapperInterface() throws Exception {
        return myBatisDAO.getAllUsersUsingMyBatisMapperInterface();
    }

    // http://localhost:8083/mybatis/getUserUsingMapperAnnotation
    @RequestMapping("/getUserUsingMapperAnnotation")
    public User getUserUsingMapperAnnotation() throws Exception {
        return myBatisDAO.getUserUsingMapperAnnotation();
    }

    @RequestMapping("/status")
    public String getStatus() {
        return "success";
    }

}
