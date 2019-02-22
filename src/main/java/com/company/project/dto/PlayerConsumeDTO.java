package com.company.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PlayerConsumeDTO {
    private Integer playerEnergy;
    private Integer playerCoin;
    private Integer playerGem;
}
