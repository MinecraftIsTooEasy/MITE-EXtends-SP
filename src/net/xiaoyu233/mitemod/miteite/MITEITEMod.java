package net.xiaoyu233.mitemod.miteite;

import net.minecraft.ChatMessage;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormat;
import net.minecraft.HttpUtilities;
import net.xiaoyu233.fml.AbstractMod;
import net.xiaoyu233.fml.FishModLoader;
import net.xiaoyu233.fml.classloading.Mod;
import net.xiaoyu233.fml.config.InjectionConfig;
import net.xiaoyu233.fml.util.ModInfo;
import net.xiaoyu233.mitemod.miteite.events.EventListeners;
import net.xiaoyu233.mitemod.miteite.trans.MinecraftTrans;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import net.xiaoyu233.mitemod.miteite.util.Constant;
import org.spongepowered.asm.mixin.MixinEnvironment;

import javax.annotation.Nonnull;
import javax.swing.*;

@Mod
public class MITEITEMod extends AbstractMod {
    // jar -cvf mite-extreme-v0.0.4.jar .
    private static final int MOD_LOADER_MIN_VER_NUM = 100;
    private static final String MOD_LOADER_MIN_VER_STRING = "v1.0.0";
    @Override
    public void preInit() {

    }
    public static void checkUpdateVer(EntityPlayer player){
        String webVersion = HttpUtilities.performGetRequest("https://lucklong.cn/ExtendVer.txt", 3000, 3000);
        if (webVersion == null){
            player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[MITE-EXtends]:").setColor(EnumChatFormat.WHITE)
                    .appendComponent(ChatMessage.createFromTranslationKey("无法检查更新")).setColor(EnumChatFormat.RED));
        } else{
            player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[MITE-EXtends]:").setColor(EnumChatFormat.WHITE)
                    .appendComponent(ChatMessage.createFromTranslationKey("当前版本: " + Constant.MITE_ITE_VERSION)).setColor(EnumChatFormat.WHITE)
                    .appendComponent(ChatMessage.createFromTranslationKey("      最新版本: " + webVersion)).setColor(EnumChatFormat.GREEN));
            if (!Constant.MITE_ITE_VERSION.equals(webVersion)) {
                player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[MITE-EXtends]:").setColor(EnumChatFormat.WHITE)
                        .appendComponent(ChatMessage.createFromTranslationKey("有新版本可用,请到交流群中下载")).setColor(EnumChatFormat.GREEN));
            } else{
                player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[MITE-EXtends]:").setColor(EnumChatFormat.WHITE)
                        .appendComponent(ChatMessage.createFromTranslationKey("当前为最新版本")).setColor(EnumChatFormat.GREEN));
            }
        }

    }
    @Nonnull
    public InjectionConfig getInjectionConfig() {
        return InjectionConfig.Builder.of("MITE-EXtends", MinecraftTrans.class.getPackage(), MixinEnvironment.Phase.INIT).setRequired().build();
    }

    @Override
    public void postInit() {
        ModInfo modLoader = FishModLoader.getModsMap().get("FishModLoader");
        int modLoaderNum = modLoader.getModVerNum();
        if (modLoaderNum < MOD_LOADER_MIN_VER_NUM){
            JFrame diaFrame = new JFrame();
            diaFrame.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(diaFrame,"MITE-EXtends加载错误: 模组加载器版本过低\nFishModLoader模组加载器需要至少" + MOD_LOADER_MIN_VER_STRING + "版本 \n当前版本:" + modLoader.getModVerStr(),"错误", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        EventListeners.registerAllEvents();
        Configs.loadConfigs();
    }

    @Override
    public String modId() {
        return "MITE-EXtends-SP";
    }

    @Override
    public int modVerNum() {
        return 47;
    }

    @Override
    public String modVerStr() {
        return Constant.MITE_ITE_VERSION;
    }

}
