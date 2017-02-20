CREATE TABLE TAG (
  id        BIGINT GENERATED BY DEFAULT AS IDENTITY,
  name      VARCHAR2(255) NOT NULL,
	parent_id BIGINT NULL
);

INSERT INTO TAG (name) VALUES ('tag1');
INSERT INTO TAG (name) VALUES ('tag2');