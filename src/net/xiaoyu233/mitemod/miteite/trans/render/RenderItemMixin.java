//package net.xiaoyu233.mitemod.miteite.trans.render;
//
//import net.minecraft.*;
//import org.lwjgl.opengl.GL11;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//
//@Mixin(bgw.class)
//public class RenderItemMixin {
//    @Overwrite
//    public void a(avi par1FontRenderer, bim par2TextureManager, ItemStack par3ItemStack, int par4, int par5, String par6Str) {
////        if (par3ItemStack != null) {
////            if (par3ItemStack.stackSize > 1 || par6Str != null) {
////                String var7 = par6Str == null ? String.valueOf(par3ItemStack.stackSize) : par6Str;
////                GL11.glDisable(2896);
////                GL11.glDisable(2929);
////                par1FontRenderer.a(var7, par4 + 19 - 2 - par1FontRenderer.a(var7), par5 + 6 + 3, 16777215);
////                GL11.glEnable(2896);
////                GL11.glEnable(2929);
////            }
////
////            if (par3ItemStack.isItemDamaged()) {
////                int var12 = (int)Math.round(13.0 - (double)par3ItemStack.getItemDamageForDisplay() * 13.0 / (double)par3ItemStack.getMaxDamage());
////                int var8 = (int)Math.round(255.0 - (double)par3ItemStack.getItemDamageForDisplay() * 255.0 / (double)par3ItemStack.getMaxDamage());
////                GL11.glDisable(2896);
////                GL11.glDisable(2929);
////                GL11.glDisable(3553);
////                bfq var9 = bfq.a;
////                int var10 = 255 - var8 << 16 | var8 << 8;
////                int var11 = (255 - var8) / 4 << 16 | 16128;
////                this.a(var9, par4 + 2, par5 + 13, 13, 2, 0);
////                this.a(var9, par4 + 2, par5 + 13, 12, 1, var11);
////                this.a(var9, par4 + 2, par5 + 13, var12, 1, var10);
////                GL11.glEnable(3553);
////                GL11.glEnable(2896);
////                GL11.glEnable(2929);
////                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
////            }
////        }
//    }
//    @Shadow
//    private void a(bfq par1Tessellator, int par2, int par3, int par4, int par5, int par6) {
//
//    }
//}
