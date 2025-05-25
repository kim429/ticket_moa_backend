DROP DATABASE IF EXISTS ticket_moa_app;
select @@global.transaction_isolation, @@transaction_isolation;
set @@transaction_isolation="read-committed";

create database ticket_moa_app;
use ticket_moa_app;

CREATE TABLE t_user (
    id varchar(100) PRIMARY KEY,            -- 유저ID
    pass VARCHAR(255) NOT NULL,                  -- 비밀번호
    name VARCHAR(100) NOT NULL,                  -- 이름
    email VARCHAR(255),          				-- 이메일
    phone VARCHAR(20),                           -- 전화번호
    profile VARCHAR(255) DEFAULT 'Avatar.png',   -- 프로필사진
    points integer default 0                     -- 포인트
);

CREATE TABLE festival (
    fes_id INT PRIMARY KEY AUTO_INCREMENT,    -- 공연ID
    title VARCHAR(255) NOT NULL,              -- 공연명
    hall_name VARCHAR(255) NOT NULL,		  -- 공연장
    fes_date DATE NOT NULL,                   -- 공연날짜
    ticket_time DATETIME NOT NULL,                -- 티켓팅시간
    poster_img VARCHAR(255),                  -- 포스터 이미지
    des_img VARCHAR(255),                     -- 설명 이미지
    price INT NOT NULL                       -- 가격
);

select * from t_user;
select * from festival;

INSERT INTO t_user (id, pass, name, email, phone, profile, points) VALUES ('ssafy', 'ssafy', '사용자1', 'ssafy1@ssafy.com', '010-1234-5678', 'Avatar.png', 5028);
INSERT INTO t_user (id, pass, name, email, phone, profile, points) VALUES ('ssafy1', 'ssafy1', '사용자2', 'ssafy2@ssafy.com', '010-1234-5679', 'Avatar.png', 0);
INSERT INTO t_user (id, pass, name, email, phone, profile, points) VALUES ('ssafy2', 'ssafy2', '사용자3', 'ssafy3@ssafy.com', '010-1234-5677', 'Avatar.png', 0);

INSERT INTO festival (title, hall_name, fes_date, ticket_time, poster_img, des_img, price) 
VALUES ('2025 인천펜타포트 락 페스티벌', '송도달빛축제공원', '2025-08-01', '2025-05-26 10:00:00', 'penta_poster.png', 'penta_desc.jpg', 120000);
INSERT INTO festival (title, hall_name, fes_date, ticket_time, poster_img, des_img, price) 
VALUES ('2025 Weverse Con Festival', '인스파이어 아레나', '2025-05-31', '2025-05-26 10:00:00', 'weverse_poster.jpg', 'weverse_desc.jpg', 187000);
INSERT INTO festival (title, hall_name, fes_date, ticket_time, poster_img, des_img, price) 
VALUES ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스 304호', '2025-05-28', '2025-05-28 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000);

commit;