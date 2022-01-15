package com.sep.restarauntreview.repository;

import com.sep.restarauntreview.domain.Restaurant;
import com.sep.restarauntreview.domain.Review;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ReviewRepository {

    private final NamedParameterJdbcOperations jdbcOperations;

    public Collection<Review> getAllForRestaurant(String restaurant) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("restaurant", restaurant);
        return jdbcOperations.query("SELECT id, restaurant, (SELECT ARRAY(SELECT cuisine FROM restaurant_cuisines rc WHERE restaurant = :restaurant)) as cuisines, quality, value FROM reviews re WHERE restaurant = :restaurant", namedParameters, new ReviewRowMapper());
    }

    public void save(Review review) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("restaurant", review.getRestarauntId())
                .addValue("quality", review.getQuality())
                .addValue("value", review.getValue());
        jdbcOperations.update("INSERT INTO reviews (restaurant, quality, value) " +
                "VALUES (:restaurant, :quality, :value)", namedParameters);
        saveCuisinesForRestaurant(review.getRestarauntId(), review.getCuisines());
    }

    private void saveCuisinesForRestaurant(String restaurant, Collection<String> cuisines) {
        SqlParameterSource[] batch = SqlParameterSourceUtils
                .createBatch(cuisines.stream().map(cuisine -> new HashMap<String, String>(){{ put("restaurant", restaurant); put("cuisine", cuisine); }}).collect(Collectors.toList()));
        jdbcOperations.batchUpdate("INSERT INTO restaurant_cuisines (restaurant, cuisine) " +
                "VALUES (:restaurant, :cuisine)", batch);
    }

    public void deleteReviewsByRestaurant(String restaurant) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("restaurant", restaurant);
        int rowsAffected = jdbcOperations.update("DELETE FROM reviews WHERE restaurant = :restaurant", namedParameters);
        if (rowsAffected == 0) {
            throw new EmptyResultDataAccessException(1);
        }
    }

    public static class ReviewRowMapper implements RowMapper<Review> {
        @Override
        public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Review.builder()
                    .id(rs.getObject("id", UUID.class).toString())
                    .restarauntId(rs.getString("restaurant"))
                    .cuisines(Arrays.asList((String[]) rs.getArray("cuisines").getArray()))
                    .quality(rs.getInt("quality"))
                    .value(rs.getInt("value"))
                    .build();
        }
    }
}
