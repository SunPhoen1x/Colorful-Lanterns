package com.phoen1x.entity;

import eu.pb4.factorytools.api.block.entity.LockableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;


public class LightGrayLanternBlockEntity extends LockableBlockEntity{
    @SuppressWarnings("unchecked")
    public LightGrayLanternBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModEntities.LIGHT_GRAY_LANTERN, blockPos, blockState);
    }
}
