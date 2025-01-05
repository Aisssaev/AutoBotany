package net.neolab.autobotany.blocks.tesr;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neolab.autobotany.blocks.pattern.BlockEntityElectricGreenhousePattern;
import net.neolab.autobotany.blocks.tiles.BlockEntityPrimitiveGreenhouse;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderPrimitiveGreenhouse implements BlockEntityRenderer<BlockEntityPrimitiveGreenhouse> {
    public RenderPrimitiveGreenhouse() {}

    public boolean shouldRender(BlockEntityPrimitiveGreenhouse p_173569_) {
        return true;
    }

    private void renderState(@Nullable BlockState state, float translateX, float translateZ, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int light, int overlay) {
        if (state != null) {
            poseStack.pushPose();
            poseStack.translate((double)translateX, 0.0, (double)translateZ);
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, poseStack, buffer, light, overlay);
            poseStack.popPose();
        }

    }

    @Override
    public void render(BlockEntityPrimitiveGreenhouse blockEntityPrimitiveGreenhouse, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {

    }
}
