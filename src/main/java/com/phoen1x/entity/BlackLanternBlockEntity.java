package com.phoen1x.entity;

import eu.pb4.factorytools.api.block.entity.LockableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;


public class BlackLanternBlockEntity extends LockableBlockEntity{
    @SuppressWarnings("unchecked")
    public BlackLanternBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModEntities.BLACK_LANTERN, blockPos, blockState);
    }
}
