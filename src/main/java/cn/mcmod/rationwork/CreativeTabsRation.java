package cn.mcmod.rationwork;

import cn.mcmod.rationwork.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsRation extends CreativeTabs {

	public CreativeTabsRation() {
		super(RationMain.MODID+"_tabs");
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemStack getTabIconItem() {
		// TODO Auto-generated method stub
		return new ItemStack(ItemLoader.can_meal,1,3);
	}

}
