package org.home.gat.tag.impl;

import org.home.gat.tag.api.Tag;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TagStoreImpl implements TagStore {

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tag> list(String tagName) {
        StringBuilder queryBuilder = new StringBuilder(
                "select t.name, count(child.id) > 0 as has_children from tag t" +
                        " left join tag child on child.parent_id = t.id");

        if (tagName == null) {
            queryBuilder.append(" where t.parent_id is null");
        } else {
            queryBuilder.append(" join tag parent on t.parent_id = parent.id and parent.name = '").append(tagName).append("'");
        }
        queryBuilder.append(" group by t.id, t.name order by t.name");

        return jdbcTemplate.query(queryBuilder.toString(), new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Optional<Long> getId(String tagName) {
        List<Long> results = jdbcTemplate.query(
                "SELECT ID FROM TAG WHERE NAME=?",
                new SingleColumnRowMapper<>(Long.class),
                tagName);
        return Optional.ofNullable(DataAccessUtils.singleResult(results));
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
