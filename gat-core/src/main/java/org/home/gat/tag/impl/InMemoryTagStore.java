package org.home.gat.tag.impl;

import org.home.gat.tag.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class InMemoryTagStore implements TagStore {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tag> list(String tagName) {
        return jdbcTemplate.query("SELECT * FROM TAG", (rs, rowNum) ->
                new Tag(rs.getString(2)));
    }
}
