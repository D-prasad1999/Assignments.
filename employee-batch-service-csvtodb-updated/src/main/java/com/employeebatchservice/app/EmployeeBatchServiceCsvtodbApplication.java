package com.employeebatchservice.app;

import javax.annotation.PostConstruct;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeBatchServiceCsvtodbApplication {

	 @Autowired
	 private JobLauncher jobLauncher;

	 @Autowired
	 private Job importEmployeeJob;
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeBatchServiceCsvtodbApplication.class, args);
	}
	
	@PostConstruct
    public void executeJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(importEmployeeJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
