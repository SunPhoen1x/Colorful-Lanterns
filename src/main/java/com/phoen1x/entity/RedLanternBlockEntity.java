package com.phoen1x.entity;

import eu.pb4.factorytools.api.block.entity.LockableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;


public class RedLanternBlockEntity extends LockableBlockEntity{
    @SuppressWarnings("unchecked")
    public RedLanternBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModEntities.RED_LANTERN, blockPos, blockState);
    }
}
