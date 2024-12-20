package net.neolab.autobotany.helper;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.client.core.handler.ClientTickHandler;

public class GhostItemRenderer {
    public GhostItemRenderer() {
    }

    public static void renderGhostItem(List<ItemStack> stacks, PoseStack poseStack, int x, int y) {
        if (!stacks.isEmpty()) {
            var stack = (ItemStack)stacks.get((ClientTickHandler.ticksInGame / 20 % stacks.size() + stacks.size()) % stacks.size());
            renderGhostItem(stack, poseStack, x, y);
        }
    }

    public static void renderGhostItem(ItemStack stack, PoseStack poseStack, int x, int y) {
        if (!stack.isEmpty()) {
            Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(stack, x, y);
            RenderSystem.depthFunc(516);
            GuiComponent.fill(poseStack, x, y, x + 16, y + 16, 822083583);
            RenderSystem.depthFunc(515);
        }
    }
}