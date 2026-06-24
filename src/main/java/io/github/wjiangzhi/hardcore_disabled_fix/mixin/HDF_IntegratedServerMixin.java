package io.github.wjiangzhi.hardcore_disabled_fix.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.server.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(IntegratedServer.class)
public class HDF_IntegratedServerMixin {
    @Redirect(
            method = "updateCommandsAllowedForOtherPlayers",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/server/IntegratedServer;updatePermissionAndChatAbilities(Lnet/minecraft/client/player/LocalPlayer;)V"
            )
    )
    private static void HDF_$updateCommandsAllowedForOtherPlayers$(IntegratedServer instance, LocalPlayer player) {
        if (instance.commandsAllowedForOtherPlayers() && instance.isPublished()) {
            instance.getPlayerList().op(player.nameAndId());
        } else {
            instance.getPlayerList().deop(player.nameAndId());
        }
    }
}
