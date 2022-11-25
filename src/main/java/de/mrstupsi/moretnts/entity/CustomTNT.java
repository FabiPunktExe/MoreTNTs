package de.mrstupsi.moretnts.entity;

import de.mrstupsi.moretnts.MoreTNTs;
import de.mrstupsi.moretnts.block.AbstractTNT;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class CustomTNT extends AbstractPrimedTNT {
    public static final String ID = "custom_tnt";
    public static final EntityType<CustomTNT> TYPE = EntityType.Builder.<CustomTNT>of(CustomTNT::new, MobCategory.MISC).fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10).build("primed_" + ID);
    public static final AbstractTNT BLOCK = new AbstractTNT() {
        @Override
        public void explode(Level world, BlockPos pos, LivingEntity owner) {
            CustomTNT tnt = new CustomTNT(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, owner, 1);
            world.addFreshEntity(tnt);
            world.playSound(null, tnt.getX(), tnt.getY(), tnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            world.gameEvent(owner, GameEvent.PRIME_FUSE, pos);
        }

        @Override
        public void exploded(Level world, BlockPos pos, Explosion explosion) {
            CustomTNT tnt = new CustomTNT(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, explosion.getSourceMob(), 1);
            int i = tnt.getFuse();
            tnt.setFuse(world.random.nextInt(i / 4) + i / 8);
            world.addFreshEntity(tnt);
        }
    };
    public static final BlockItem ITEM = new BlockItem(BLOCK, new Item.Properties().tab(MoreTNTs.TAB));
    private final float size;

    public CustomTNT(Level world, double x, double y, double z, LivingEntity owner, float size) {
        super(world, x, y, z, owner);
        this.size = size;
    }

    public CustomTNT(EntityType<? extends CustomTNT> type, Level world) {
        super(type, world);
        this.size = 1F;
    }

    @Override
    protected void explode() {
        float power = 4;
        if (size < 1) power *= size;
        else power *= ((size - 1) * 0.25F + 1);
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), power, Explosion.BlockInteraction.BREAK);
    }

    public float getSize() {
        return size;
    }

    public @NotNull EntityDimensions getDimensions(Pose pose) {
        return super.getDimensions(pose).scale(size);
    }

    @Override
    public BlockState getRenderState() {
        return BLOCK.defaultBlockState();
    }

    @Override
    public ResourceLocation getTexture() {
        return TextureAtlas.LOCATION_BLOCKS;
    }

    public static void register(DeferredRegister<EntityType<?>> entities, DeferredRegister<Block> blocks, DeferredRegister<Item> items) {
        entities.register("primed_" + ID, () -> TYPE);
        blocks.register(ID, () -> BLOCK);
        items.register(ID, () -> ITEM);
    }
}