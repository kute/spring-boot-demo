package com.kute.demo;

import com.kute.demo.dao.IUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Hashtable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

    @Autowired
    private IUserDao userDao;

	@Test
	public void testInsert() {
        String sql = "insert into User values(3, 'lili', 19)";
        userDao.execute(sql);
	}

}
