package com.phoen1x.entity;

import eu.pb4.factorytools.api.block.entity.LockableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;


public class CyanLanternBlockEntity extends LockableBlockEntity{
    @SuppressWarnings("unchecked")
    public CyanLanternBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModEntities.CYAN_LANTERN, blockPos, blockState);
    }
}
