package de.mrstupsi.moretnts.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTNT extends TntBlock {
    public static final List<AbstractTNT> TNTS = new ArrayList<>();
    public AbstractTNT() {
        super(Properties.of(Material.EXPLOSIVE).instabreak().sound(SoundType.GRASS));
        TNTS.add(this);
    }

    public abstract void explode(Level world, BlockPos pos, LivingEntity owner);
    public abstract void exploded(Level world, BlockPos pos, Explosion explosion);

    @Override
    public void onCaughtFire(BlockState state, Level world, BlockPos pos, Direction face, LivingEntity owner) {
        if (!world.isClientSide) explode(world, pos, owner);
    }

    @Override
    public void wasExploded(Level world, BlockPos pos, Explosion explosion) {
        if (!world.isClientSide) exploded(world, pos, explosion);
    }
}