package net.xiaoyu233.mitemod.miteite.events.command;

import net.minecraft.*;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CommandEXtend extends CommandAbstract {
    @Override
    public String getCommandName() {
        return "extend";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandListener iCommandListener) {
        return "commands.extend.usage";
    }

    @Override
    public List addTabCompletionOptions(ICommandListener par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, "generatePrice"
                //, "setPrice"
        ) : null;
    }

    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
        //Item heldItem = player.getHeldItemStack().getItem();
        if (strings[0].equals("generatePrice")) {
            try {
                File file = new File("price.txt");
                FileWriter fileWritter = new FileWriter(file.getName(), true);
                for (Item item : Item.itemsList) {
                    if (item != null && item.hasPrice()) {
                        if (file.exists()) {
                            if (item instanceof ItemBlock) {
                                fileWritter.write(((ItemBlock) item).getBlock().getLocalizedName() + " 售价: " + item.getSoldPrice() + " 购价: " + item.getPrice() + " ID: " + ((ItemBlock) item).getBlockID() + "\n\n");
//                                fileWritter.write(item.getUnlocalizedName() + "=" + item.getSoldPrice() + "," + item.getPrice() + "\n\n");
                            } else {
                                fileWritter.write(item.getItemDisplayName() + " 售价: " + item.getSoldPrice() + " 购价: " + item.getPrice() + " ID: " + item.itemID + "\n\n");
//                                fileWritter.write(item.getUnlocalizedName() + "=" + item.getSoldPrice() + "," + item.getPrice() + "\n\n");
                            }
                        }
                    }
                }
                player.sendChatToPlayer(ChatMessage.createFromText("[MITE-EXtends]: ").setColor(EnumChatFormat.BLUE)
                        .appendComponent(ChatMessage.createFromText("生成并打开价格表")));
                fileWritter.close();
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
//        else if (strings[0].equals("setPrice")) {
//            heldItem.setPrice(Integer.valueOf(strings[1]));
//            player.chat;
//            player.sendChatToPlayer(ChatMessage.createFromText("[MITE-EXtends]: ").setColor(EnumChatFormat.BLUE)
//                    .appendComponent(ChatMessage.createFromText("手中物品购价为" + strings[1])));
        } else {
            player.sendChatToPlayer(ChatMessage.createFromText("[MITE-EXtends]: ").setColor(EnumChatFormat.BLUE)
                    .appendComponent(ChatMessage.createFromText("指令不存在咯")));
        }
    }
}
