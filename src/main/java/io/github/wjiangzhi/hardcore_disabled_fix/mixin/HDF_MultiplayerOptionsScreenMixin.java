package io.github.wjiangzhi.hardcore_disabled_fix.mixin;

import net.minecraft.client.gui.screens.MultiplayerOptionsScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

import org.spongepowered.asm.mixin.Mixin;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;

import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mutable;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerOptionsScreen.class)
public class HDF_MultiplayerOptionsScreenMixin {
    @ModifyExpressionValue(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/server/IntegratedServer;isHardcore()Z"
            )
    )
    private boolean HDF$init$isHardcore(boolean original) {
        return false;
    }

    @Shadow
    @Final
    @Mutable
    private static Component TITLE;
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void HDF$_$TITLE(CallbackInfo ci) {
        TITLE = Component.literal("*").append(Component.translatable("options.multiplayer.title").withStyle(ChatFormatting.ITALIC));
    }

    @Shadow
    @Final
    @Mutable
    private static Component OTHER_PLAYERS_HEADER;
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void HDF$_$OTHER_PLAYERS_HEADER(CallbackInfo ci) {
        OTHER_PLAYERS_HEADER = Component.literal("")
                .append(Component.translatable(
                        "menu.multiplayerOptions.otherPlayers.header"
                ).withStyle(
                    new ChatFormatting[]{ChatFormatting.UNDERLINE, ChatFormatting.BOLD}
                ))
                .append(Component.literal(" - ").withStyle(ChatFormatting.RESET))
                .append(Component.translatable(
                        "hardcore_disabled_fix.gui.tail"
                ).withStyle(
                        new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GOLD}
                ));
    }
}
