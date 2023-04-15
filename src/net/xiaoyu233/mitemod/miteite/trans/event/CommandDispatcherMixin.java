package net.xiaoyu233.mitemod.miteite.trans.event;

import net.minecraft.CommandDispatcher;
import net.minecraft.CommandHandler;
import net.xiaoyu233.mitemod.miteite.events.command.CommandEXtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandDispatcher.class)
public class CommandDispatcherMixin extends CommandHandler {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectInit(CallbackInfo callbackInfo) {
        this.registerCommand(new CommandEXtend());

    }
}
