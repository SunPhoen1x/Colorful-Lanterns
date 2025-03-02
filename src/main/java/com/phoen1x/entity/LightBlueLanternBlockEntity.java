package com.phoen1x.entity;

import eu.pb4.factorytools.api.block.entity.LockableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;


public class LightBlueLanternBlockEntity extends LockableBlockEntity{
    @SuppressWarnings("unchecked")
    public LightBlueLanternBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModEntities.LIGHT_BLUE_LANTERN, blockPos, blockState);
    }
}
