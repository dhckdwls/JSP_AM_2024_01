DROP DATABASE IF EXISTS `JSP_AM`;
CREATE DATABASE `JSP_AM`;
USE `JSP_AM`;

# article 테이블 생성
CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

# member 테이블 생성
CREATE TABLE `member`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    loginId CHAR(100) NOT NULL,
    loginPw CHAR(200) NOT NULL,
    `name` CHAR(100) NOT NULL
);

# article TD 생성
INSERT INTO article
SET regDate = NOW(),
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
title = '제목3',
`body` = '내용3';

# member TD 생성
INSERT INTO `member`
SET regDate = NOW(),
loginId = 'test1',
loginPw = 'test1',
`name` = '회원1';

INSERT INTO `member`
SET regDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = '회원2';

INSERT INTO `member`
SET regDate = NOW(),
loginId = 'test3',
loginPw = 'test3',
`name` = '회원3';

ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER regDate;

UPDATE article 
SET memberId = 1
WHERE id IN (1,2);

UPDATE article 
SET memberId = 2
WHERE id = 3;

SELECT *
FROM article
ORDER BY id DESC;

SELECT *
FROM `member`
ORDER BY id DESC;

#################################################

DESC article;

INSERT INTO article
SET regDate = NOW(),
memberId = CEILING((RAND() * 9) / 3),
title = CONCAT('제목__', RAND()),
`body` = CONCAT('내용__',RAND());

SELECT CEILING((RAND() * 9) / 3);


SELECT COUNT(*) AS cnt
FROM `member`
WHERE loginId = 'test1';