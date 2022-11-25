package de.mrstupsi.moretnts.client;

import de.mrstupsi.moretnts.block.AbstractTNT;
import de.mrstupsi.moretnts.client.renderer.entity.AbstractPrimedTNTRenderer;
import de.mrstupsi.moretnts.entity.CustomTNT;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "moretnts", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MoreTNTsClient {
    @SubscribeEvent
    public static void onRegister(EntityRenderersEvent.RegisterRenderers e) {
        //e.registerEntityRenderer(CustomTNT.TYPE, AbstractPrimedTNTRenderer::new);
    }

    /*@SubscribeEvent
    public static void onGather(GatherDataEvent e) {
        DataGenerator gen = e.getGenerator();
        if (e.includeClient()) {
            gen.addProvider(true, new BlockStateProvider(gen, "moretnts", e.getExistingFileHelper()) {
                @Override
                protected void registerStatesAndModels() {
                    for (AbstractTNT tnt : AbstractTNT.TNTS) {
                        getVariantBuilder(tnt).partialState().modelForState().modelFile(
                                models().getExistingFile(
                                        new ResourceLocation("moretnts", "models/block/" + tnt.getDescriptionId())
                                )
                        ).build();
                    }
                }
            });
            gen.addProvider(true, new BlockModelProvider(gen, "moretnts", e.getExistingFileHelper()) {
                @Override
                protected void registerModels() {
                    for (AbstractTNT tnt : AbstractTNT.TNTS) {
                        cubeBottomTop(
                                tnt.getDescriptionId(),
                                new ResourceLocation("moretnts", "textures/block/" + tnt.getDescriptionId() + "_side"),
                                new ResourceLocation("moretnts", "textures/block/" + tnt.getDescriptionId() + "_bottom"),
                                new ResourceLocation("moretnts", "textures/block/" + tnt.getDescriptionId() + "_top")
                        );
                    }
                }
            });
            gen.addProvider(true, new ItemModelProvider(gen, "moretnts", e.getExistingFileHelper()) {
                @Override
                protected void registerModels() {
                    for (AbstractTNT tnt : AbstractTNT.TNTS) {
                        withExistingParent(
                                tnt.getDescriptionId(),
                                new ResourceLocation("moretnts", "models/block/" + tnt.getDescriptionId())
                        );
                    }
                }
            });
        }
    }*/
}