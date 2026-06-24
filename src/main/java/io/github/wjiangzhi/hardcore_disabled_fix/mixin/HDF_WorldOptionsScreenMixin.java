package io.github.wjiangzhi.hardcore_disabled_fix.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.options.WorldOptionsScreen;

import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;

import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldOptionsScreen.class)
public class HDF_WorldOptionsScreenMixin {
    @ModifyExpressionValue(
            method = "createAllowCommandsButton",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/server/IntegratedServer;isHardcore()Z"
            )
    )
    private boolean HDF_createAllowCommandsButton$isHardcore(boolean original) {
        return false;
    }

    @ModifyExpressionValue(
            method = "updateButton",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/server/IntegratedServer;isHardcore()Z"
            )
    )
    private boolean HDF_updateButton$isHardcore(boolean original) {
        return false;
    }

    @Shadow
    @Final
    @Mutable
    private static Component TITLE;
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void HDF_$$TITLE(CallbackInfo ci) {
        TITLE = Component.literal("*")
                .append(
                        Component.translatable("options.worldOptions.title")
                                .withStyle(ChatFormatting.ITALIC)
                )
                .append(
                        Component.literal(" - ")
                )
                .append(
                        Component.translatable(
                                "hardcore_disabled_fix.gui.tail"
                        ).withStyle(
                                new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GOLD}
                        )
                )
        ;
    }
}
