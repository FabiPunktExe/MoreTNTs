package de.mrstupsi.moretnts;

import de.mrstupsi.moretnts.block.AbstractTNT;
import de.mrstupsi.moretnts.entity.CustomTNT;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("moretnts")
public class MoreTNTs {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "moretnts");
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "moretnts");
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "moretnts");
    public static final CreativeModeTab TAB = new CreativeModeTab("moretnts") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.TNT);
        }
    };

    public MoreTNTs() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        CustomTNT.register(ENTITIES, BLOCKS, ITEMS);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ENTITIES.register(modEventBus);
    }
}
