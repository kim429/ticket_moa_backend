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
    price INT NOT NULL,                       -- 가격
    noti integer default 0 					  -- 즐겨찾기 
);

CREATE TABLE noti_fest (
    id VARCHAR(100),        -- 유저 ID (FK)
    fes_id INT,             -- 공연 ID (FK)
    PRIMARY KEY (id, fes_id),
    FOREIGN KEY (id) REFERENCES t_user(id) ON DELETE CASCADE,
    FOREIGN KEY (fes_id) REFERENCES festival(fes_id) ON DELETE CASCADE
);

CREATE TABLE reservation (
    res_id INT AUTO_INCREMENT PRIMARY KEY,      -- 예매 ID
    user_id VARCHAR(100),                       -- 유저 ID (FK)
    fes_id INT,                                 -- 공연 ID (FK)
    res_date DATETIME DEFAULT CURRENT_TIMESTAMP,-- 예매 일시
    ticket_count INT NOT NULL,                  -- 예매 수량
    total_price INT NOT NULL,                   -- 총 가격
    seat_row VARCHAR(1) NOT NULL,               -- 좌석 열 (A~Z)
    seat_col INT NOT NULL,                      -- 좌석 행 (숫자)
    FOREIGN KEY (user_id) REFERENCES t_user(id) ON DELETE CASCADE,
    FOREIGN KEY (fes_id) REFERENCES festival(fes_id) ON DELETE CASCADE
);

ALTER TABLE reservation ADD COLUMN used BOOLEAN DEFAULT FALSE;

CREATE TABLE review (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(100),
    fes_id INT,
    comment TEXT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES t_user(id) ON DELETE CASCADE,
    FOREIGN KEY (fes_id) REFERENCES festival(fes_id) ON DELETE CASCADE
);



select * from t_user;
select * from festival;

INSERT INTO t_user (id, pass, name, email, phone, profile, points) VALUES ('ssafy', 'ssafy', '사용자1', 'ssafy1@ssafy.com', '010-1234-5678', 'Avatar.png', 5028);
INSERT INTO t_user (id, pass, name, email, phone, profile, points) VALUES ('ssafy1', 'ssafy1', '사용자2', 'ssafy2@ssafy.com', '010-1234-5679', 'Avatar.png', 0);
INSERT INTO t_user (id, pass, name, email, phone, profile, points) VALUES ('ssafy2', 'ssafy2', '사용자3', 'ssafy3@ssafy.com', '010-1234-5677', 'Avatar.png', 0);

INSERT INTO festival (title, hall_name, fes_date, ticket_time, poster_img, des_img, price, noti) 
VALUES ('2025 인천펜타포트 락 페스티벌', '송도달빛축제공원', '2025-08-01', '2025-05-25 10:00:00', 'penta_poster.png', 'penta_desc.jpg', 120000, 23);
INSERT INTO festival (title, hall_name, fes_date, ticket_time, poster_img, des_img, price, noti) 
VALUES ('2025 Weverse Con Festival', '인스파이어 아레나', '2025-05-31', '2025-05-25 10:00:00', 'weverse_poster.jpg', 'weverse_desc.jpg', 187000, 0);
INSERT INTO festival (title, hall_name, fes_date, ticket_time, poster_img, des_img, price, noti) 
VALUES ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스 304호', '2025-05-28', '2025-05-25 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 13);

INSERT INTO noti_fest (id, fes_id) VALUES ('ssafy', 1);

INSERT INTO reservation (user_id, fes_id, ticket_count, total_price, seat_row, seat_col)
VALUES 
('ssafy', 1, 2, 240000, 'C', 7),
('ssafy1', 2, 1, 187000, 'A', 5);

INSERT INTO review (user_id, fes_id, comment, rating, created_at) VALUES
('ssafy', 1, '정말 멋진 공연이었어요! 다음에도 꼭 볼래요!', 5, NOW()),
('ssafy', 1, '무대가 조금 아쉬웠지만 전체적으로 만족합니다.', 4, NOW()),
('ssafy', 2, '지루했어요. 기대에 못 미쳤습니다.', 2, NOW()),
('ssafy', 3, '가족이랑 보기 딱 좋았어요. 아이들도 너무 좋아했어요.', 5, NOW()),
('ssafy', 2, '음향이 별로였어요. 공연 내용은 괜찮았지만...', 3, NOW());


commit;