package com.kute.demo.batch.writer;

import com.kute.demo.po.Person;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by kute on 2018/1/12.
 */
@Component
public class SelfItemWriter {

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource dataSource;

    @Bean(name = "jdbcBatchItemWriter")
    public JdbcBatchItemWriter<Person> writer() {
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("insert into Person(first_name, last_name) values(:firstName, :lastName)");
        return writer;
    }
}
