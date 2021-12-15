package com.fantasy.ladbe.common

import com.fantasy.ladbe.model.User
import com.fantasy.ladbe.repository.PlayerRepository
import com.fantasy.ladbe.repository.UserRepository
import com.fantasy.ladbe.service.ScrapingService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.ObjectUtils
import java.util.*

@Component
class DataInitializer
    (
    val env: Environment,
    val playerRepository: PlayerRepository,
    val userRepository: UserRepository,
    val scrapingService: ScrapingService,
    @Value("\${spring.jpa.hibernate.ddl-auto}") val ddl: String,
    ) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {
        if (Arrays.stream(env.activeProfiles).anyMatch("local"::equals) && ddl == "none"){
            createTestUser()
            createPlayer()
        }
    }

    private fun createTestUser() {
        if (ObjectUtils.isEmpty(userRepository.findById(1L))) {
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
        if (ObjectUtils.isEmpty(playerRepository.findById(1L))) {
            scrapingService.iterativeApproachToHtml()
        }
    }
}
