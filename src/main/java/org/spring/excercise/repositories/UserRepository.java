package org.spring.excercise.repositories;


import org.spring.excercise.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void updateUser(User user) {
        String sqlUpdate = "UPDATE users SET name=?, email=? WHERE id = :id";

        jdbc.update(sqlUpdate, user.getId());
    }

    public void storeUsers(User user) {
        String sqlStore = "INSERT INTO users (name, email) VALUES (?,?)";
        jdbc.update(sqlStore, user.getName(), user.getEmail());
    }

    public List<User> findAllUsers() {
        String sqlSelect = "SELECT * from users";

        RowMapper<User> rowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setName(r.getString("name"));
            rowObject.setEmail(r.getString("email"));
            return rowObject;
        };
        return jdbc.query(sqlSelect, rowMapper);
    }

    public void deleteUser(User user) {
        String sqlDelete = "DELETE from users WHERE id = :id";
        jdbc.update(sqlDelete);
    }
}
