package cn.mcmod.rationwork;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RationMain.MODID, name = RationMain.NAME, version = RationMain.VERSION,dependencies="required:forge@[14.23.4.2764,);required:coffeework")
public class RationMain {
    public static final String MODID = "rationwork";
    public static final String NAME = "Ration Workshop";
    public static final String VERSION = "@version@";

    @Instance(RationMain.MODID)
    public static RationMain instance;

    @SidedProxy(clientSide = "cn.mcmod.rationwork.ClientProxy", serverSide = "cn.mcmod.rationwork.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
