package org.home.gat.tag.impl;

import org.home.gat.tag.api.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TagStoreImpl implements TagStore {

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tag> list(String tagName) {
        return jdbcTemplate.query("SELECT * FROM TAG", (rs, rowNum) ->
                new Tag(rs.getLong("id"), rs.getString("name")));
    }

    @Override
    public Optional<Tag> getOne(String tagName) {
        ResultSetExtractor<Tag> resultSetExtractor =
                rs -> rs.next() ?
                        new Tag(rs.getLong("id"), rs.getString("name")) :
                        null;
        Tag tag = jdbcTemplate.query("SELECT * FROM TAG WHERE NAME=?",
                resultSetExtractor,
                tagName);
        return Optional.ofNullable(tag);
    }

    @Override
    public void add(String tagName) {
        jdbcTemplate.update("INSERT INTO TAG(NAME) VALUES (?)", tagName);
    }

    @Override
    public void add(String tagName, Long parentId) {
        jdbcTemplate.update("INSERT INTO TAG(NAME, PARENT_ID) VALUES (?,?)", tagName, parentId);
    }
}
