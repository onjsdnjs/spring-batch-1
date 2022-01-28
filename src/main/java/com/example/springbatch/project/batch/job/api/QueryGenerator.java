package com.example.springbatch.project.batch.job.api;

import com.example.springbatch.project.batch.domain.ProductVO;
import com.example.springbatch.project.batch.rowmapper.ProductRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryGenerator {

    public static ProductVO[] getProductList(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<ProductVO> productList = jdbcTemplate.query("select type as type from product group by type", new ProductRowMapper() {
            // 타입만 가져오기 별도 정의
            @Override
            public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return ProductVO.builder()
                        .type(rs.getString("type"))
                        .build();
            }
        });
        return productList.toArray(new ProductVO[]{});
    }

    public static Map<String, Object> getParameterForQuery(String parameter, String value) {

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(parameter, value);

        return parameters;
    }
}