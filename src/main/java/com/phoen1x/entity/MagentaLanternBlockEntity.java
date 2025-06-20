package com.phoen1x.entity;

import eu.pb4.factorytools.api.block.entity.LockableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;


public class MagentaLanternBlockEntity extends LockableBlockEntity{
    @SuppressWarnings("unchecked")
    public MagentaLanternBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModEntities.MAGENTA_LANTERN, blockPos, blockState);
    }
}
