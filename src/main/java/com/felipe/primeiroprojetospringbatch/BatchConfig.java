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
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@Configurable
public class BatchConfig {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job imprimereOlaJob() {
		return jobBuilderFactory
				.get("imprimereOlaJob")
				.start(imprimreOlaStep())
				.build();
	}
	
	public Step imprimreOlaStep() {
		return stepBuilderFactory
				.get("imprimreOlaStep")
				.tasklet(new Tasklet() {
					
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						System.out.println("Olá, mundo");
						return RepeatStatus.FINISHED;
					}
				})
				.build();
				
	}
	
}
