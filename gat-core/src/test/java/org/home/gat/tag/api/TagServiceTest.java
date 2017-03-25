package org.home.gat.tag.api;

import org.home.gat.GatCoreConfig;
import org.home.gat.exception.TagAlreadyExistsException;
import org.home.gat.exception.TagNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@JdbcTest
@SpringBootTest(classes = GatCoreConfig.class)
public class TagServiceTest {

    @Inject
    private TagService tagService;

    @Test
    public void listTest() {
        Assert.assertTrue(tagService.list(null).isEmpty());
    }

    @Test(expected = TagNotFoundException.class)
    public void addToNotExistsParentTest() {
        tagService.add("tag2", "tagParent");
    }

    @Test(expected = TagAlreadyExistsException.class)
    public void addAlreadyExistedTagTest() {
        tagService.add("tag1", null);
        tagService.add("tag1", null);
    }

    @Test
    public void addTest() {
        tagService.add("tag1", null);
        Assert.assertFalse(tagService.list(null).isEmpty());
    }
}