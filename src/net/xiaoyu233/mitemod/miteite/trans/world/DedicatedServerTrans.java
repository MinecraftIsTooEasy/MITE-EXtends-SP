//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.xiaoyu233.mitemod.miteite.trans.world;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.MITEITEMod;
import net.xiaoyu233.mitemod.miteite.util.Constant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DedicatedServer.class)
public class DedicatedServerTrans {

    public DedicatedServerTrans() {}

    @Inject(method = "playerLoggedIn",at = @At("RETURN"))
    public void playerLoggedIn(ServerPlayer par1EntityPlayerMP, CallbackInfo callbackInfo) {
        par1EntityPlayerMP.sendChatToPlayer(ChatMessage.createFromTranslationKey("[MITE-EXtends-SP]:").setColor(EnumChatFormat.WHITE)
                .appendComponent(ChatMessage.createFromTranslationKey("MITE-EXtends-SP-" + Constant.MITE_ITE_VERSION +" 由 ")
                        .appendComponent(ChatMessage.createFromTranslationKey("lee、whscqngsd、rizur、NoRegrets/Huix").setColor(EnumChatFormat.WHITE)))
                .addText(" 重写自wensc的MITE-extreme").setColor(EnumChatFormat.DARK_AQUA));
        MITEITEMod.checkUpdateVer(par1EntityPlayerMP);
        par1EntityPlayerMP.sendChatToPlayer(ChatMessage.createFromTranslationKey("[MITE-EXtends-SP]:")
                .appendComponent(ChatMessage.createFromTranslationKey("企鹅交流群:  661223990").setColor(EnumChatFormat.WHITE)));
        if (par1EntityPlayerMP.isFirstLogin == true) {
            par1EntityPlayerMP.isFirstLogin = false;
        }
        this.updatePlayersFile();
    }
    @Shadow
    public void updatePlayersFile() {
    }
}
