package org.home.gat.tag.api;

import org.home.gat.GatCoreConfig;
import org.home.gat.exception.TagAlreadyExistsException;
import org.home.gat.exception.TagNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;

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
        List<Tag> tags = tagService.list(null);
        Assert.assertTrue(tags.size() == 1);
        Tag tag = tags.get(0);
        Assert.assertEquals("tag1", tag.getName());
        Assert.assertFalse(tag.isHasChildren());

        tagService.add("tag2", "tag1");
        tags = tagService.list(null);
        Assert.assertTrue(tags.size() == 1);
        Assert.assertTrue(tags.get(0).isHasChildren());

        tags = tagService.list("tag1");
        Assert.assertTrue(tags.size() == 1);
        tag = tags.get(0);
        Assert.assertEquals("tag2", tag.getName());
        Assert.assertFalse(tags.get(0).isHasChildren());
    }
}