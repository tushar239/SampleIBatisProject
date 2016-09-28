package spring.boot.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/jdbc")
public class JdbcController {
    
    @Autowired
    private MyDAO myDAO;

    @RequestMapping("/tryjdbc")
    public String execute() {
        myDAO.execute();
        return "Done";
    }
}
