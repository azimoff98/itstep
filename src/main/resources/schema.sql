DROP TABLE IF EXISTS tr_task;
DROP TABLE IF EXISTS tr_row;
DROP TABLE IF EXISTS tr_board;
DROP TABLE IF EXISTS tr_member;
DROP TABLE IF EXISTS tr_team;

CREATE TABLE tr_team (
  PRIMARY KEY (id),
  id   BIGSERIAL,
  name VARCHAR(55) NOT NULL
);

CREATE TABLE tr_member (
  PRIMARY KEY (id),
  id        BIGSERIAL,
  username  VARCHAR(30) NOT NULL,
  password  VARCHAR(30) NOT NULL,
  firstname VARCHAR(55),
  lastname  VARCHAR(55),
  email     VARCHAR(99),
  team_id   BIGINT,

  FOREIGN KEY (team_id) REFERENCES tr_team (id)
);

CREATE TABLE tr_board (
  PRIMARY KEY (id),
  id      BIGSERIAL,
  name    VARCHAR(55) NOT NULL,
  team_id BIGINT,
  FOREIGN KEY (team_id) REFERENCES tr_team (id)
);

CREATE TABLE tr_row (
  PRIMARY KEY (id),
  id       BIGSERIAL,
  name     VARCHAR(55) NOT NULL,
  board_id BIGINT,
  FOREIGN KEY (board_id) REFERENCES tr_board (id)
);

CREATE TABLE tr_task (
  PRIMARY KEY (id),
  id        BIGSERIAL,
  task_text VARCHAR(55) NOT NULL,
  row_id    BIGINT,

  FOREIGN KEY (row_id) REFERENCES tr_row (id)
);