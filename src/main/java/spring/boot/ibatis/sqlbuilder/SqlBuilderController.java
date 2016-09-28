package spring.boot.ibatis.sqlbuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.ibatis.User;

import java.util.List;

/**
 * Created by chokst on 12/2/14.
 */

@RestController
@RequestMapping("/sqlbuilder")
public class SqlBuilderController {
    @Autowired
    private SqlBuilderDAO sqlBuilderDAO;

    // http://localhost:8083/sqlbuilder/getAllUsers
    @RequestMapping("/getAllUsers")
    public List<User> getAllUsers() throws Exception {
        return sqlBuilderDAO.getAllUsers();
    }
}
