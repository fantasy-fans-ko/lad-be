package com.fantasy.ladbe.model.abstraction

import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

// TODO : 다른 것 끝내고 테스트 하기
@MappedSuperclass
abstract class TimeEntity {
    @Column(name = "create_at")
    var createAt: ZonedDateTime? = null

    @Column(name = "modified_at")
    var modifiedAt: ZonedDateTime? = null

    @PrePersist
    fun prePersist() {
        this.createAt = ZonedDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        this.modifiedAt = ZonedDateTime.now()
    }
}
