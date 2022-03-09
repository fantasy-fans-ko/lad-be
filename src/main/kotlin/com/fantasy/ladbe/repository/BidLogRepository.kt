package com.fantasy.ladbe.repository

import com.fantasy.ladbe.model.BidLog
import org.springframework.data.jpa.repository.JpaRepository

interface BidLogRepository : JpaRepository<BidLog, Long>
