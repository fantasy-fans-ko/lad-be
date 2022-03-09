package com.fantasy.ladbe.repository.custom.impl

import com.fantasy.ladbe.model.User
import com.fantasy.ladbe.repository.custom.UserCustomRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import javax.annotation.Resource

@Repository
class UserCustomRepositoryImpl(
    @Autowired @Resource(name = "jpaQueryFactory") val queryFactory: JPAQueryFactory,
) : QuerydslRepositorySupport(User::class.java), UserCustomRepository
