package com.yerti.runecraft.skills;

import com.yerti.runecraft.skills.mining.SkillMining;

public enum SkillType {

    MINING(SkillMining.class);

    Class<? extends SkillManager> manager;

    SkillType(Class<? extends SkillManager> manager) {
        this.manager = manager;
    }

    public Class<? extends SkillManager> getManager() {
        return manager;
    }
}
