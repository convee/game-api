package com.company.project.cache;

import com.company.project.util.DateTools;
import java.util.HashMap;
import java.util.Map;
import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerCache {

    @Autowired
    private SSDB ssdb;

    public final static String ACCOUNT_PRE = "account:";
    public final static String ACCOUNT_DID_PRE = "account:did:";
    public final static String PLAYER_PRE = "player:";
    public final static String PLAYER_NAME_PRE = "player:name:";
    public final static String PLAYER_BAG_PRE = "bag:";
    public final static String PLAYER_CARD_PRE = "card:";
    public final static String PLAYER_SHARD_PRE = "shard:";
    public final static String PLAYER_SCORE_PRE = "score:";
    public final static String PLAYER_CHAPTER_PRE = "chapter:";

    public void addPlayer(Integer userId) {
        Map map = new HashMap<>();
        map.put("playerName", "");
        map.put("playerId", userId);
        map.put("playerVipLevel", 0);
        map.put("playerLevel", 1);
        map.put("playerExp", 0);

        map.put("playerEnergy", 0);
        map.put("playerCoin", 0);
        map.put("playerGem", 0);

        map.put("currChapter", 1);
        map.put("currChapterNode", 1);

        map.put("thinking", 0);
        map.put("creation", 0);
        map.put("action", 0);
        map.put("aesthetic", 0);

        map.put("playerBags", "");
        map.put("playerCards", "");
        map.put("playerCardShards", "");
        ssdb.multi_hset(PLAYER_PRE + userId, map);
    }

    public void createAccount(Integer userId, String did) {
        ssdb.set(ACCOUNT_DID_PRE + did, userId);
        ssdb.hset(ACCOUNT_PRE + userId, "did", did);
        ssdb.hset(ACCOUNT_PRE + userId, "createTime", DateTools.timeStamp());
    }

    public Integer incrUserId() {
        Response resp = ssdb.incr(ACCOUNT_PRE + "autoId", 1);
        if (resp.ok()) {
            return resp.asInt();
        }
        return null;
    }

    public Map<String, String> getAccount(Integer userId) {
        Response resp = ssdb.hgetall(ACCOUNT_PRE + userId);
        if (resp.ok()) {
            return resp.mapString();
        }
        return null;
    }

    /**
     * did获取uid
     * @param did
     * @return
     */
    public Integer getUserIdByDid(String did) {
        Response resp = ssdb.get(ACCOUNT_DID_PRE + did);
        if (resp.ok()) {
            return resp.asInt();
        }
        return null;
    }

    public Boolean checkUid(Integer userId) {
        Response resp = ssdb.exists(ACCOUNT_PRE + userId);
        if (resp.ok() && resp.asInt() > 0) {
            return true;
        }
        return false;
    }
    /**
     * 根据uid获取玩家信息
     * @param userId
     * @return
     */
    public Map<String, String> getPlayer(Integer userId) {
        Response resp = ssdb.hgetall(PLAYER_PRE + userId);
        if (resp.ok()) {
            return resp.mapString();
        }
        return null;
    }

    /**
     * 获取玩家副本分数
     * @param userId
     * @param chapter
     * @return
     */
    public Integer getInstanceScore(Integer userId, Integer chapter) {
        Response resp = ssdb.hget(PLAYER_SCORE_PRE + userId, chapter);
        if (resp.ok()) {
            return resp.asInt();
        }
        return null;
    }

    /**
     * 副本分数更新
     * @param userId
     * @param chapter
     * @param score
     */
    public void updateInstanceScore(Integer userId, Integer chapter, Integer score) {
        ssdb.hset(PLAYER_SCORE_PRE + userId, chapter, score);
    }

    public Boolean checkNickname(String nickname) {
        Response resp = ssdb.exists(PLAYER_NAME_PRE + nickname);
        if (resp.ok() && resp.asInt() > 0) {
            return true;
        }
        return false;
    }
    /**
     * 更新昵称
     * @param userId
     * @param nickname
     */
    public void updateNickname(Integer userId, String nickname) {
        ssdb.set(PLAYER_NAME_PRE + nickname, userId);
        ssdb.hset(PLAYER_PRE + userId, "playerName", nickname);
    }

    /**
     * 更新vip等级
     * @param userId
     * @param num
     */
    public void incrPlayerVipLevel(Integer userId, Integer num) {
        ssdb.hincr(PLAYER_PRE + userId, "playerVipLevel", num);
    }

    /**
     * 更新玩家等级
     * @param userId
     * @param num
     */
    public void incrPlayerLevel(Integer userId, Integer num) {
        ssdb.hincr(PLAYER_PRE + userId, "playerLevel", num);
    }

    /**
     * 更新玩家经验
     * @param userId
     * @param num
     */
    public void incrPlayerExp(Integer userId, Integer num) {
        ssdb.hincr(PLAYER_PRE + userId, "playerExp", num);
    }

    /**
     * 更新玩家体力
     * @param userId
     * @param num
     */
    public void incrPayerEnergy(Integer userId, Integer num) {
        ssdb.hincr(PLAYER_PRE + userId, "playerEnergy", num);
    }

    /**
     * 更新玩家金币数量
     * @param userId
     * @param num
     */
    public void incrPlayerCoin(Integer userId, Integer num) {
        ssdb.hincr(PLAYER_PRE + userId, "playerCoin", num);
    }

    /**
     * 更新玩家宝石数量
     * @param userId
     * @param num
     */
    public void incrPlayerGem(Integer userId, Integer num) {
        ssdb.hincr(PLAYER_PRE + userId, "playerGem", num);
    }

    /**
     * 更新玩家章
     * @param userId
     * @param num
     */
    public void incrPlayerChapter(Integer userId, Integer num) {
        ssdb.hincr(PLAYER_PRE + userId, "currChapter", num);
    }

    /**
     * 更新玩家节
     * @param userId
     * @param num
     */
    public void incrPlayerChapterNode(Integer userId, Integer num) {
        if (num == 0) {
            ssdb.hincr(PLAYER_PRE + userId, "currChapterNode", 1);
        } else {
            ssdb.hincr(PLAYER_PRE + userId, "currChapterNode", num);
        }
    }
    /**
     * 获取背包所有物品+数量
     * @param userId
     * @return
     */
    public Map<String, String> getBag(Integer userId) {
        Response resp = ssdb.hgetall(PLAYER_BAG_PRE + userId);
        if (resp.ok()) {
            return resp.mapString();
        }
        return null;
    }

    /**
     * 获取背包所有物品+数量
     * @param userId
     * @return
     */
    public Map<String, String> getBag(Integer userId, Integer itemId) {
        Response resp = ssdb.hgetall(PLAYER_BAG_PRE + userId + ":" + itemId);
        if (resp.ok()) {
            return resp.mapString();
        }
        return null;
    }

    /**
     * 添加背包物品
     * @param userId
     * @param itemId
     * @param itemNum
     * @return
     */
    public void addBag(Integer userId, Integer itemId, Integer itemNum) {
        ssdb.hset(PLAYER_BAG_PRE + userId, itemId, 1);
        ssdb.hset(PLAYER_BAG_PRE + userId + ":" + itemId, "itemId", itemId);
        ssdb.hset(PLAYER_BAG_PRE + userId + ":" + itemId, "itemNum", itemNum);
    }

    /**
     * 增加物品数量
     * @param userId
     * @param itemId
     * @param itemNum
     */
    public void incrBag(Integer userId, Integer itemId, Integer itemNum) {
        ssdb.incr(PLAYER_BAG_PRE + userId + ":" + itemId, itemNum);
    }
    /**
     * 获取用户所有卡片
     * @param userId
     * @return
     */
    public String getCard(Integer userId) {
        Response resp = ssdb.hget(PLAYER_PRE + userId, "playerCards");
        if (resp.ok()) {
            return resp.asString();
        }
        return null;
    }

    /**
     * 获取用户卡片
     * @param userId
     * @param cardId
     * @return
     */
    public Map<String, String> getCard(Integer userId, Integer cardId) {
        Response resp = ssdb.hgetall(PLAYER_CARD_PRE + userId + ":" + cardId);
        if (resp.ok()) {
            return resp.mapString();
        }
        return null;
    }

    /**
     * 添加卡片
     * @param userId
     * @param cardId
     * @return
     */
    public void addCard(Integer userId, Integer cardId) {
        String newCards;
        String currCards = this.getCard(userId);
        if ("".equals(currCards)) {
            newCards = cardId.toString();
        } else {
            newCards = currCards + "," + cardId.toString();
        }
        ssdb.hset(PLAYER_PRE + userId, "playerCards", newCards);
        ssdb.hset(PLAYER_CARD_PRE + userId + ":" + cardId, "cardId", cardId);
    }

    /**
     * 增加卡片经验
     * @param userId
     * @param cardId
     * @param cardExp
     */
    public void incrCardExp(Integer userId, Integer cardId, Integer cardExp) {
        ssdb.hincr(PLAYER_CARD_PRE + userId + ":" + cardId, "cardExp", cardExp);

    }

    /**
     * 增加卡片等级
     * @param userId
     * @param cardId
     * @param cardLevel
     */
    public void incrCardLevel(Integer userId, Integer cardId, Integer cardLevel) {
        ssdb.hincr(PLAYER_CARD_PRE + userId + ":" + cardId, "cardLevel", cardLevel);
    }

    /**
     * 获取所有碎片
     * @param userId
     * @return
     */
    public Map<String, String> getShard(Integer userId) {
        Response resp = ssdb.hgetall(PLAYER_SHARD_PRE + userId);
        if (resp.ok()) {
            return resp.mapString();
        }
        return null;
    }

    /**
     * 获取碎片
     * @param userId
     * @param cardId
     * @return
     */
    public Map<String, String> getShard(Integer userId, Integer cardId) {
        Response resp = ssdb.hget(PLAYER_SHARD_PRE + userId, cardId);
        if (resp.ok()) {
            return resp.mapString();
        }
        return null;
    }

    /**
     * 获取玩家历史章节信息：星级， 得分
     * @param userId
     * @param chapter
     * @return
     */
    public String getChapterInfo(Integer userId, Integer chapter) {
        Response resp = ssdb.hget(PLAYER_CHAPTER_PRE + userId, chapter);
        if (resp.ok()) {
            return resp.asString();
        }
        return null;
    }

    /**
     * 更新玩家历史章节信息：星级，得分
     * @param userId
     * @param chapter
     * @param info
     */
    public void updateChapterInfo(Integer userId, Integer chapter, String info) {
        ssdb.hset(PLAYER_CHAPTER_PRE + userId, chapter, info);
    }
}
