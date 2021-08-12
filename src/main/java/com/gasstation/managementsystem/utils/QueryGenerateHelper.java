package com.gasstation.managementsystem.utils;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryGenerateHelper {
    private StringBuilder query;
    private Map<String, Object> params = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryGenerateHelper that = (QueryGenerateHelper) o;
        return this.query.toString().trim().equals(that.query.toString().trim()) &&
                this.params.toString().trim().equals(that.params.toString().trim());
    }

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
        if (StringUtils.isEmpty(value)) return this;
        query
                .append(" AND ")
                .append("LOWER(")
                .append(field)
                .append(")")
                .append(" LIKE LOWER")
                .append(" (:").append(key).append(")");
        params.put(key, "%" + value.toLowerCase() + "%");
        return this;
    }

    public QueryGenerateHelper equal(String field, String key, Object value) {
        if (value == null) return this;
        query
                .append(" AND ")
                .append(field)
                .append(" = ")
                .append(" (:").append(key).append(")");
        params.put(key, value);
        return this;
    }

    public QueryGenerateHelper between(String field, Double min, Double max) {
        if (min != null) {
            query.append(" AND ")
                    .append(field)
                    .append(" >= ")
                    .append(min);
        }
        if (max != null) {
            query.append(" AND ")
                    .append(field)
                    .append(" <= ")
                    .append(max);
        }
        return this;
    }

    public QueryGenerateHelper between(String field, Long min, Long max) {
        if (min != null) {
            query.append(" AND ")
                    .append(field)
                    .append(" >= ")
                    .append(min);
        }
        if (max != null) {
            query.append(" AND ")
                    .append(field)
                    .append(" <= ")
                    .append(max);
        }
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

    public QueryGenerateHelper sort(HashMap<String, String> sortMap) {
        AtomicBoolean isFirst = new AtomicBoolean(true);
        sortMap.forEach((field, value) -> {
            if (isFirst.get()) {
                query.append(" ORDER BY")
                        .append(field)
                        .append(" ")
                        .append(value);
                isFirst.set(false);
            }
            query.append(", ")
                    .append(field)
                    .append(" ")
                    .append(value);

        });
        return this;
    }

    public QueryGenerateHelper setValueToParams(Query query) {
        params.forEach(query::setParameter);
        return this;
    }

    public HashMap<String, Object> paging(Integer pageSize, long totalElement, List listResult) {
        long totalPage = totalElement / pageSize;
        if (totalElement % pageSize != 0) {
            totalPage++;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", listResult);
        map.put("totalElement", totalElement);
        map.put("totalPage", totalPage);
        return map;
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

    public QueryGenerateHelper openBracket() {
        query.append(" ( ");
        return this;
    }

    public QueryGenerateHelper closeBracket() {
        query.append(" ) ");
        return this;
    }
}
