package com.phoen1x.entity;

import eu.pb4.factorytools.api.block.entity.LockableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;


public class PurpleLanternBlockEntity extends LockableBlockEntity{
    @SuppressWarnings("unchecked")
    public PurpleLanternBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModEntities.PURPLE_LANTERN, blockPos, blockState);
    }
}
