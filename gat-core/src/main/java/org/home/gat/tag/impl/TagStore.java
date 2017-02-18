package org.home.gat.tag.impl;

import org.home.gat.tag.api.Tag;

import java.util.List;

public interface TagStore {

    List<Tag> list(String tagName);

}
