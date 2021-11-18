ALTER TABLE `players` ADD `rank_pre` int NOT NULL COMMENT '이전 랭킹';
ALTER TABLE `players` ADD `rank_current` int NOT NULL COMMENT '현재 랭킹';
ALTER TABLE `players` ADD `image_url` varchar(255) NOT NULL COMMENT '이전 랭킹';
