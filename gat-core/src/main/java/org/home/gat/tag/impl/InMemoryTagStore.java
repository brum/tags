package org.home.gat.tag.impl;

import com.google.common.base.Strings;
import org.home.gat.exception.TagNotFoundException;
import org.home.gat.tag.api.Tag;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InMemoryTagStore implements TagStore {

    @Override
    public List<Tag> list(String tagName) {
        if (Strings.isNullOrEmpty(tagName)) {
            return Arrays.asList(
                    new Tag("tag1", true),
                    new Tag("tag2", true),
                    new Tag("tag11", false),
                    new Tag("tag12", true),
                    new Tag("tag121", false),
                    new Tag("tag21", false)
            );
        }
        switch (tagName) {
            case "tag1": {
                return Arrays.asList(
                        new Tag("tag11", false),
                        new Tag("tag12", true)
                );
            }
            case "tag2": {
                return Arrays.asList(
                        new Tag("tag21", false)
                );
            }
            case "tag12": {
                return Arrays.asList(
                        new Tag("tag121", false)
                );
            }
            case "tag11":
            case "tag121":
            case "tag21": {
                return Collections.emptyList();
            }
            default: {
                throw new TagNotFoundException(tagName);
            }
        }
    }
}
