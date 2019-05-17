package cn.mcmod.rationwork.event;

import cn.mcmod.rationwork.RationMain;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.RequiresWorldRestart;

@Config(modid = RationMain.MODID)
public final class RationConfigLoader {

    @Config.Name("chestLootChance")
    @Config.RangeInt(min = 1)
    @RequiresWorldRestart
    @Config.RequiresMcRestart
    public static int chestLootChance = 5;
}
