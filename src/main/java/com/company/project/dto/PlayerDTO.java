package com.company.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PlayerDTO {
    private String playerName;
    private Integer playerVipLevel;
    private Integer playerLevel;
    private Integer playerExp;
    private Integer playerId;
    private Integer currChapter;
    private Integer currChapterNode;
    private Integer playerEnergy;
    private Integer playerCoin;
    private Integer playerGem;
    private String playerCards;
    private String playerBags;
    private String playerCardShards;
    /**
     * 想像
     */
    private Integer thinking;
    /**
     * 创作
     */
    private Integer creation;
    /**
     * 行动
     */
    private Integer action;
    /**
     * 审美
     */
    private Integer aesthetic;
}