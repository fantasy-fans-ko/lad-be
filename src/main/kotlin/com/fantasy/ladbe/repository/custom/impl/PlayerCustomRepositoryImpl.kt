package com.fantasy.ladbe.repository.custom.impl

import com.fantasy.ladbe.dto.PlayerDto
import com.fantasy.ladbe.model.Player
import com.fantasy.ladbe.model.QPlayer.*
import com.fantasy.ladbe.dto.QPlayerDto_Response_PlayerDetail
import com.fantasy.ladbe.repository.custom.PlayerCustomRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import javax.annotation.Resource


@Repository
class PlayerCustomRepositoryImpl(
    @Autowired
    @Resource(name = "jpaQueryFactory")
    val queryFactory: JPAQueryFactory,
) : QuerydslRepositorySupport(Player::class.java), PlayerCustomRepository{

    override fun selectById(id: Long): Player? {
        return queryFactory.selectFrom(player).where(player.id.eq(id)).fetchOne()
    }

    override fun selectAll(): MutableList<Player>? {
        return queryFactory.selectFrom(player).fetch()
    }

    override fun selectPlayersByPaging(playerPage: PlayerDto.Request.PlayerPage): Page<PlayerDto.Response.PlayerDetail>? {

        val pageRequest = playerPage.pageable.getPageRequest()

        val queryResults = queryFactory.select(
            QPlayerDto_Response_PlayerDetail(
            player.id,
            player.name,
            player.position,
            player.threePct,
            player.ftPct,
            player.fgPct,
            player.points,
            player.rebounds,
            player.assists,
            player.steals,
            player.blocks,
            player.turnOvers,
            player.tripleDoubles,
            player.teamName,
            player.status,
            player.imageUrl,
            player.rankPre,
            player.rankCurrent
        ))
            .from(player)
            .where()
            .offset(pageRequest.offset)
            .limit(pageRequest.pageSize.toLong())
            .fetchResults()
//            .orderBy(getOrderSpecifier(pageRequest.sort))
        val content: List<PlayerDto.Response.PlayerDetail> = queryResults.results
        val total = queryResults.total

        return PageImpl(content,pageRequest,total)

//


    }

//    fun getOrderSpecifier(sort: Sort): List<OrderSpecifier<*>>? {
//        val orders: MutableList<OrderSpecifier<*>> = ArrayList()
//
//        //Id 역순 정렬
//        if (sort.isEmpty) {
//            orders.add(OrderSpecifier(Order.DESC, player.id))
//        }
//
//        sort.stream().forEach { order ->
//            val direction: Order =
//                if (order.isAscending) Order.ASC else Order.DESC
//            val prop = order.property
//            orders.add(getSortColumn(direction, player, prop))
//        }
//
//        return orders
//    }
//
//    fun getSortColumn(order: Order, path: Path<*>, fieldName: String): OrderSpecifier<*> {
//        val fieldPath: Path<Any> = Expressions.path(Any::class.java, path, fieldName)
//        return OrderSpecifier(order, fieldPath as)
//    }


}