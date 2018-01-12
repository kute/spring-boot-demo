package com.kute.demo.batch.processor;

import com.kute.demo.po.Person;
import org.slf4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by kute on 2018/1/12.
 */
@Component
public class SelfItemProcessor {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Bean(name = "itemProcessor")
    public ItemProcessor<Person, Person> processor() {
        return (person) -> {
            final String firstName = person.getFirstName().toUpperCase();
            final String lastName = person.getLastName().toUpperCase();

            final Person transformedPerson = new Person(firstName, lastName);

            logger.info("Converting [{}] into [{}]", person, transformedPerson);

            return transformedPerson;
        };
    }

}
