package org.home.gat.exception;

public class TagNotFoundException extends GatException {

    public TagNotFoundException(String missingTagName) {
        super("Tag '%s' not found!", missingTagName);
    }
}
