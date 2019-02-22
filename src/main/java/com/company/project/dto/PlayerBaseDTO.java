package com.company.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PlayerBaseDTO {
    private String playerName;
    private Integer playerVipLevel;
    private Integer playerLevel;
    private Integer playerExp;
    private Integer playerId;

}
