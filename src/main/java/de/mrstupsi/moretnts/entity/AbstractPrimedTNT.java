package de.mrstupsi.moretnts.entity;

import de.mrstupsi.moretnts.block.AbstractTNT;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractPrimedTNT extends PrimedTnt {
    public AbstractPrimedTNT(Level world, double x, double y, double z, LivingEntity owner) {
        super(world, x, y, z, owner);
    }

    public AbstractPrimedTNT(EntityType<? extends AbstractPrimedTNT> type, Level world) {
        super(type, world);
    }

    public abstract BlockState getRenderState();
    public abstract ResourceLocation getTexture();
}