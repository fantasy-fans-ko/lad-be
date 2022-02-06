drop table `bid_logs`;

CREATE TABLE `bid_logs`
(
    `id`                    bigint NOT NULL AUTO_INCREMENT COMMENT '로그 id',
    `price`                 int    NOT NULL COMMENT '입찰 금액',
    `bidder_id`             bigint DEFAULT NULL COMMENT '입찰자 id',
    `player_id`             bigint DEFAULT NULL COMMENT '선수 id',
    `auction_id`            bigint DEFAULT NULL COMMENT '방 id',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`bidder_id`) REFERENCES bidders (`id`),
    FOREIGN KEY (`player_id`) REFERENCES players (`id`),
    FOREIGN KEY (`auction_id`) REFERENCES auctions (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER
      SET = utf8mb4
    COMMENT
        '입찰 정보';

alter table `player_acquisitions`
    add `bidder_id` bigint default NULL COMMENT '낙찰한 입찰자 id';
alter table `player_acquisitions`
    add `bid_log_id` bigint default NULL COMMENT '낙찰된 입찰 정보 id';
alter table `player_acquisitions`
    add `price` int not null COMMENT '낙찰 금액';

alter table `player_acquisitions`
    add foreign key (`bidder_id`) references bidders (`id`);
alter table `player_acquisitions`
    add foreign key (`bid_log_id`) references bid_logs (`id`);

alter table `auctions`
    add `user_id` bigint default NULL COMMENT '생성한 유저 id';
alter table `auctions`
    add foreign key (`user_id`) references users (`id`);


