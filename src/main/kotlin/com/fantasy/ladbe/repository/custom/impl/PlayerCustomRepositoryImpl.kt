package com.fantasy.ladbe.repository.custom.impl

import com.fantasy.ladbe.dto.PlayerDto
import com.fantasy.ladbe.dto.QPlayerDto_Response_PlayerDetail
import com.fantasy.ladbe.model.Player
import com.fantasy.ladbe.model.QPlayer.player
import com.fantasy.ladbe.repository.custom.PlayerCustomRepository
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import javax.annotation.Resource


@Repository
class PlayerCustomRepositoryImpl(
    @Autowired
    @Resource(name = "jpaQueryFactory")
    val queryFactory: JPAQueryFactory,
) : QuerydslRepositorySupport(Player::class.java), PlayerCustomRepository {

    override fun selectById(id: Long): Player? {
        return queryFactory
            .selectFrom(player)
            .where(player.id.eq(id))
            .fetchOne()
    }

    override fun selectAll(): List<Player> {
        return queryFactory
            .selectFrom(player)
            .fetch()
    }

    override fun selectPlayersByPaging(playerPage: PlayerDto.Request.PlayerPage): Page<Player> {

        val pageRequest: PageRequest = playerPage.pageable.getPageRequest()

        val queryResults = queryFactory
            .selectFrom(player)
            .where()
            .orderBy(*getOrderSpecifier(pageRequest.sort).toTypedArray())
            .offset(pageRequest.offset)
            .limit(pageRequest.pageSize.toLong())
            .fetchResults()

        val content: List<Player> = queryResults.results
        val total = queryResults.total

        return PageImpl(content, pageRequest, total)
    }

    fun getOrderSpecifier(sort: Sort): List<OrderSpecifier<*>> {

        val orders: MutableList<OrderSpecifier<*>> = ArrayList()

        if (!sort.isEmpty) {
            for (order: Sort.Order in sort) {
                val direction: Order = if (order.direction.isAscending) Order.ASC else Order.DESC
                when (order.property) {
                    "id" -> orders.add(OrderSpecifier(direction, player.id))
                    "name" -> orders.add(OrderSpecifier(direction, player.name))
                    "position" -> orders.add(OrderSpecifier(direction, player.position))
                    "ftPct" -> orders.add(OrderSpecifier(direction, player.ftPct))
                    "fgPct" -> orders.add(OrderSpecifier(direction, player.fgPct))
                    "points" -> orders.add(OrderSpecifier(direction, player.points))
                    "rebounds" -> orders.add(OrderSpecifier(direction, player.rebounds))
                    "assists" -> orders.add(OrderSpecifier(direction, player.assists))
                    "steals" -> orders.add(OrderSpecifier(direction, player.steals))
                    "blocks" -> orders.add(OrderSpecifier(direction, player.blocks))
                    "turnOvers" -> orders.add(OrderSpecifier(direction, player.turnOvers))
                    "tripleDoubles" -> orders.add(OrderSpecifier(direction, player.tripleDoubles))
                    "status" -> orders.add(OrderSpecifier(direction, player.status))
                    "rankPre" -> orders.add(OrderSpecifier(direction, player.rankPre))
                    "rankCurrent" -> orders.add(OrderSpecifier(direction, player.rankCurrent))
                    else -> continue
                }
            }
        }
        return orders
    }
}
