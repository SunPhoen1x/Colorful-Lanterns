package com.phoen1x.entity;

import eu.pb4.factorytools.api.block.entity.LockableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;


public class OrangeLanternBlockEntity extends LockableBlockEntity{
    @SuppressWarnings("unchecked")
    public OrangeLanternBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModEntities.ORANGE_LANTERN, blockPos, blockState);
    }
}
