package com.yerti.runecraft.utils;

import net.minecraft.server.v1_8_R3.Items;
import net.minecraft.server.v1_8_R3.Item;

import java.lang.reflect.Field;

public class StackChanger {

    public StackChanger() {
        sm(Items.LAVA_BUCKET, 69);
        sm(Items.WOODEN_PICKAXE, 20);
        sm(Items.DIAMOND, 3);
        sm(Items.GOLDEN_SWORD, 120);
        sm(Items.APPLE, 1);

    }

    public void sm(Item item, int i){
        try {
            Field field = Item.class.getDeclaredField("maxStackSize");
            field.setAccessible(true);
            field.setInt(item, i);
        } catch (Exception e) {}
    }


}
