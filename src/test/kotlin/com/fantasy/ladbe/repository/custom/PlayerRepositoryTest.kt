package com.fantasy.ladbe.repository.custom

import com.fantasy.ladbe.model.Player
import com.fantasy.ladbe.repository.PlayerRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class PlayerRepositoryTest @Autowired constructor(
    val playerRepository: PlayerRepository
) {
    @BeforeEach
    fun beforeEach() {
        playerRepository.deleteAll()

        val playerList: List<Player> = listOf(
            Player(name = "김동규"), Player(name = "주요한")
        )
        playerRepository.saveAll(playerList)
    }

    @Test
    @DisplayName("전체 조회 테스트")
    fun searchAllTest() {
        // given

        // when
//        val list = playerRepository.selectAll()

        // then
//        assertEquals(list?.size, 2)
    }
}
