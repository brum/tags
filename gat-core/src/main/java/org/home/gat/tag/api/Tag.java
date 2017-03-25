package org.home.gat.tag.api;

import com.google.common.base.Preconditions;

import java.util.List;

public class Tag {

    private final Long id;
    private final String name;
    private final boolean hasChildren;

    public Tag(Long id, String name) {
        this(id, name, false);
    }

    private Tag(Long id, String name, boolean hasChildren) {
        Preconditions.checkNotNull(name);

        this.id = id;
        this.name = name;
        this.hasChildren = hasChildren;
    }

    public String getName() {
        return name;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public Long getId() {
        return id;
    }
}
