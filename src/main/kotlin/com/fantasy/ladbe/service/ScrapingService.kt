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

const val DEFAULT_HTML_PATH = "http://localhost:8080/htmlResources/"
const val DEFAULT_IMAGE_PATH = "http://localhost:8080/player/"

// const val DEFAULT_IMAGE_LOCAL_PATH = "/Users/juyohan/Downloads/players2/"
const val DEFAULT_IMAGE_RESOURCES_PATH = "playerImages/"

@Service
class ScrapingService(
    @Autowired
    val playerRepository: PlayerRepository,
) {

    @Value("classpath:/htmlResources/*.html")
    private lateinit var htmls: Array<Resource>

    /**
     * resources 파일에 있는 html의 정보들을 계속 돌려가며 접근하기 위한 함수
     */
    fun iterativeApproachToHtml() {
        for (html: Resource in htmls) { // 여러 html들 중에서 하나씩 접근
            val document: Document = Jsoup.connect(DEFAULT_HTML_PATH + html.filename).get() // 해당 html에 접근한다.
            val elements: Elements = document.select("tbody tr") // 한 줄을 가져옴

            savePlayer(elements)
        }
    }

    /**
     * 파싱한 뒤 선수의 정보를 저장하는 함수
     * @param elements html 의 내용
     */
    private fun savePlayer(elements: Elements) {
        for (element: Element in elements) {
            val playerName: String = element.select("td.player a.Nowrap").text() // 선수 이름
            val status: String = element.select("td.player abbr.F-injury").text() // 선수 상태
            // 선수의 팀과 포지션
            val teamAndPosition: List<String> = element.select("td.player span.Fz-xxs")
                .text().split(" ").toList()
            val imagePath = filterSameName(playerName, teamAndPosition.elementAt(0)) // 동명이인 체크
            // 스탯의 값을 분할한 뒤, 값이 없는 경우(-) 0으로 변경
            val statList: List<String> = element.select("td.Ta-end").text().split(" ")
                .map { str: String -> str.replace("-", "0") }

            Player(
                name = playerName,
                teamName = teamAndPosition.elementAt(0),
                position = teamAndPosition.elementAt(2),
                rankPre = statList.elementAt(1).toInt(),
                rankCurrent = statList.elementAt(2).toInt(),
                fgPct = statList.elementAt(6).toFloat(),
                ftPct = statList.elementAt(8).toFloat(),
                threePct = statList.elementAt(10).toFloat(),
                points = statList.elementAt(11).toInt(),
                rebounds = statList.elementAt(12).toInt(),
                assists = statList.elementAt(13).toInt(),
                steals = statList.elementAt(14).toInt(),
                blocks = statList.elementAt(15).toInt(),
                turnOvers = statList.elementAt(16).toInt(),
                tripleDoubles = statList.elementAt(17).toInt(),
                status = playerStatusOf(status),
                imagePath = "$DEFAULT_IMAGE_PATH$imagePath.png"
            ).let {
                playerRepository.save(it)
            }
        }
    }

    /**
     * 동명이인이 있는지 확인을 한 뒤, 있다면 이름 뒤에 팀을 붙여줌
     * @param playerName 선수 이름
     * @param teamName 팀 이름
     * @return 가공을 한 뒤, 문자열을 반환합니다.
     */
    private fun filterSameName(playerName: String, teamName: String): String {
        /**
         * 파일명을 접근할 수 있는 이름으로 변경
         * Example) A. Davis -> A_Davis
         **/
        val afterPlayerName = playerName.replace(" ", "_").replace(".", "")

        // local에 접근
//        val file = File("$DEFAULT_IMAGE_LOCAL_PATH$afterPlayerName.png")
//        if (!file.exists()) { // 동명이인이 있다면
//            return afterPlayerName + "_" + teamName // 뒤에 팀 이름까지 붙여줌
//        }

        // class resource 파일에 접근
        val resource = ClassPathResource("$DEFAULT_IMAGE_RESOURCES_PATH$afterPlayerName.png")

        if (!resource.exists()) // 동명이인이 있다면
            return afterPlayerName + "_" + teamName // 선수이름 뒤에 팀 이름까지 붙여줌

        return afterPlayerName // 아니라면 선수이름만 반환
    }

    /**
     * 선수의 상태를 확인하는 함수
     * @param status 선수의 현재 상태
     * @return PlayerStatus Enum에 알맞는 값 반환
     */
    private fun playerStatusOf(status: String) =
        when (status) {
            "GTD" -> PlayerStatus.GAME_TIME_DECISION
            "O" -> PlayerStatus.OUT
            "INJ" -> PlayerStatus.INJURED
            else -> PlayerStatus.HEALTHY
        }
}
