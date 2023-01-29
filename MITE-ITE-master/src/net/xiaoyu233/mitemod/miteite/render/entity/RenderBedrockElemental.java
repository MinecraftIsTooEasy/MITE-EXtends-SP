package net.xiaoyu233.mitemod.miteite.render.entity;

import net.minecraft.*;

public class RenderBedrockElemental extends RenderEarthElemental{
    @Override
    protected void setTextures() {
        this.setTexture(0, "textures/entity/earth_elemental/bedrock/earth_elemental_bedrock");
    }
}
