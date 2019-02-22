package com.company.project.service.impl;
import com.company.project.cache.PlayerCache;
import com.company.project.core.ServiceException;
import com.company.project.dto.PlayerAttributesDTO;
import com.company.project.dto.PlayerBagDTO;
import com.company.project.dto.PlayerBaseDTO;
import com.company.project.dto.PlayerCardDTO;
import com.company.project.dto.PlayerConsumeDTO;
import com.company.project.dto.PlayerDTO;
import com.company.project.dto.PlayerDramaDTO;
import com.company.project.service.PlayerService;
import com.company.project.util.BeanMapper;
import com.company.project.util.TableTools;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerCache playerCache;

    @Override
    public PlayerDTO getPlayer(Integer userId) {
        Map<String, String> playerData = playerCache.getPlayer(userId);
        return BeanMapper.map(playerData, PlayerDTO.class);
    }
    /**
     * 获取玩家信息
     * @param userId
     * @return
     */
    @Override
    public Map getPlayerInfo(Integer userId) {
        Map<String, Object> playerInfo = new HashMap<>();
        PlayerDTO player = this.getPlayer(userId);
        PlayerConsumeDTO consume = BeanMapper.map(player, PlayerConsumeDTO.class);
        PlayerDramaDTO drama = BeanMapper.map(player, PlayerDramaDTO.class);
        PlayerBaseDTO base = BeanMapper.map(player, PlayerBaseDTO.class);
        PlayerAttributesDTO attributes = BeanMapper.map(player, PlayerAttributesDTO.class);
        String cards = player.getPlayerCards();
        String bags = player.getPlayerBags();
        playerInfo.put("playerAttributes", attributes);
        playerInfo.put("playerConsum", consume);
        playerInfo.put("playerBase", base);
        playerInfo.put("playerDrama", drama);
        if (cards != null && !"".equals(cards)) {
            List<String> cardsList = Arrays.asList(cards.split(","));
            if (cardsList.size() > 0) {
                ArrayList<Map<String, String>> cardArray = new ArrayList<>();
                for (String cardId : cardsList) {
                    Map<String, String> card = playerCache.getCard(userId, Integer.parseInt(cardId));
                    if (card.size() > 0) {
                        cardArray.add(card);
                    }
                }
                if (cardArray.size() > 0) {
                    playerInfo.put("playerCardList", BeanMapper.mapList(cardArray, PlayerCardDTO.class));
                }
            }
        }
        if (bags != null && !"".equals(bags)) {
            List<String> bagsList = Arrays.asList(bags.split(","));
            if (bagsList.size() > 0) {
                ArrayList<Map<String, String>> bagArray = new ArrayList<>();
                for (String itemId : bagsList) {
                    Map<String, String> bag = playerCache.getBag(userId, Integer.parseInt(itemId));
                    if (bag.size() > 0) {
                        bagArray.add(bag);
                    }
                }
                if (bagArray.size() > 0) {
                    playerInfo.put("playerBagList", BeanMapper.mapList(bagArray, PlayerBagDTO.class));
                }
            }
        }
        return playerInfo;
    }

    /**
     * 检查副本
     * @param chapterConfig
     */
    @Override
    public void checkInstance(TableTools.chapter_config chapterConfig, PlayerDTO player) {

        //副本
        //副本ID
        Integer instanceId = chapterConfig.instance_id;
        if (instanceId <= 0) {
            throw new ServiceException("副本不存在");
        }
        //掉落副本的配置
        TableTools.draw_instance_config instanceConfig = TableTools.Get_draw_instance_config_byid(instanceId);
        if (instanceConfig == null) {
            throw new ServiceException("副本不存在");
        }
        if (instanceConfig.day_enter_num > 0) {
            //todo 进入次数处理
        }
        Integer consumeNum = instanceConfig.consum_num;
        //消耗
        switch (instanceConfig.consum_type) {
            //体力
            case 1:
                if (player.getPlayerEnergy() < consumeNum) {
                    throw new ServiceException("体力不足");
                }
                break;
            //金币
            case 2:
                if (player.getPlayerCoin() < consumeNum) {
                    throw new ServiceException("金币不足");
                }
                break;
            //宝石
            case 3:
                if (player.getPlayerGem() < consumeNum) {
                    throw new ServiceException("宝石不足");
                }
                break;
            default:
                break;
        }
    }
    /**
     * 解锁章节
     * @param userId
     * @param chapterConfig
     * @param chapter
     */
    @Override
    public void unlockChapter(Integer userId, TableTools.chapter_config chapterConfig, Integer chapter, PlayerDTO player) {
        if (chapterConfig.is_story == 1) {
            //剧情
            TableTools.story_config storyConfig = TableTools.Get_story_config_byid(chapter);
            if (storyConfig == null) {
                throw new ServiceException(String.format("不存在%s剧情", chapter));
            }
            //第一次进入剧情，掉落卡牌
            Integer cardId = storyConfig.drop_card_id;
            playerCache.addCard(userId, cardId);
        } else {
            //副本
            //副本ID
            Integer instanceId = chapterConfig.instance_id;
            if (instanceId <= 0) {
                throw new ServiceException("副本不存在");
            }
            //掉落副本的配置
            TableTools.draw_instance_config instanceConfig = TableTools.Get_draw_instance_config_byid(instanceId);
            if (instanceConfig == null) {
                throw new ServiceException("副本不存在");
            }
            if (instanceConfig.day_enter_num > 0) {
                //todo 进入次数处理
            }
            Integer consumeNum = instanceConfig.consum_num;
            //消耗
            switch (instanceConfig.consum_type) {
                //体力
                case 1:
                    if (player.getPlayerEnergy() < consumeNum) {
                        throw new ServiceException("体力不足");
                    }
                    playerCache.incrPayerEnergy(userId, -consumeNum);
                    break;
                //金币
                case 2:
                    if (player.getPlayerCoin() < consumeNum) {
                        throw new ServiceException("金币不足");
                    }
                    playerCache.incrPlayerCoin(userId, -consumeNum);
                    break;
                //宝石
                case 3:
                    if (player.getPlayerGem() < consumeNum) {
                        throw new ServiceException("宝石不足");
                    }
                    playerCache.incrPlayerGem(userId, -consumeNum);
                    break;
                default:
                    break;
            }

        }
        //最后一节：章+1 节=1
        if (chapterConfig.is_last == 1) {
            playerCache.incrPlayerChapter(userId, 1);
            playerCache.incrPlayerChapterNode(userId, 0);
        } else {
            playerCache.incrPlayerChapterNode(userId, 1);
        }
    }

    /**
     * 重复通关
     * @param chapterConfig
     * @param userId
     * @param chapter
     */
    @Override
    public void rePassChapter(Integer userId, TableTools.chapter_config chapterConfig, Integer chapter, PlayerDTO player) {

    }
}
