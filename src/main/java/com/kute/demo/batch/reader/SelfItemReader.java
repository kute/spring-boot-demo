package com.kute.demo.batch.reader;

import com.kute.demo.po.Person;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by kute on 2018/1/12.
 */
@Component
public class SelfItemReader {

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource dataSource;

    public static final String INPUT = "data/sample.csv";

    @Bean(name = "flatFileItemReader")
    public FlatFileItemReader<Person> flatFileItemReader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(INPUT));
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setDelimiter(",");
                setNames(new String[]{"firstName", "lastName"});
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }

    /*@Bean
    public JdbcCursorItemReader<Map<String, String>> jdbcCursorItemReader() {
        JdbcCursorItemReader<Map<String, String>> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("select 1");
        reader.setRowMapper(((resultSet, i) -> {
            return null;
        }));
        //reader逻辑根据实际需要写，先不举例
        return reader;
    }*/

}
