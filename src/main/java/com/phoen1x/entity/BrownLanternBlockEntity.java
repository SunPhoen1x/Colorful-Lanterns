package com.phoen1x.entity;

import eu.pb4.factorytools.api.block.entity.LockableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;


public class BrownLanternBlockEntity extends LockableBlockEntity{
    @SuppressWarnings("unchecked")
    public BrownLanternBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModEntities.BROWN_LANTERN, blockPos, blockState);
    }
}
