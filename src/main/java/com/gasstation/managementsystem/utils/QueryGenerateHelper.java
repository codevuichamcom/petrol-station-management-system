package com.gasstation.managementsystem.utils;

import lombok.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryGenerateHelper {
    private StringBuilder query;
    private Map<String, Object> params = new HashMap<>();


    public QueryGenerateHelper in(String field, String key, Object[] values) {
        if (values == null) return this;
        query
                .append(" AND ")
                .append(field)
                .append(" IN ")
                .append(" (:").append(key).append(")");
        params.put(key, Arrays.asList(values));
        return this;
    }

    public QueryGenerateHelper like(String field, String key, String value) {
        if (value == null) return this;
        query
                .append(" AND ")
                .append(field)
                .append(" LIKE ")
                .append(" (:").append(key).append(")");
        params.put(key, "%" + value + "%");
        return this;
    }

    public QueryGenerateHelper between(String field, Double min, Double max, String key, Double value) {
        if (value == null || value < min || value > max) return this;
        query
                .append(" AND ")
                .append(field)
                .append(" BETWEEN ")
                .append(min)
                .append(" AND ")
                .append(" (:").append(key).append(")");
        params.put(key, value);
        return this;
    }

    public QueryGenerateHelper between(String field, Long min, Long max, String key, Long value) {
        if (value == null || value < min || value > max) return this;
        query
                .append(" AND ")
                .append(field)
                .append(" BETWEEN ")
                .append(min)
                .append(" AND ")
                .append(" (:").append(key).append(")");
        params.put(key, value);
        return this;
    }

    public QueryGenerateHelper sort(String field, String value) {
        if (value == null) {
            value = "ASC";
        }
        query
                .append(" ORDER BY ")
                .append(field)
                .append(" ")
                .append(value);
        return this;
    }
}
