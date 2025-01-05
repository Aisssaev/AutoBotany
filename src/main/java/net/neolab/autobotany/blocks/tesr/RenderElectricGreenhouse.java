package net.neolab.autobotany.blocks.tesr;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.ModelData;
import net.neolab.autobotany.blocks.pattern.BlockEntityElectricGreenhousePattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderElectricGreenhouse implements BlockEntityRenderer<BlockEntityElectricGreenhousePattern> {
    public RenderElectricGreenhouse() {}

    @Override
    public boolean shouldRender(BlockEntityElectricGreenhousePattern p_173568_, Vec3 p_173569_) {
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
    public void render(BlockEntityElectricGreenhousePattern blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        var inventory = blockEntity.getInventory();
        float[][] positions = {
                {0.0f, 0.1f, 0.0f}, // Позиция для первого слота
                {0.0f, 0.1f, 0.3f}, // Второй слот
                {0.0f, 0.1f, 0.6f}, // Третий слот
                {0.3f, 0.1f, 0.0f}, // Четвёртый слот
                {0.6f, 0.1f, 0.0f}, // Пятый слот
                {0.6f, 0.1f, 0.6f}  // Шестой слот
        };
        for (int i = 0; i < 6; i++) {
            var stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty() && stack.getItem() instanceof BlockItem blockItem) {
                poseStack.pushPose();
                var state = blockItem.getBlock().defaultBlockState();
                // Перемещаем предмет в соответствующую позицию внутри блока
                poseStack.translate(positions[i][0], positions[i][1], positions[i][2]);

                // Устанавливаем масштаб предмета
                poseStack.scale(0.5f, 0.5f, 0.5f);

                // Рендер предмета
                Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, poseStack, buffer, light, overlay);

                poseStack.popPose();
            }
        }
    }
}
