package spring.boot.ibatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
Two ways to use IBatis:-
SQLMap configuration (e.g. IbatisController)
    requires ibatis-sqlmap dependency in pom.xml
MyBatis configuration (e.g. MyBatisController)
    requires mybatis dependency in pom.xml
    there are 3 ways using MuBatis configuration
    - using mapping.xml directly
    - using mapper interface that is linked to mapping.xml
    - using SqlBuilder

Concept of Dynamic Queries:-
http://www.tutorialspoint.com/ibatis/ibatis_dynamic_sql.htm
Sometime you have changing WHERE clause criterion based on your parameter object's state.
e.g. if some field is null then add some condition in WHERE clause otherwise add some other condition
create IN clause from the parameter of type list


To test
1) <start-class>spring.boot.ibatis.IbatisApplication</start-class> and comment out other <start-class>
2) mvn spring-boot:run   --- jetty will run with port 8083 as mentioned in application.properties
3)
create USERINFO table
(IbatisController) http://localhost:8083/sqlmap/createUserTable

add a row in USERINFO table
(IbatisController) http://localhost:8083/sqlmap/addUser

get all records from USERINFO table
(MyBatisController) http://localhost:8083/mybatis/getAllUsersUsingMyBatis
OR
(SqlBuilderController) http://localhost:8083/sqlbuilder/getAllUsers

To drop USERINFO table
(IbatisController) http://localhost:8083/sqlmapdropUserTable

*/

@Configuration
@ComponentScan(basePackages = "spring.boot.ibatis")
@EnableAutoConfiguration
public class IbatisApplication {

    public static void main(String args[]) throws Exception {
        EmbeddedWebApplicationContext webApplicationContext = (EmbeddedWebApplicationContext) SpringApplication.run(IbatisApplication.class, args);
    }

}
