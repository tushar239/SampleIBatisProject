package spring.boot.ibatis.sqlmap;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.boot.ibatis.User;

import java.io.Reader;
import java.util.List;

/*
Two ways to use IBatis:-
SQLMap configuration
    requires ibatis-sqlmap dependency in pom.xml
MyBatis configuration
    requires mybatis dependency in pom.xml
 */



@Component
public class SqlMapDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SqlMapDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void drop() throws Exception {
        System.out.println("Droping tables");
        Reader reader = Resources.getResourceAsReader("ibatis/sql-maps-config.xml");
        SqlMapClient sqlmapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
        sqlmapClient.update("user.dropUserInfoTable");


    }
    public void create() throws Exception {
        System.out.println("Creating tables");

        Reader reader = Resources.getResourceAsReader("ibatis/sql-maps-config.xml");
        SqlMapClient sqlmapClient = SqlMapClientBuilder.buildSqlMapClient(reader);

        sqlmapClient.update("user.createUserInfoTable");
    }

    /*
    This method uses SQLMap configuration.

    SqlMapClient:-
    A thread safe client for working with your SQL Maps (Start Here).  This interface inherits transaction control
    and execution methods from the SqlMapTransactionManager and SqlMapExecutor interfaces.

    The SqlMapClient is the central class for working with SQL Maps.  This class will allow you
    to run mapped statements (select, insert, update, delete etc.), and also demarcate
    transactions and work with batches.  Once you have an SqlMapClient instance, everything
    you need to work with SQL Maps is easily available.
     */
    public User add(User user) throws Exception {
        //Create the SQLMapClient
        Reader reader = Resources.getResourceAsReader("ibatis/sql-maps-config.xml");
        SqlMapClient sqlmapClient = SqlMapClientBuilder.buildSqlMapClient(reader);

        //Add the user
        sqlmapClient.insert("user.addUser", user);

        Integer id = (Integer)sqlmapClient.queryForObject("user.getMaxId");
        user = getUserById(id, sqlmapClient);
        System.out.println(user);
        return user;
    }
    public User getUserById(Integer id, SqlMapClient sqlmapClient) {
        try
        {
            User user = (User)sqlmapClient.queryForObject("user.getUserById", id);
            return user;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /*
        This method uses SQLMap configuration.
     */

    public List<User> getAllUsers() throws Exception {
        System.out.println("Getting all users");

        Reader reader = Resources.getResourceAsReader("ibatis/sql-maps-config.xml");
        SqlMapClient sqlmapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
        List <User> users = (List<User>)
                sqlmapClient.queryForList("user.getAllUsers", null);
        System.out.println(users);
        return users;
    }

    /*
        This method uses SQLMap configuration.
        It uses own Session instead of SqlMapClient.
     */
    public List<User> getAllUsers(User user) throws Exception {
        System.out.println("Getting all users using dynamic sql");

        Reader reader = Resources.getResourceAsReader("ibatis/sql-maps-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            List <User> users = session.selectList("UserMapper.getAllUsers", user);
            System.out.println(users);
            return users;
        } finally {
            session.close();
        }
    }


}
