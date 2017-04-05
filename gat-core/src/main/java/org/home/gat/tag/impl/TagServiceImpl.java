package org.home.gat.tag.impl;

import org.home.gat.exception.TagAlreadyExistsException;
import org.home.gat.exception.TagNotFoundException;
import org.home.gat.tag.api.Tag;
import org.home.gat.tag.api.TagService;

import java.util.List;
import java.util.Optional;

public class TagServiceImpl implements TagService {

    private final TagStore tagStore;

    public TagServiceImpl(TagStore tagStore) {
        this.tagStore = tagStore;
    }

    @Override
    public List<Tag> list(String tagName) {
        return tagStore.list(tagName);
    }

    @Override
    public void add(String tagName, String parentTagName) {
        if (tagStore.getId(tagName).isPresent()) {
            throw new TagAlreadyExistsException(tagName);
        }
        if (parentTagName == null) {
            tagStore.add(tagName);
        } else {
            Optional<Long> parentTagIdOpt = tagStore.getId(parentTagName);
            if (!parentTagIdOpt.isPresent()) {
                throw new TagNotFoundException(parentTagName);
            }
            tagStore.add(tagName, parentTagIdOpt.get());
        }
    }

}
