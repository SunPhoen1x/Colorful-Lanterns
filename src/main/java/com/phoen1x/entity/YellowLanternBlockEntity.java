package com.phoen1x.entity;

import eu.pb4.factorytools.api.block.entity.LockableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;


public class YellowLanternBlockEntity extends LockableBlockEntity{
    @SuppressWarnings("unchecked")
    public YellowLanternBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModEntities.YELLOW_LANTERN, blockPos, blockState);
    }
}
