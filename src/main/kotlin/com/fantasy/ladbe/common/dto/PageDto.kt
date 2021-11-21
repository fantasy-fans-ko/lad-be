package com.fantasy.ladbe.common.dto

import org.springframework.data.domain.Page
import java.io.Serializable

class PageDto(
    page: Page<*>,
    val content: List<*>,
) {
    val size: Int = page.size
    val page: Int = page.number + 1
    val numberOfElement: Int = page.numberOfElements
    val totalElements: Long = page.totalElements
    val totalPages: Int = page.totalPages

}