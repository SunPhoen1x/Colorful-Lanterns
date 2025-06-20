package com.phoen1x.entity;

import eu.pb4.factorytools.api.block.entity.LockableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;


public class PinkLanternBlockEntity extends LockableBlockEntity{
    @SuppressWarnings("unchecked")
    public PinkLanternBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModEntities.PINK_LANTERN, blockPos, blockState);
    }
}
