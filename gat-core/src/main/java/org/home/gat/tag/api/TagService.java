package org.home.gat.tag.api;

import java.util.List;

public interface TagService {

    List<Tag> list(String tagName);

    void add(String tagName, String parentTagName);

}
