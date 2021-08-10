package com.gasstation.managementsystem.utils;

import org.junit.jupiter.api.Test;

import javax.persistence.Query;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryGenerateHelperTest {

    private QueryGenerateHelper queryGenerateHelper;
    private Map<String, Object> params = new HashMap<>();

    /**
     * values is null
     */
    @Test
    void in_UTCID01() {
        QueryGenerateHelper result = new QueryGenerateHelper();
        result.setParams(null);
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.in("field", "strKey", null);
    }

    /**
     * values is not null
     */
    @Test
    void in_UTCID02() {
        Object[] values = new Object[1];
        values[0] = "value1";
        QueryGenerateHelper result = new QueryGenerateHelper();
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.setQuery(new StringBuilder());

        params = new HashMap<>();
        params.put("strKey", Arrays.asList(values));
        result.setQuery(new StringBuilder(" AND field IN  (:strKey)"));
        result.setParams(params);
        assertEquals(result, queryGenerateHelper.in("field", "strKey", values));
    }

    /**
     * values is null
     */
    @Test
    void like_UTCID01() {
        QueryGenerateHelper result = new QueryGenerateHelper();
        result.setParams(null);
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.like("field", "strKey", null);
    }

    /**
     * values is not null
     */
    @Test
    void like_UTCID02() {
        String value = "value1";
        QueryGenerateHelper result = new QueryGenerateHelper();
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.setQuery(new StringBuilder());

        params = new HashMap<>();
        params.put("strKey", "%" + value + "%");
        result.setQuery(new StringBuilder(" AND field LIKE  (:strKey)"));
        result.setParams(params);
        assertEquals(result, queryGenerateHelper.like("field", "strKey", "value1"));
    }

    /**
     * values is null
     */
    @Test
    void between_UTCID01() {
        QueryGenerateHelper result = new QueryGenerateHelper();
        result.setParams(null);
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.between("field", 100d, 1000d, "strKey", null);
    }
    /**
     * values is null
     */
    @Test
    void equal_UTCID01() {
        QueryGenerateHelper result = new QueryGenerateHelper();
        result.setParams(null);
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.equal("field", "key", null);
    }

    /**
     * values is not null
     */

    @Test
    void equal_UTCID02() {
        String value = "value1";
        QueryGenerateHelper result = new QueryGenerateHelper();
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.setQuery(new StringBuilder());

        params = new HashMap<>();
        params.put("strKey",value);
        result.setQuery(new StringBuilder(" AND field =  (:strKey)"));
        result.setParams(params);
        assertEquals(result, queryGenerateHelper.equal("field", "strKey", value));
    }

    /**
     * values is not null
     */
    @Test
    void between_UTCID02() {
        Double value = 500d;
        QueryGenerateHelper result = new QueryGenerateHelper();
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.setQuery(new StringBuilder());

        params = new HashMap<>();
        params.put("strKey", value);
        result.setQuery(new StringBuilder(" AND field BETWEEN 100.0 AND  (:strKey)"));
        result.setParams(params);
        assertEquals(result, queryGenerateHelper.between("field", 100d, 1000d, "strKey", value));
    }

    /**
     * values is null
     */
    @Test
    void testBetween_UTCID01() {
        QueryGenerateHelper result = new QueryGenerateHelper();
        result.setParams(null);
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.between("field", 1L, 10L, "strKey", null);
    }

    /**
     * values is not null
     */
    @Test
    void testBetween_UTCID02() {
        Long value = 500L;
        QueryGenerateHelper result = new QueryGenerateHelper();
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.setQuery(new StringBuilder());

        params = new HashMap<>();
        params.put("strKey", value);
        result.setQuery(new StringBuilder(" AND field BETWEEN 100 AND  (:strKey)"));
        result.setParams(params);
        assertEquals(result, queryGenerateHelper.between("field", 100L, 1000L, "strKey", value));
    }

    /**
     * values is null
     */
    @Test
    void sort() {
        String value = null;
        QueryGenerateHelper result = new QueryGenerateHelper();
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.setQuery(new StringBuilder());

        params = new HashMap<>();
        result.setQuery(new StringBuilder(" ORDER BY field ASC"));
        result.setParams(params);
        assertEquals(result, queryGenerateHelper.sort("field", value));
    }

    @Test
    void setValueToParams() {
//        queryGenerateHelper = new QueryGenerateHelper();
//        queryGenerateHelper.setQuery(new StringBuilder());
//        params = new HashMap<>();
//        queryGenerateHelper.setValueToParams(query);
    }


    @Test
    void paging() {
        queryGenerateHelper = new QueryGenerateHelper();
        List list = new ArrayList<>();
        list.add(3);
        HashMap<String, Object> result = new HashMap<>();
        result.put("data", list);
        result.put("totalElement", 1000);
        result.put("totalPage", 334);

        assertEquals(result.toString(),queryGenerateHelper.paging(3,1000,list).toString());
    }

    @Test
    void isNULL() {
        QueryGenerateHelper result = new QueryGenerateHelper();
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.setQuery(new StringBuilder());

        params = new HashMap<>();
        result.setQuery(new StringBuilder(" field IS  NULL "));
        result.setParams(params);
        assertEquals(result, queryGenerateHelper.isNULL("field"));
    }

    @Test
    void isNotNULL() {
        QueryGenerateHelper result = new QueryGenerateHelper();
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.setQuery(new StringBuilder());

        params = new HashMap<>();
        result.setQuery(new StringBuilder(" field IS NOT  NULL "));
        result.setParams(params);
        assertEquals(result, queryGenerateHelper.isNotNULL("field"));
    }

    @Test
    void or() {
        QueryGenerateHelper result = new QueryGenerateHelper();
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.setQuery(new StringBuilder());

        params = new HashMap<>();
        result.setQuery(new StringBuilder(" OR "));
        result.setParams(params);
        assertEquals(result, queryGenerateHelper.or());
    }

    @Test
    void and() {
        QueryGenerateHelper result = new QueryGenerateHelper();
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.setQuery(new StringBuilder());

        params = new HashMap<>();
        result.setQuery(new StringBuilder(" AND "));
        result.setParams(params);
        assertEquals(result, queryGenerateHelper.and());
    }

    @Test
    void openBracket() {
        QueryGenerateHelper result = new QueryGenerateHelper();
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.setQuery(new StringBuilder());

        params = new HashMap<>();
        result.setQuery(new StringBuilder(" ( "));
        result.setParams(params);
        assertEquals(result, queryGenerateHelper.openBracket());
    }

    @Test
    void closeBracket() {
        QueryGenerateHelper result = new QueryGenerateHelper();
        queryGenerateHelper = new QueryGenerateHelper();
        queryGenerateHelper.setQuery(new StringBuilder());

        params = new HashMap<>();
        result.setQuery(new StringBuilder(" ) "));
        result.setParams(params);
        assertEquals(result, queryGenerateHelper.closeBracket());
    }
}