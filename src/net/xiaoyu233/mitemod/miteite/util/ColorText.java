package net.xiaoyu233.mitemod.miteite.util;

import net.minecraft.EnumChatFormat;
import net.minecraft.Minecraft;

public class ColorText {
    private static final EnumChatFormat[] fabulousness = new EnumChatFormat[] {EnumChatFormat.RED, EnumChatFormat.YELLOW, EnumChatFormat.YELLOW,
            EnumChatFormat.GREEN, EnumChatFormat.AQUA, EnumChatFormat.BLUE, EnumChatFormat.LIGHT_PURPLE};
    public static String makeFabulous(String input) {
        return Formatting(input, fabulousness, 1, 1);
    }

    private static final EnumChatFormat[] sanic = new EnumChatFormat[] {EnumChatFormat.BLUE, EnumChatFormat.BLUE, EnumChatFormat.BLUE,
            EnumChatFormat.BLUE, EnumChatFormat.WHITE, EnumChatFormat.BLUE, EnumChatFormat.WHITE, EnumChatFormat.WHITE, EnumChatFormat.BLUE,
            EnumChatFormat.WHITE, EnumChatFormat.WHITE, EnumChatFormat.BLUE, EnumChatFormat.RED, EnumChatFormat.WHITE, EnumChatFormat.GRAY,
            EnumChatFormat.GRAY, EnumChatFormat.GRAY, EnumChatFormat.GRAY, EnumChatFormat.GRAY, EnumChatFormat.GRAY, EnumChatFormat.GRAY,
            EnumChatFormat.GRAY, EnumChatFormat.GRAY, EnumChatFormat.GRAY, EnumChatFormat.GRAY, EnumChatFormat.GRAY, EnumChatFormat.GRAY,
            EnumChatFormat.GRAY, EnumChatFormat.GRAY, EnumChatFormat.GRAY};
    public static String makeSANIC(String input) {
        return Formatting(input, sanic, 1, 1);
    }

    public static String Formatting(String input, EnumChatFormat[] colours, double delay, int posstep) {
        StringBuilder sb = new StringBuilder(input.length()*3);

        if (delay <= 0) {
            delay = 0.001;
        }

        Minecraft mc = Minecraft.w();
        long gameTime = 0;
        if (mc.h.worldObj != null) {
            gameTime = mc.h.worldObj.total_time;
        }

        int offset = (int) Math.floor(gameTime / delay) % colours.length;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            int col = ((i * posstep) + colours.length - offset) % colours.length;

            sb.append(colours[col].toString());
            sb.append(c);
        }

        return sb.toString();
    }
}
