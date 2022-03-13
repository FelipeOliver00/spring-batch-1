package com.felipe.primeiroprojetospringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class PrimeiroprojetospringbatchApplication {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	public static void main(String[] args) {
		SpringApplication.run(PrimeiroprojetospringbatchApplication.class, args);
	}
	
	
	@Bean
	public Step step() {
		return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
			

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Hello, World");
				return RepeatStatus.FINISHED;
			}
		}).build();
				
	}
	
	@Bean
	public Job job() {
		return this.jobBuilderFactory.get("job").start(step()).build();
	}
	
}
