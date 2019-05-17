package cn.mcmod.rationwork;

import cn.mcmod.rationwork.event.RationEventLoader;
import cn.mcmod.rationwork.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@EventBusSubscriber
public class CommonProxy {
    public static CreativeTabs tab;

    public void preInit(FMLPreInitializationEvent event) {
        tab = new CreativeTabsRation();
        new ItemLoader(event);
        MinecraftForge.EVENT_BUS.register(new RationEventLoader());
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {

    }



}
