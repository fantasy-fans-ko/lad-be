ALTER TABLE `users`
    MODIFY `kakao_code` varchar(256) NOT NULL COMMENT '카카오에서 받은 사용자 고유번호';
ALTER TABLE `users`
    MODIFY `kakao_image_path` varchar(256) NOT NULL COMMENT '카카오에서 받은 프로필 사진 경로';
ALTER TABLE `users`
    MODIFY `kakao_email` varchar(256) NOT NULL COMMENT '카카오에서 받은 email';

ALTER TABLE `players`
    MODIFY `name` varchar(128) NOT NULL COMMENT '선수 이름';
ALTER TABLE `players`
    MODIFY `position` varchar(16) NOT NULL COMMENT '선수 포지션';
ALTER TABLE `players`
    MODIFY `team_name` varchar(16) NOT NULL COMMENT '소속 팀 이름';
ALTER TABLE `players`
    MODIFY `status` varchar(64) NOT NULL COMMENT '선수 상태';

ALTER TABLE `auctions`
    MODIFY `name` varchar(128) DEFAULT NULL COMMENT '옥션 이름';

ALTER TABLE `bidders`
    MODIFY `nickname` varchar(128) NOT NULL COMMENT '입찰자 닉네임';
