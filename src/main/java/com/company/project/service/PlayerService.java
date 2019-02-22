package com.company.project.service;

import com.company.project.dto.PlayerDTO;
import com.company.project.util.TableTools;
import java.util.Map;

public interface PlayerService {
    PlayerDTO getPlayer(Integer userId);
    Map getPlayerInfo(Integer userId);
    void unlockChapter(Integer userId, TableTools.chapter_config chapterConfig, Integer chapter, PlayerDTO player);
    void rePassChapter(Integer userId, TableTools.chapter_config chapterConfig, Integer chapter, PlayerDTO player);
    void checkInstance(TableTools.chapter_config chapterConfig, PlayerDTO player);
}
