package com.example.springbatch.project.batch.chunk.writer;

import com.example.springbatch.project.batch.domain.ApiRequestVO;
import com.example.springbatch.project.batch.domain.ApiResponseVO;
import com.example.springbatch.project.service.AbstractApiService;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ApiItemWriter2 implements ItemWriter<ApiRequestVO> {

    private final AbstractApiService apiService;

    public ApiItemWriter2(AbstractApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void write(List<? extends ApiRequestVO> items) throws Exception {
        ApiResponseVO responseVO = apiService.service(items);
        System.out.println("responseVO = " + responseVO);
    }
}
