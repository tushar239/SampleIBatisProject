package spring.boot.ibatis.sqlbuilder;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import spring.boot.ibatis.User;

import javax.sql.DataSource;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/*
    Building SQL statements programatically
    http://mybatis.github.io/mybatis-3/statement-builders.html
 */
@Component
public class SqlBuilderDAO {
    public List<User> getAllUsers() throws Exception {
        System.out.println("Getting all users");


        Reader reader = Resources.getResourceAsReader("ibatis/sql-maps-config.xml");
        SqlMapClient sqlmapClient = SqlMapClientBuilder.buildSqlMapClient(reader);

        List<User> users = new ArrayList<User>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            DataSource dataSource = sqlmapClient.getDataSource();
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectUsersSql());
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("ID"));
                user.setFirstName(resultSet.getString("FNAME"));
                user.setLastName(resultSet.getString("LNAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                users.add(user);
            }
        }finally {
            resultSet.close();
            statement.close();
            connection.close();;

        }
        System.out.println(users);
        return users;
    }

    /* building sql programtaically */
    public String selectUsersSql() {
        return new SQL()
                .SELECT("*")
                .FROM("USERINFO")
                .toString();
    }
}
