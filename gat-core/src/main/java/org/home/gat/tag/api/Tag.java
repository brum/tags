package org.home.gat.tag.api;

import com.google.common.base.Preconditions;

import java.util.List;

public class Tag {

    private final String name;
    private final boolean hasChildren;

    public Tag(String name) {
        this(name, false);
    }

    public Tag(String name, boolean hasChildren) {
        Preconditions.checkNotNull(name);

        this.name = name;
        this.hasChildren = hasChildren;
    }

    public String getName() {
        return name;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }
}
