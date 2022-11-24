package de.mrstupsi.moretnts;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("moretnts")
public class MoreTNTs {
    public MoreTNTs() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
