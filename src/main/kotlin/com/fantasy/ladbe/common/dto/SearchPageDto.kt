package com.fantasy.ladbe.common.dto

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction
import org.springframework.util.ObjectUtils

class PageParam(
    val page: Int = 1,
    val size: Int = 25,
    val sort: Map<String, Direction>? = null,
) {
    fun getPageRequest(): PageRequest {
        if (ObjectUtils.isEmpty(sort))
            return PageRequest.of(page - 1, size)

        val orders: MutableList<Sort.Order> = ArrayList()

        sort?.keys?.forEach {
            val order = if (sort[it] == Direction.ASC) Sort.Order.asc(it) else Sort.Order.desc(it)
            orders.add(order)
        }
        return PageRequest.of(page - 1, size, Sort.by(orders))
    }
}
