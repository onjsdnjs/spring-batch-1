package com.example.springbatch.project.batch.job.api;

import com.example.springbatch.project.batch.listener.JobListener;
import com.example.springbatch.project.batch.tasklet.ApiEndTasklet;
import com.example.springbatch.project.batch.tasklet.ApiStartTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApiJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    // api 시작,종료를 남기는 로그
    private final ApiStartTasklet apiStartTasklet;
    private final ApiEndTasklet apiEndTasklet;

    private final Step jobStep; // 부모

    @Bean
    public Job apiJob() {
        return jobBuilderFactory.get("apiJob")
                .listener(new JobListener())
                .start(apiStep1())
                .next(jobStep)
                .next(apiStep2())
                .build();
    }

    @Bean
    public Step apiStep1() {
        return stepBuilderFactory.get("apiStep1")
                .tasklet(apiStartTasklet)
                .build();
    }

    @Bean
    public Step apiStep2() {
        return stepBuilderFactory.get("apiStep2")
                .tasklet(apiEndTasklet)
                .build();
    }
}
