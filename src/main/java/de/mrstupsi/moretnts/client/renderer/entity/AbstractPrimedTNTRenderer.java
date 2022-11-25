package de.mrstupsi.moretnts.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import de.mrstupsi.moretnts.entity.AbstractPrimedTNT;
import de.mrstupsi.moretnts.entity.CustomTNT;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AbstractPrimedTNTRenderer extends EntityRenderer<AbstractPrimedTNT> {
    private final BlockRenderDispatcher blockRenderer;

    public AbstractPrimedTNTRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.5F;
        this.blockRenderer = ctx.getBlockRenderDispatcher();
    }

    @Override
    public void render(AbstractPrimedTNT tnt, float p_116178_, float p_116179_, PoseStack stack, MultiBufferSource bufferSource, int p_116182_) {
        stack.pushPose();
        stack.translate(0.0D, 0.5D, 0.0D);
        int i = tnt.getFuse();
        if ((float)i - p_116179_ + 1.0F < 10.0F) {
            float f = 1.0F - ((float)i - p_116179_ + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float f1 = 1.0F + f * 0.3F;
           stack.scale(f1, f1, f1);
        }
        stack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        stack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        if (tnt instanceof CustomTNT) {
            float size = ((CustomTNT) tnt).getSize();
            stack.scale(size, size, size);
            stack.translate(-0.5, -0.5 / size, -0.5);
        } else {
            stack.translate(-0.5, -0.5, -0.5);
        }
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, tnt.getRenderState(), stack, bufferSource, p_116182_, i / 5 % 2 == 0);
        stack.popPose();
        super.render(tnt, p_116178_, p_116179_, stack, bufferSource, p_116182_);
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractPrimedTNT tnt) {
        return tnt.getTexture();
    }
}