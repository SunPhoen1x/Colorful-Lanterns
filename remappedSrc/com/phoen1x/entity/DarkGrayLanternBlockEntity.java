package com.phoen1x.entity;

import eu.pb4.factorytools.api.block.entity.LockableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;


public class DarkGrayLanternBlockEntity extends LockableBlockEntity{
    @SuppressWarnings("unchecked")
    public DarkGrayLanternBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModEntities.DARK_GRAY_LANTERN, blockPos, blockState);
    }
}
