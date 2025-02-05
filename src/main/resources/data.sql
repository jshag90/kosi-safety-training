-- 사용자 테이블에 데이터 삽입
INSERT INTO `user` (username, password, nickname, activated) 
VALUES ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);

INSERT INTO `user` (username, password, nickname, activated) 
VALUES ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1);

-- 권한 테이블에 데이터 삽입
INSERT INTO authority (authority_name) VALUES ('ROLE_USER');
INSERT INTO authority (authority_name) VALUES ('ROLE_ADMIN');

-- user_authority 관계 테이블에 데이터 삽입
INSERT INTO user_authority (user_id, authority_name) 
VALUES ((SELECT user_id FROM `user` WHERE username = 'admin'), 'ROLE_USER');

INSERT INTO user_authority (user_id, authority_name) 
VALUES ((SELECT user_id FROM `user` WHERE username = 'admin'), 'ROLE_ADMIN');

INSERT INTO user_authority (user_id, authority_name) 
VALUES ((SELECT user_id FROM `user` WHERE username = 'user'), 'ROLE_USER');
