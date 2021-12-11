package com.sep.restarauntreview.repository;

import com.sep.restarauntreview.domain.Restaurant;
import com.sep.restarauntreview.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class RestaurantRepository {

    private final NamedParameterJdbcOperations jdbcOperations;

    public Collection<Restaurant> getAll() {
        return jdbcOperations.query("SELECT * FROM restaurants", new RestaurantRowMapper());
    }

    public Restaurant getById(String id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbcOperations.queryForObject("SELECT * FROM restaurants WHERE id = :id", namedParameters, new RestaurantRowMapper());
    }

    public void save(Restaurant restaurant) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", restaurant.getName());
        jdbcOperations.update("INSERT INTO restaurants (name) VALUES (:name)", namedParameters);
    }

    public void deleteById(String id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        int rowsAffected = jdbcOperations.update("DELETE FROM restaurants WHERE id = :id", namedParameters);
        if (rowsAffected == 0) {
            throw new EmptyResultDataAccessException(1);
        }
    }

    public static class RestaurantRowMapper implements RowMapper<Restaurant> {
        @Override
        public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Restaurant.builder()
                    .id(rs.getObject("id", UUID.class).toString())
                    .name(rs.getString("name"))
                    .build();
        }
    }
}
