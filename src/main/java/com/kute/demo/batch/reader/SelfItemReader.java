package com.kute.demo.batch.reader;

import com.kute.demo.po.Person;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by kute on 2018/1/12.
 */
@Component
public class SelfItemReader {

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

    @Bean
    public JdbcCursorItemReader<Map<String, String>> jdbcCursorItemReader() {
        JdbcCursorItemReader<Map<String, String>> reader = new JdbcCursorItemReader<>();
        //reader逻辑根据实际需要写，先不举例
        return reader;
    }

}
