package top.forethought.framework.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void insert(User user){
        String []args={user.getName(),user.getSex(),user.getDescription()};

        jdbcTemplate.update("insert into tb_index001 (name,sex,description) VALUES (?,?,?)",args);
    }
    public void addBatch(List<User> userList){

        jdbcTemplate.batchUpdate("insert into tb_index001 (name,sex,description) VALUES (?,?,?)",userList, 500, (ps, argument) -> {
            ps.setString(1, argument.getName());
            ps.setString(2,argument.getSex());
            ps.setString(3,argument.getDescription());

        });
    }

}
