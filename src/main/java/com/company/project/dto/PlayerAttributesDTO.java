package com.company.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PlayerAttributesDTO {
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
