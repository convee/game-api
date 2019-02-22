package com.company.project.cache;

import org.nutz.ssdb4j.spi.SSDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagCache {

    @Autowired
    private SSDB ssdb;
    public final static String PLAYER_CARD_PRE = "card:";

}
