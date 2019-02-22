package com.conpany.project;

import com.company.project.cache.PlayerCache;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerTest extends Tester {
    @Autowired
    private PlayerCache playerCache;

    public final static Integer userId = 12;

    @Test
    public void player() {
        //玩家缓存初始化
        playerCache.addPlayer(userId);
        playerCache.updateNickname(userId, "小强");
        playerCache.incrPayerEnergy(userId, 1);
        playerCache.incrPlayerCoin(userId, -1);
        playerCache.incrPlayerExp(userId, 1);
        playerCache.incrPlayerGem(userId, 1);
        playerCache.incrPlayerVipLevel(userId, 1);
        playerCache.incrPlayerLevel(userId, 1);
        playerCache.addCard(userId, 1);
        playerCache.incrCardExp(userId, 1, 1);
        playerCache.incrCardLevel(userId, 1, 1);
        playerCache.addCard(userId, 3);
        playerCache.incrCardExp(userId, 3, 1);
        playerCache.incrCardLevel(userId, 3, 1);
        playerCache.addBag(userId, 1002, 1);
    }
}
