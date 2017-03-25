package org.home.gat.tag.impl;

import org.home.gat.tag.api.Tag;

import java.util.List;
import java.util.Optional;

public interface TagStore {

    List<Tag> list(String tagName);

    Optional<Tag> getOne(String tagName);

    void add(String tagName);

    void add(String tagName, Long parantId);

}
