package com.gasstation.managementsystem.utils;

import lombok.*;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
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

    public HashMap<String, Object> paging(TypedQuery<?> tQuery, Query countTotalQuery, Integer pageIndex, Integer pageSize) {
        params.forEach((k, v) -> {
            tQuery.setParameter(k, v);
            countTotalQuery.setParameter(k, v);
        });
        long totalElement = (long) countTotalQuery.getSingleResult();
        long totalPage = totalElement / pageSize;
        if (totalElement % pageSize != 0) {
            totalPage++;
        }
        tQuery.setFirstResult((pageIndex - 1) * pageSize);
        tQuery.setMaxResults(pageSize);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", tQuery.getResultList());
        map.put("totalElement", totalElement);
        map.put("totalPage", totalPage);
        return map;
    }

    public QueryGenerateHelper isNULL(String field) {
        query
                .append(" ")
                .append(field)
                .append(" IS ")
                .append(" NULL ");
        return this;
    }

    public QueryGenerateHelper isNotNULL(String field) {
        query
                .append(" ")
                .append(field)
                .append(" IS NOT ")
                .append(" NULL ");
        return this;
    }

    public QueryGenerateHelper or() {
        query.append(" OR ");
        return this;
    }

    public QueryGenerateHelper and() {
        query.append(" AND ");
        return this;
    }
}
