package com.fantasy.ladbe.repository.custom.impl

import com.fantasy.ladbe.model.Player
import com.fantasy.ladbe.model.QPlayer
import com.fantasy.ladbe.repository.custom.PlayerCustomRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource


@Repository
class PlayerCustomRepositoryImpl(
    @Autowired
    @Resource(name = "jpaQueryFactory")
    val query: JPAQueryFactory,
) : PlayerCustomRepository {


    override fun selectAll(): List<Player> = query.selectFrom(QPlayer.player).fetch()



}