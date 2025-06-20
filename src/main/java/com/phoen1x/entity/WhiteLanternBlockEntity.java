package com.phoen1x.entity;

import eu.pb4.factorytools.api.block.entity.LockableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;


public class WhiteLanternBlockEntity extends LockableBlockEntity{
    @SuppressWarnings("unchecked")
    public WhiteLanternBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModEntities.WHITE_LANTERN, blockPos, blockState);
    }
}
