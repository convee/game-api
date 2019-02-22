package com.company.project.web;

import com.company.project.authorization.manager.TokenManager;

import com.company.project.cache.PlayerCache;
import com.company.project.core.ResultCode;
import com.company.project.core.Ret;
import com.company.project.core.ServiceException;
import com.company.project.dto.PlayerAttributesDTO;
import com.company.project.dto.PlayerDTO;
import com.company.project.service.PlayerService;
import com.company.project.service.TableService;
import com.company.project.util.BeanMapper;
import com.company.project.util.FuncHelper;
import com.company.project.util.TableTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 玩家信息控制器
 */
@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerCache playerCache;
    @Resource
    private TokenManager tokenManager;
    @Resource
    private TableService tableService;

    @Resource
    private PlayerService playerService;

    /**
     * 更新玩家昵称
     * @param token
     * @param name
     * @return
     */
    @GetMapping("updateName")
    public Object updateName(@RequestParam String token, @RequestParam Integer userId, @RequestParam String name) {
        tokenManager.checkLogin(token, userId);
        if (playerCache.checkNickname(name)) {
            return (new Ret()).setOption("code", ResultCode.NICKNAME_EXISTS.code).getResult();
        }
        playerCache.updateNickname(userId, name);
        return (new Ret()).getResult();
    }

    @GetMapping("getAttributes")
    public Object getPlayerAttributes(@RequestParam String token, @RequestParam Integer userId) {
        tokenManager.checkLogin(token, userId);
        PlayerDTO player = playerService.getPlayer(userId);
        PlayerAttributesDTO attributes = BeanMapper.map(player, PlayerAttributesDTO.class);
        return (new Ret()).setOption("playerAttributes", attributes).getResult();
    }

    @GetMapping("addBagItem")
    public Object addBagItem(@RequestParam String token, @RequestParam Integer userId, @RequestParam Integer itemId, Integer itemNum) {
        tokenManager.checkLogin(token, userId);
        playerCache.addBag(userId, itemId, itemNum);
        return (new Ret()).getResult();
    }

    @GetMapping("accessInstance")
    public Object accessInstance(@RequestParam String token, @RequestParam Integer userId, @RequestParam Integer chapter) {
        tokenManager.checkLogin(token, userId);
        //玩家信息
        PlayerDTO player = BeanMapper.map(playerCache.getPlayer(userId), PlayerDTO.class);
        //初始化配置表
        tableService.initTable();

        //格式化当前章节
        Integer currentChapter = FuncHelper.formatChapter(player.getCurrChapter(), player.getCurrChapterNode());
        //章节配置
        TableTools.chapter_config chapterConfig = TableTools.Get_chapter_config_byid(chapter);
        if (chapterConfig == null) {
            throw new ServiceException(String.format("不存在%s章节", chapter));
        }

        //通过章节不能大于当前章节
        if (chapter > currentChapter) {
            throw new ServiceException("章节错误");
        }
        //检查玩家属性
        playerService.checkInstance(chapterConfig, player);
        return (new Ret()).getResult();
    }

    public void chooseScene(@RequestParam String token, @RequestParam Integer userId, @RequestParam Integer chapter) {}
    /**
     * 剧情则自动解锁，副本则需要达到一颗星才可解锁下一个
     * @param token 登录态
     * @param userId 用户id
     * @param chapter 当前通过的章节
     * @return
     */
    @GetMapping("passChapter")
    public Object passChapter(@RequestParam String token, @RequestParam Integer userId, @RequestParam Integer chapter, @RequestParam String cardIds) {
        tokenManager.checkLogin(token, userId);
        //玩家信息
        PlayerDTO player = BeanMapper.map(playerCache.getPlayer(userId), PlayerDTO.class);

        //初始化配置表
        tableService.initTable();

        //格式化当前章节
        Integer currentChapter = FuncHelper.formatChapter(player.getCurrChapter(), player.getCurrChapterNode());

        //章节配置
        TableTools.chapter_config chapterConfig = TableTools.Get_chapter_config_byid(chapter);
        if (chapterConfig == null) {
            throw new ServiceException(String.format("不存在%s章节", chapter));
        }

        //通过章节不能大于当前章节
        if (chapter > currentChapter) {
            throw new ServiceException("章节错误");
        }

        //玩家等级未达到，拒绝解锁下一节
        if (player.getPlayerLevel() < chapterConfig.player_level) {
            throw new ServiceException(String.format("玩家%s等级未达到", userId));
        }

        //解锁章节，副本一颗星以上才能解锁下一个节点，剧情自动解锁
        if (currentChapter.equals(chapter)) {
            playerService.unlockChapter(userId, chapterConfig, chapter, player);
        }

        //重复通过章节
        if (chapter < currentChapter) {
            playerService.rePassChapter(userId, chapterConfig, chapter, player);
        }


        return (new Ret()).getResult();
    }

    /**
     * 卡牌升级
     *
     * @param token
     * @param userId
     * @param cardId
     * @return
     */
    public Object cardUp(@RequestParam String token, @RequestParam Integer userId, @RequestParam Integer cardId) {

    }
}
