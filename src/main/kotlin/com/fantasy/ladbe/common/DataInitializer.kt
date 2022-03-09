package com.fantasy.ladbe.common

import com.fantasy.ladbe.model.Auction
import com.fantasy.ladbe.model.Bidder
import com.fantasy.ladbe.model.User
import com.fantasy.ladbe.repository.AuctionRepository
import com.fantasy.ladbe.repository.BidderRepository
import com.fantasy.ladbe.repository.PlayerRepository
import com.fantasy.ladbe.repository.UserRepository
import com.fantasy.ladbe.service.ScrapingService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DataInitializer(
    val env: Environment,
    val playerRepository: PlayerRepository,
    val userRepository: UserRepository,
    val auctionRepository: AuctionRepository,
    val bidderRepository: BidderRepository,
    val scrapingService: ScrapingService,
    @Value("\${spring.jpa.hibernate.ddl-auto}") val ddl: String,
) : CommandLineRunner {
    @Transactional
    override fun run(vararg args: String?) {
        if (env.activeProfiles.any { it == "local" } && ddl == "none") {
            createTestUser()
            createPlayer()
            createAuction()
            createBidder()
        }
    }

    private fun createTestUser() {
        if (userRepository.findById(1L).isEmpty) {
            val userList = listOf(
                User(1, 1, "FAKE_IMG1", "FAKE_EMAIL1"),
                User(2, 2, "FAKE_IMG2", "FAKE_EMAIL2"),
                User(3, 3, "FAKE_IMG3", "FAKE_EMAIL3"),
                User(4, 4, "FAKE_IMG4", "FAKE_EMAIL4"),
                User(5, 5, "FAKE_IMG5", "FAKE_EMAIL5"),
            )
            userRepository.saveAll(userList)
        }
    }

    private fun createPlayer() {
        if (playerRepository.findById(1L).isEmpty) {
            scrapingService.iterativeApproachToHtml()
        }
    }

    private fun createAuction() {
        if (auctionRepository.findById(1L).isEmpty) {
            val auction = Auction(1L, "기부 왕", 6)
            auctionRepository.save(auction)
        }
    }

    private fun createBidder() {
        if (bidderRepository.findById(1L).isEmpty) {
            val bidder = Bidder(
                1L,
                "jupaka",
                "FAKE_IMAGE_PATH",
                200,
                auctionRepository.findById(1L).get(),
                userRepository.findById(1L).get()
            )
            bidderRepository.save(bidder)
        }
    }
}
