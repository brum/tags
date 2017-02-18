package org.home.gat.tag.impl;

import org.home.gat.tag.api.Tag;
import org.home.gat.tag.api.TagService;

import java.util.List;

public class TagServiceImpl implements TagService {

    private final TagStore tagStore;

    public TagServiceImpl(TagStore tagStore) {
        this.tagStore = tagStore;
    }

    @Override
    public List<Tag> list(String tagName) {
        return tagStore.list(tagName);
    }
}
