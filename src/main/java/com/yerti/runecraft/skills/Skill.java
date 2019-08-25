package com.yerti.runecraft.skills;

import com.yerti.runecraft.core.player.RunePlayer;

import java.math.BigDecimal;

public interface Skill {

    void setXP(RunePlayer player);

    void addXP(RunePlayer player);

    String getName();
}
