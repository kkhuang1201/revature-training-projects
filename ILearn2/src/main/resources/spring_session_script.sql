DROP TABLE spring_session_attributes;
DROP TABLE spring_session;

create table SPRING_SESSION (
primary_id CHAR(36) NOT NULL,
session_id CHAR(36) NOT NULL,
creation_time BIGINT NOT NULL,
last_access_time BIGINT NOT NULL,
max_inactive_interval INT NOT NULL,
expiry_time BIGINT NOT NULL,
principal_name VARCHAR(100),
CONSTRAINT spring_session_pk PRIMARY KEY (primary_id)
);

create table SPRING_SESSION_ATTRIBUTES (
session_primary_id CHAR(36) NOT NULL,
attribute_name VARCHAR(200) NOT NULL,
attribute_bytes BYTEA NOT NULL,
CONSTRAINT spring_session_attributes_pk PRIMARY KEY (session_primary_id, attribute_name),
CONSTRAINT spring_session_attributes_fk FOREIGN KEY (session_primary_id) REFERENCES SPRING_SESSION(primary_id) ON DELETE CASCADE
);