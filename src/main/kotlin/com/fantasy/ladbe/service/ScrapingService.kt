package com.fantasy.ladbe.service

import com.fantasy.ladbe.model.Player
import com.fantasy.ladbe.model.enumeration.PlayerStatus
import com.fantasy.ladbe.repository.PlayerRepository
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

const val DEFAULT_HTML_URL = "http://localhost:8080/htmlResources/"
const val DEFAULT_IMAGE_URL = "http://localhost:8080/player/"
//const val DEFAULT_IMAGE_LOCAL_PATH = "/Users/juyohan/Downloads/players2/"
const val DEFAULT_IMAGE_RESOURCES_PATH = "playerImages/"


@Service
class ScrapingService {

    @Autowired
    private lateinit var playerRepository: PlayerRepository

    @Value("classpath:/htmlResources/*.html")
    private lateinit var htmls: Array<Resource>

    fun iterativeApproachToHtml(): Unit {
        for (html: Resource in htmls) { // 여러 html들 중에서 하나씩 접근
            val document: Document = Jsoup.connect(DEFAULT_HTML_URL + html.filename).get() // 해당 html에 접근한다.
            val elements: Elements = document.select("tbody tr") // 한 줄을 가져옴

            savePlayer(elements)
        }
    }

    private fun savePlayer(elements: Elements): Unit {
        for (element: Element in elements) {
            val playerName: String = element.select("td.player a.Nowrap").text() // 선수 이름
            val playerHealth: String = element.select("td.player abbr.F-injury").text() // 선수 상태
            val teamAndPosition: List<String> = element.select("td.player span.Fz-xxs")
                .text().split(" ").toList() // 선수의 팀과 포지션
            val statsString: String = element.select("td.Ta-end").text() // 스탯 정보들 문자열로 통합
            val imageUrl = filterSameName(playerName, teamAndPosition.elementAt(0)) // 동명이인 체크
            println(imageUrl)

            if (statsString.length > 1) { // 공백 처리
                val statsValue: List<String> = statsString.split(" ") // 다시 분할
                var player: Player = Player(
                    name = playerName,
                    position = teamAndPosition.elementAt(2),
                    teamName = teamAndPosition.elementAt(0),
                    rankPre = statsValue.elementAt(1).toInt(),
                    rankCurrent = statsValue.elementAt(2).toInt(),
                    fgPct = statsValue.elementAt(6).toFloat(),
                    ftPct = statsValue.elementAt(8).replace("-", "0").toFloat(), // 값이 없다면 0으로 변경
                    threePct = statsValue.elementAt(10).replace("-", "0").toFloat(), // 값이 없다면 0으로 변경
                    points = statsValue.elementAt(11).toInt(),
                    rebounds = statsValue.elementAt(12).toInt(),
                    assists = statsValue.elementAt(13).toInt(),
                    steals = statsValue.elementAt(14).toInt(),
                    blocks = statsValue.elementAt(15).toInt(),
                    turnOvers = statsValue.elementAt(16).toInt(),
                    tripleDoubles = statsValue.elementAt(17).toInt(),
                    status = checkHealth(playerHealth), // 선수 상태 저장하는 함수
                    imageUrl = "$DEFAULT_IMAGE_URL$imageUrl.png"
                )
                playerRepository.save(player);
            }
        }
    }


    // 동명이인 확인
    private fun filterSameName(playerName: String, teamName: String): String {
        val afterPlayerName = playerName.replace(" ", "_").replace(".", "") // 파일에 접근할 수 있게 이름 변경

        // local에 접근
//        val file = File("$DEFAULT_IMAGE_LOCAL_PATH$afterPlayerName.png")
//        if (!file.exists()) { // 동명이인이 있다면
//            return afterPlayerName + "_" + teamName // 뒤에 팀 이름까지 붙여줌
//        }

        // class resource에 접근
        val resource = ClassPathResource("$DEFAULT_IMAGE_RESOURCES_PATH$afterPlayerName.png")
        if (!resource.exists()) // 동명이인이 있다면
            return afterPlayerName + "_" + teamName // 선수이름 뒤에 팀 이름까지 붙여줌
        return afterPlayerName // 아니라면 선수이름만 반환
    }

    // 선수의 상태
    private fun checkHealth(playerHealth: String) =
        when (playerHealth) {
            "GTD" -> PlayerStatus.GAME_TIME_DECISION
            "O" -> PlayerStatus.OUT
            "INJ" -> PlayerStatus.INJURED
            else -> PlayerStatus.HEALTHY
        }

}
