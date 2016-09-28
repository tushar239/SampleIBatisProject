package spring.boot.ibatis.mybatis;

import com.ibatis.common.resources.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;
import spring.boot.ibatis.User;

import java.io.Reader;
import java.util.List;

/**
 * Created by chokst on 12/2/14.
 */
@Component
public class MyBatisDAO {
    /*
     This method uses MyBatis configuration instead of SQLMap configuration.
  */
    public List<User> getAllUsersUsingMyBatis() throws Exception {
        System.out.println("Getting all users using fully qualified mapper name");

        Reader reader = Resources.getResourceAsReader("ibatis/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // Using fully qualified mapper name provided in UserMapper.xml
            List <User> users = session.selectList("spring.boot.ibatis.mybatis.UserMapper.getAllUsers");
            System.out.println(users);
            return users;
        } finally {
            session.close();
        }
    }

    public List<User> getAllUsersUsingMyBatisMapperInterface() throws Exception {
        System.out.println("Getting all users using mapper interface");

        Reader reader = Resources.getResourceAsReader("ibatis/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // Using Mapper interface. Related mapping is provided in UserMapper.xml
            UserMapper mapper = session.getMapper(UserMapper.class);
            List <User> users = mapper.getAllUsers();
            System.out.println(users);
            return users;
        } finally {
            session.close();
        }

        /*
        Another way

    <bean id="userMapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
        <property name="mapperInterface" value="spring.boot.ibatis.mybatis.UserMapper"/>
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>

    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="offersDataSource"/>
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:ibatis/UserMapper.xml"/>
    </bean>

    <bean id="offersDataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
        <property name="jndiName" value="java:comp/env/offerDataSource"/>
        <property name="resourceRef" value="true"/>
    </bean>

    - Inject UserMapper bean
    - userMapper.getAllUsers();

    OR
    DAO class can extend SqlSessionDaoSupport
    List <User> users = getSqlSession().selectList("spring.boot.ibatis.mybatis.UserMapper.getAllUsers");
*/
    }

    public User getUserUsingMapperAnnotation() throws Exception {
        System.out.println("Getting user using session");

        Reader reader = Resources.getResourceAsReader("ibatis/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // Using Mapper interface. Related mapping is provided in UserMapper.xml
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.getUser(1);
            System.out.println(user);
            return user;
        } finally {
            session.close();
        }

    }
}
