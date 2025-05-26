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
select * from review;

INSERT INTO t_user (id, pass, name, email, phone, profile, points) VALUES 
('ssafy', 'ssafy', '사용자1', 'ssafy1@ssafy.com', '010-1234-5678', 'Avatar.png', 5028),
('ssafy1', 'ssafy1', '사용자2', 'ssafy2@ssafy.com', '010-1234-5671', 'Avatar.png', 0),
('ssafy2', 'ssafy2', '사용자3', 'ssafy3@ssafy.com', '010-1234-5672', 'Avatar.png', 0),
('ssafy3', 'ssafy3', '사용자4', 'ssafy4@ssafy.com', '010-1234-5673', 'Avatar.png', 0),
('ssafy4', 'ssafy4', '사용자5', 'ssafy5@ssafy.com', '010-1234-5674', 'Avatar.png', 0),
('ssafy5', 'ssafy5', '사용자6', 'ssafy6@ssafy.com', '010-1234-5675', 'Avatar.png', 0),
('hamster1', 'pw1234', '햄스터1', 'ham1@ticket.com', '010-2345-1111', 'ham1.png', 1500),
('hamster2', 'pw1234', '햄스터2', 'ham2@ticket.com', '010-2345-2222', 'ham2.png', 800),
('hamster3', 'pw1234', '햄스터3', 'ham3@ticket.com', '010-2345-3333', 'ham3.png', 300),
('hamster4', 'pw1234', '햄스터4', 'ham4@ticket.com', '010-2345-4444', 'ham4.png', 2200);

INSERT INTO festival (title, hall_name, fes_date, ticket_time, poster_img, des_img, price, noti) VALUES
	('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-05-28', '2025-05-27 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
	('2025 인천펜타포트 락 페스티벌', '송도달빛축제공원', '2025-08-01', '2025-05-27 10:00:00', 'penta_poster.png', 'penta_desc.jpg', 120000, 64),
	('2025 Weverse Con Festival', '인스파이어 아레나', '2025-05-31', '2025-05-27 10:00:00', 'weverse_poster.jpg', 'weverse_desc.jpg', 187000, 0),
    ('뮤지컬 유진과 유진', '링크아트센터드림 4관', '2024-07-06', '2024-06-07 20:00:00', 'yujin_poster.jpg', 'yujin_desc.jpg', 55000, 10 ),
    ('2025 LCK MSI 대표 선발전', '부산사직실내체육관', '2025-06-07', '2025-05-27 20:00:00', 'msi_poster.jpg', 'msi_desc.jpg', 90000, 43),
    ('오이스터 단독콘서트 : 롤링 30주년 기념 공연', '롤링홀', '2025-06-10', '2025-05-27 10:00:00', 'oyster_poster.jpg', 'oyster_desc.jpg', 55000, 13),
    ('롤링홀 𝟯𝟬𝗧𝗛 𝗔𝗡𝗡𝗜𝗩𝗘𝗥𝗦𝗔𝗥𝗬 𝗖𝗢𝗡𝗖𝗘𝗥𝗧', '롤링홀', '2025-06-10', '2025-05-27 13:00:00', 'thefix_poster.jpg', 'thefix_desc.jpg', 55000, 7),
    ('이글루베이 단독 콘서트 : 롤링 29주년 기념 공연', '롤링홀', '2024-06-03', '2024-05-27 20:00:00', 'igloobay_poster.jpg', 'igloobay_desc.jpg', 55000, 3),
    ('BOYNEXTDOOR TOUR FINAL+ Hotels','KSPO DOME', '2025-07-25', '2025-05-27 15:23:33', 'bnd_poster.jpg', 'bnd_desc.jpg', 178000, 23),
    ('JEON SOMI 2025 FAN MEETING ［CHAOS］ IN SEOUL', '올림픽홀','2025-07-19', '2025-05-27 17:18:54', 'somi_poster.jpg', 'somi_desc.jpg', 99000, 23),
    ('국립현대무용단: 공일차원', '대학로예술극장 대극장', '2025-06-05', '2025-05-26 20:00:00', 'green_poster.jpg', 'green_desc.jpg', 20000, 7),
    ('2025 ILLIT GLITTER DAY IN SEOUL', '올림픽홀', '2025-06-07', '2025-05-27 13:00:00', 'illit_poster.jpg', 'illit_desc.jpg', 154000, 36),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30),
    ('안드로이드 일타강사 허태식의 Kotlin 특강', 'SSAFY 구미 캠퍼스', '2025-01-13', '2025-01-10 10:00:00', 'heo_poster.png', 'heo_desc.png', 1500000, 30);


INSERT INTO noti_fest (id, fes_id) VALUES 
	('ssafy', 1),
    ('ssafy', 3),
    ('ssafy', 5),
    ('ssafy', 9),
    ('ssafy', 10),
    ('ssafy', 11);

INSERT INTO reservation (user_id, fes_id, ticket_count, total_price, seat_row, seat_col)
VALUES 
('ssafy', 2, 1, 187000, 'A', 5);

INSERT INTO review (user_id, fes_id, comment, rating, created_at) VALUES
('ssafy', 3, '정말 멋진 공연이었어요! 다음에도 꼭 볼래요!', 5, NOW()),
('ssafy', 2, '무대가 조금 아쉬웠지만 전체적으로 만족합니다.', 4, NOW()),
('ssafy', 2, '지루했어요. 기대에 못 미쳤습니다.', 2, NOW()),
('ssafy', 3, '가족이랑 보기 딱 좋았어요. 아이들도 너무 좋아했어요.', 5, NOW()),
('ssafy', 2, '음향이 별로였어요. 공연 내용은 괜찮았지만...', 3, NOW()),
('ssafy', 1, '정말 유익한 강의였습니다. Kotlin의 모든 것을 배웠어요!', 5),
('ssafy1', 2, '락 페스티벌 분위기가 최고였어요. 무대 연출도 훌륭했습니다.', 4),
('hamster1', 3, '좋은 경험이었지만, 입장 대기가 좀 길었어요.', 3),
('hamster2', 4, '배우들의 연기력이 인상 깊었어요. 추천합니다!', 5),
('hamster3', 5, '경기장이 생각보다 작아서 아쉬웠지만 경기 내용은 만족스러웠습니다.', 4),
('ssafy2', 6, '기타 사운드가 예술이었습니다. 다음에도 꼭 가고 싶어요!', 5),
('ssafy3', 7, '아티스트의 열정이 느껴지는 공연이었어요.', 4),
('hamster4', 8, '분위기가 너무 조용해서 아쉬웠습니다.', 2),
('ssafy4', 9, '무대 구성과 조명이 인상 깊었습니다.', 4),
('ssafy5', 10, '팬미팅이라 그런지 아티스트와 가까워서 좋았어요.', 5),
('hamster1', 11, '공연 내용이 참신하고 즐거웠습니다.', 5),
('hamster2', 12, '가격에 비해 기대 이하였어요.', 2),
('hamster3', 1, '강의가 지루하지 않고 재밌었어요. 실습이 많아서 좋았어요.', 5),
('hamster4', 2, '사운드가 빵빵해서 귀가 아팠지만 신났어요!', 3),
('ssafy', 3, '처음 보는 아티스트였는데, 팬이 되었어요.', 5);


commit;