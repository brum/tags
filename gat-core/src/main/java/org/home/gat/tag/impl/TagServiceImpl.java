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
        checkTagNotExist(tagName);
        if (parentTagName == null) {
            tagStore.add(tagName);
        } else {
            Optional<Tag> parentTagOpt = tagStore.getOne(parentTagName);
            if (!parentTagOpt.isPresent()) {
                throw new TagNotFoundException(parentTagName);
            }
            tagStore.add(tagName, parentTagOpt.get().getId());
        }

    }

    private void checkTagExist(String tagName) {
        if (!tagStore.getOne(tagName).isPresent()) {
            throw new TagNotFoundException(tagName);
        }
    }

    private void checkTagNotExist(String tagName) {
        if (tagStore.getOne(tagName).isPresent()) {
            throw new TagAlreadyExistsException(tagName);
        }
    }
}
