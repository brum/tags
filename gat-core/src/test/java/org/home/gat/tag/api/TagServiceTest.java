package org.home.gat.tag.api;

import org.home.gat.GatCoreConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JdbcTest
@SpringBootTest(classes = GatCoreConfig.class)
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Test
    public void listTest() {
        Assert.assertEquals(2, tagService.list(null).size());
    }

}