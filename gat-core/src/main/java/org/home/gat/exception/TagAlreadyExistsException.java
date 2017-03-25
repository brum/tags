package org.home.gat.exception;

public class TagAlreadyExistsException extends GatException {

    public TagAlreadyExistsException(String tagName) {
        super("Tag '%s' already exists!", tagName);
    }
}
