package cn.mcmod.rationwork.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.langball.coffee.item.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemRationCan extends ItemBase {
	public final Item[] foods;
	public final int[][] metas;
	
	public ItemRationCan(String name, String[] subNames,Item[] food,int[][] meta) {
		super(name, 64, subNames);
		foods=food!=null&&food.length>0?food:new Item[]{};
		metas=meta!=null&&meta.length>0?meta:new int[][]{};
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		List<ItemStack> result = new ArrayList<ItemStack>();
		if(!playerIn.world.isRemote){
		for(int i = 0;i<foods.length;i++){
			Random rand = worldIn.rand;
			int size = rand.nextInt(2)+1;
			int[] arr = metas[i];
			int meta = arr[rand.nextInt(arr.length)];
			result.add(new ItemStack(foods[i],size,meta));
		}
		
		for(int j = 0;j<result.size();j++){
			if (!playerIn.inventory.addItemStackToInventory(result.get(j)))
            {
				playerIn.dropItem(result.get(j), false);
            }
			
		}
		if(playerIn.getHeldItem(handIn).getCount()>1){
			playerIn.getHeldItem(handIn).shrink(1); 
			playerIn.dropItem(new ItemStack(ItemLoader.can_opened), false);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
		else {
			playerIn.setHeldItem(handIn, new ItemStack(ItemLoader.can_opened));
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
		}else return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
