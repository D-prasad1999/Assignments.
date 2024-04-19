package com.employeebatchservice.app.configuration;

import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import com.employeebatchservice.app.entity.Employee;

@Configuration
@EnableBatchProcessing
public class EmployeeBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Employee> reader() {
        JdbcCursorItemReader<Employee> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT id, name, salary, age FROM employee");
        reader.setRowMapper(new BeanPropertyRowMapper<>(Employee.class));
        return reader;
    }

    @Bean
	public EmployeeProcessor processor() {
		return new EmployeeProcessor();
	}

    @Bean
    public StaxEventItemWriter<Employee> writer() {
        StaxEventItemWriter<Employee> writer = new StaxEventItemWriter<>();
        writer.setResource(new FileSystemResource("src/main/resources/employees.xml"));
        writer.setRootTagName("employees");
        writer.setMarshaller(new Jaxb2Marshaller() {{
            setClassesToBeBound(Employee.class);
        }});
        return writer;
	}

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step1")
                .<Employee, Employee>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job exportEmployeeJob() {
        return jobBuilderFactory.get("exportEmployeeJob")
                .incrementer(new RunIdIncrementer())
                .flow(step())
                .end()
                .build();
    }
}
