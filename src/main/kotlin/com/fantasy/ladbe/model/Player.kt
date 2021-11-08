package com.fantasy.ladbe.model

import com.fantasy.ladbe.model.enumeration.PlayerStatus
import javax.persistence.*
import javax.persistence.GenerationType.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "players")
data class Player(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id : Long = 0L,
    @NotNull @Column(name = "player_name")
    var playerName : String = "",
    @NotNull @Column(name = "player_position")
    var playerPosition : String = "",
    @NotNull @Column(name = "player_three")
    var playerThree : Float = 0F,
    @NotNull @Column(name = "player_ft")
    var playerFT : Float = 0F,
    @NotNull @Column(name = "player_fg")
    var playerFG : Float = 0F,
    @NotNull @Column(name = "player_pts")
    var playerPTS : Int = 0,
    @NotNull @Column(name = "player_reb")
    var playerREB : Int = 0,
    @NotNull @Column(name = "player_ast")
    var playerAST : Int = 0,
    @NotNull @Column(name = "player_st")
    var playerST : Int = 0,
    @NotNull @Column(name = "player_blk")
    var playerBLK : Int = 0,
    @NotNull @Column(name = "player_to")
    var playerTO : Int = 0,
    @NotNull @Column(name = "player_td")
    var playerTD : Int = 0,
    @NotNull @Column(name = "player_team")
    var playerTeam : String = "",
    @NotNull @Column(name = "player_status") @Enumerated(EnumType.STRING)
    var playerStatus : PlayerStatus = PlayerStatus.HEALTH,
    @OneToMany(mappedBy = "player")
    var bidLogs : List<BidLog> = mutableListOf(),

    )
