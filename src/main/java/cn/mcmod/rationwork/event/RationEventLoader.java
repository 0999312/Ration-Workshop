package cn.mcmod.rationwork.event;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import cn.mcmod.rationwork.item.ItemLoader;
import net.langball.coffee.recipes.blocks.GrinderRecipes;
import net.langball.coffee.recipes.blocks.RollerRecipes;
import net.langball.coffee.util.RecipesUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapelessOreRecipe;

@EventBusSubscriber
public class RationEventLoader {
	@SubscribeEvent
	public static void recipeRegister(RegistryEvent.Register<IRecipe> event) {
		RecipesUtil.addRecipe("can_oatmeal", new ShapelessOreRecipe(new ResourceLocation(""),new ItemStack(ItemLoader.can_meal,1,0),new Object[]{
				new ItemStack(ItemLoader.can_opened,1),new ItemStack(ItemLoader.food_bar,1,0),"listAllwater"
		}));
		RecipesUtil.addRecipe("can_mashedpotato", new ShapelessOreRecipe(new ResourceLocation(""),new ItemStack(ItemLoader.can_meal,1,1),new Object[]{
				new ItemStack(ItemLoader.can_opened,1),new ItemStack(ItemLoader.food_bar,1,1),"listAllwater"
		}));
		RecipesUtil.addRecipe("can_brownedmeat", new ShapelessOreRecipe(new ResourceLocation(""),new ItemStack(ItemLoader.can_meal,1,2),new Object[]{
				new ItemStack(ItemLoader.can_opened,1),new ItemStack(ItemLoader.food_bar,1,2),"listAllwater"
		}));
		RecipesUtil.addRecipe("can_meatstew", new ShapelessOreRecipe(new ResourceLocation(""),new ItemStack(ItemLoader.can_meal,1,3),new Object[]{
				new ItemStack(ItemLoader.can_opened,1),new ItemStack(ItemLoader.food_bar,1,3),"listAllwater"
		}));
		RecipesUtil.addRecipe("can_coffee", new ShapelessOreRecipe(new ResourceLocation(""),new ItemStack(ItemLoader.can_drink,1,0),new Object[]{
				new ItemStack(ItemLoader.can_opened,1),new ItemStack(ItemLoader.instant_bag,1,0),"listAllwater"
		}));
		RecipesUtil.addRecipe("can_tea", new ShapelessOreRecipe(new ResourceLocation(""),new ItemStack(ItemLoader.can_drink,1,1),new Object[]{
				new ItemStack(ItemLoader.can_opened,1),new ItemStack(ItemLoader.instant_bag,1,1),"listAllwater"
		}));
		RecipesUtil.addRecipe("can_cocoa", new ShapelessOreRecipe(new ResourceLocation(""),new ItemStack(ItemLoader.can_drink,1,2),new Object[]{
				new ItemStack(ItemLoader.can_opened,1),new ItemStack(ItemLoader.beverage_bar,1,0),"listAllwater"
		}));
		RecipesUtil.addRecipe("can_lemon", new ShapelessOreRecipe(new ResourceLocation(""),new ItemStack(ItemLoader.can_drink,1,3),new Object[]{
				new ItemStack(ItemLoader.can_opened,1),new ItemStack(ItemLoader.beverage_bar,1,1),"listAllwater"
		}));
		RollerRecipes.instance().addSmeltingRecipe(new ItemStack(ItemLoader.can_opened,1), new ItemStack(net.langball.coffee.item.ItemLoader.materials,2,9), 0f);
		GrinderRecipes.instance().addSmeltingRecipe(new ItemStack(ItemLoader.can_opened,1), new ItemStack(Items.IRON_INGOT,2), 0f);
		addChestLoot(new ItemStack(ItemLoader.ration_can_normal));
		addChestLoot(new ItemStack(ItemLoader.ration_can_small));
		addChestLoot(new ItemStack(ItemLoader.ration_can_large));
	}
	
    private static final Set<String> LOOT_LOCATIONS = ImmutableSet.<String>builder()
            .add(LootTableList.CHESTS_SIMPLE_DUNGEON.toString())
            .add(LootTableList.CHESTS_ABANDONED_MINESHAFT.toString())
            .add(LootTableList.CHESTS_DESERT_PYRAMID.toString())
            .add(LootTableList.CHESTS_JUNGLE_TEMPLE.toString())
            .add(LootTableList.CHESTS_VILLAGE_BLACKSMITH.toString())
            .add(LootTableList.CHESTS_IGLOO_CHEST.toString())
            .add(LootTableList.CHESTS_NETHER_BRIDGE.toString())
            .add(LootTableList.CHESTS_END_CITY_TREASURE.toString())
            .add(LootTableList.CHESTS_SPAWN_BONUS_CHEST.toString())
            .build();
    private final static Set<LootPool> lootPools = new HashSet<LootPool>();
    
	@SubscribeEvent
	public static void onLootLoaded(LootTableLoadEvent evt) {

        if (LOOT_LOCATIONS.contains(evt.getName().toString()))
        {
            for (LootPool pool : lootPools)
            {
                evt.getTable().addPool(pool);
            }
        }
	}
	
    public static void addChestLoot(ItemStack stack)
    {
        LootCondition[] lootConditions = new LootCondition[0];
        LootFunction[] setMeta = new LootFunction[] { new SetMetadata(lootConditions, new RandomValueRange(stack.getMetadata())) };
        LootEntry entry = new LootEntryItem(stack.getItem(), 1, 1, setMeta, lootConditions, stack.getUnlocalizedName());
        LootCondition chance = new RandomChance((float)RationConfigLoader.chestLootChance * 0.75f);

        lootPools.add(new LootPool(new LootEntry[] { entry }, new LootCondition[] { chance }, new RandomValueRange(1), new RandomValueRange(0), stack.getUnlocalizedName()));
    }
	
	@SubscribeEvent
	public static void KillingMob(LivingExperienceDropEvent event) {
		if(event.getAttackingPlayer()!=null){
			EntityLivingBase target = event.getEntityLiving();
			  if (!(target instanceof EntityLiving)) return;
		      if (!(target instanceof IMob)) return;
		      if(!target.isNonBoss() && !target.world.isRemote){
		           Random rand = target.getRNG();
		           int result = rand.nextInt(99)+1;
		           if(result<=5) {
		        	   dropItem(new ItemStack(ItemLoader.ration_can_large), event.getEntityLiving().world, event.getEntityLiving());
		        	return;   
		           	}
		           if(result>5&&result<=25) {
		        	   dropItem(new ItemStack(ItemLoader.ration_can_normal), event.getEntityLiving().world, event.getEntityLiving());
		        	return;   
		           	}
		           if(result>25&&result<=49) {
		        	   dropItem(new ItemStack(ItemLoader.ration_can_small), event.getEntityLiving().world, event.getEntityLiving());
		        	return;   
		           	}
		       }
		      }
		}
	
	 private static void dropItem(ItemStack itemStack, World world, EntityLivingBase entity)
	  {
	    if ((world.restoringBlockSnapshots) || (world.isRemote)) {
	      return;
	    }
	    float f = 0.5F;
	    double d0 = world.rand.nextFloat() * f + 0.25D;
	    double d1 = world.rand.nextFloat() * f + 0.25D;
	    double d2 = world.rand.nextFloat() * f + 0.25D;
	    

	    EntityItem entityItem = new EntityItem(world, entity.posX + d0, entity.posY + d1, entity.posZ + d2, itemStack);
	    entityItem.setDefaultPickupDelay();
	    world.spawnEntity(entityItem);
	  }
}
