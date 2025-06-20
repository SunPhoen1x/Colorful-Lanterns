package com.phoen1x.entity;

import eu.pb4.factorytools.api.block.entity.LockableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;


public class LimeLanternBlockEntity extends LockableBlockEntity{
    @SuppressWarnings("unchecked")
    public LimeLanternBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModEntities.LIME_LANTERN, blockPos, blockState);
    }
}
