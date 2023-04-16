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
        if (strings[0].equals("generatePrice")) {
            try {
                File file = new File("allPrice.txt");
                if (file.delete()){
                    player.sendChatToPlayer(ChatMessage.createFromText("正在删除已有价格表").setColor(EnumChatFormat.RED));
                }
                FileWriter fileWritter = new FileWriter(file.getName(), true);
                if (file.exists()) {
                    for (Item item : Item.itemsList) {
                        if (item != null && item.hasPrice() || item != null && item.hasSoldPrice()) {
                            try {
                                if (item instanceof ItemBlock) {
                                    fileWritter.write(((ItemBlock) item).getBlock().getLocalizedName() + " 售价: " + item.getSoldPrice() + " 购价: " + item.getPrice() + " ID: " + ((ItemBlock) item).getBlockID() + "\n\n");
                                } else {
                                    fileWritter.write(item.getItemDisplayName() + " 售价: " + item.getSoldPrice() + " 购价: " + item.getPrice() + " ID: " + item.itemID + "\n\n");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                fileWritter.close();
                player.sendChatToPlayer(ChatMessage.createFromText("生成并打开价格表").setColor(EnumChatFormat.BLUE));
                Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    e.printStackTrace();
            }
        } else {
            player.sendChatToPlayer(ChatMessage.createFromText("指令不存在咯").setColor(EnumChatFormat.RED));
        }
    }
}
