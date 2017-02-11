package org.home.gat.tag.impl;

import org.home.gat.tag.api.TagService;

import java.util.Arrays;
import java.util.List;

public class TagServiceImpl implements TagService {

    @Override
    public List<String> list() {
        return Arrays.asList("tag1", "temp", "photo");
    }
}
