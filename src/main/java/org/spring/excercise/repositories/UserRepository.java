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
        String sqlUpdate = "UPDATE users SET name=?, email=? WHERE id = ?";
        jdbc.update(sqlUpdate, user.getName(), user.getEmail(), user.getId());
    }

    public void storeUsers(User user) {
        String sqlStore = "INSERT INTO users (name, email) VALUES (?,?)";
        jdbc.update(sqlStore, user.getName(), user.getEmail());
    }

    public List<User> findAllUsers() {
        String sqlSelect = "SELECT * from users";

        RowMapper<User> rowMapper = (r, i) -> {
            User rowObject =
                    new User(r.getInt("id"), r.getString("name"), r.getString("email"));
            return rowObject;
        };
        return jdbc.query(sqlSelect, rowMapper);
    }

    public void deleteUser(String id) {
        String sqlDelete = "DELETE from users WHERE id = ?";
        jdbc.update(sqlDelete, id);
    }
}
