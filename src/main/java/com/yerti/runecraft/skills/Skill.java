package com.yerti.runecraft.skills;

public abstract class Skill {

    SkillType type;

    public Skill(SkillType type) {
        this.type = type;
    }

    public abstract String getName();
}
