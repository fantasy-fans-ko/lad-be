CREATE TABLE `users`
(
    `id`               bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '사용자 id',
    `nickname`         varchar(16)  NOT NULL COMMENT '사용자 닉네임',
    `kakao_code`       varchar(255) NOT NULL COMMENT '카카오에서 받은 사용자 고유번호',
    `kakao_image_path` varchar(255) NOT NULL COMMENT '카카오에서 받은 프로필 사진 경로',
    `kakao_email`      varchar(49)  NOT NULL COMMENT '카카오에서 받은 email',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT '사용자 정보';

CREATE TABLE `players`
(
    `id`             bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '선수 id',
    `name`           varchar(16) NOT NULL COMMENT '선수 이름',
    `position`       varchar(4)  NOT NULL COMMENT '선수 포지션',
    `three_pct`      float       NOT NULL COMMENT '3점 슛 성공률',
    `ft_pct`         float       NOT NULL COMMENT '자유투 성공률',
    `fg_pct`         float       NOT NULL COMMENT '야투 성공률',
    `points`         int         NOT NULL COMMENT '득점',
    `rebounds`       int         NOT NULL COMMENT '리바운드',
    `assists`        int         NOT NULL COMMENT '어시스트',
    `steals`         int         NOT NULL COMMENT '스틸',
    `blocks`         int         NOT NULL COMMENT '블록',
    `turnovers`      int         NOT NULL COMMENT '실책',
    `triple_doubles` int         NOT NULL COMMENT '트리플 더블',
    `team`           varchar(9)  NOT NULL COMMENT '소속 팀 이름',
    `status`         varchar(9)  NOT NULL COMMENT '선수 상태',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT '선수 정보';

CREATE TABLE `auctions`
(
    `id`   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '옥션 id',
    `name` varchar(36) DEFAULT NULL COMMENT '옥션 이름',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT '옥션 정보';

CREATE TABLE `player_acquisitions`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '낙찰 선수 id',
    `pick_number` int        NOT NULL COMMENT '낙찰 순서',
    `auction_id`  bigint(20) DEFAULT NULL COMMENT '옥션 id',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`auction_id`) REFERENCES auctions (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT '낙찰 정보';

CREATE TABLE `bid_logs`
(
    `id`                    bigint(20) NOT NULL AUTO_INCREMENT COMMENT '로그 id',
    `price`                 int        NOT NULL COMMENT '입찰 금액',
    `user_id`               bigint(20) DEFAULT NULL COMMENT '사용자 id',
    `player_id`             bigint(20) DEFAULT NULL COMMENT '선수 id',
    `player_acquisition_id` bigint(20) DEFAULT NULL COMMENT '낙찰 선수 id',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES users (`id`),
    FOREIGN KEY (`player_id`) REFERENCES players (`id`),
    FOREIGN KEY (`player_acquisition_id`) REFERENCES player_acquisitions (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT '입찰 정보';

CREATE TABLE `user_states`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT COMMENT '사용자 상태 id',
    `budget`     int        NOT NULL COMMENT '가지고 있는 금액',
    `user_id`    bigint(20) DEFAULT NULL COMMENT '사용자 id',
    `auction_id` bigint(20) DEFAULT NULL COMMENT '옥션 id',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES users (`id`),
    FOREIGN KEY (`auction_id`) REFERENCES auctions (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT '사용자 상태';
