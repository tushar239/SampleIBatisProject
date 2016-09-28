package spring.boot.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;

@Component
public class MyDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void execute() {
        System.out.println("Creating tables");
        
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        jdbcTemplate.execute("drop table customers if exists");
        jdbcTemplate.execute("create table customers(" +
                "id serial, first_name varchar(255), last_name varchar(255))");

        String[] names = "John Woo;Jeff Dean;Josh Bloch;Josh Long".split(";");
        for (String fullname : names) {
            String[] name = fullname.split(" ");
            System.out.printf("Inserting customer record for %s %s\n", name[0], name[1]);
            jdbcTemplate.update(
                    "INSERT INTO customers(first_name,last_name) values(?,?)",
                    name[0], name[1]);
        }

        System.out.println("Querying for customer records where first_name = 'Josh':");
        List<Customer> results = jdbcTemplate.query(
                "select * from customers where first_name = ?", new Object[] { "Josh" },
                new RowMapper<Customer>() {
                    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Customer(rs.getLong("id"), rs.getString("first_name"),
                                rs.getString("last_name"));
                    }
                });

        for (Customer customer : results) {
            System.out.println(customer);
        }
    }
}
