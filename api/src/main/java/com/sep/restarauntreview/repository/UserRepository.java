package com.sep.restarauntreview.repository;

import com.sep.restarauntreview.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;

@Repository
@AllArgsConstructor
public record UserRepository(NamedParameterJdbcOperations jdbcOperations,
                             PasswordEncoder passwordEncoder) {

    public Collection<String> getAllUsernames() {
        return jdbcOperations.queryForList("SELETE username FROM users", Collections.emptyMap(), String.class);
    }

    public void save(User user) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("password", passwordEncoder.encode(user.getPassword()));
        jdbcOperations.update("INSERT INTO users (username, password) VALUES (:username, :password)", namedParameters);
    }

    public void deleteByUsername(String username) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("username", username);
        jdbcOperations.update("DELETE FROM users WHERE username = :username", namedParameters);
    }
}
