package cn.mcmod.rationwork.item;
import net.langball.coffee.drinks.DrinkCoffee;
import net.langball.coffee.effect.PotionLoader;
import net.langball.coffee.item.ItemBase;
import net.langball.coffee.item.ItemFoodBasic;
import net.langball.coffee.item.ItemFoodContain;
import net.langball.coffee.util.JSON_Creator;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import cn.mcmod.rationwork.CommonProxy;
import cn.mcmod.rationwork.RationMain;

public class ItemLoader {
	public static Item can_opened = new Item().setUnlocalizedName(RationMain.MODID+"."+"can_opened");
	public static ItemFoodContain can_meal = new ItemFoodContain("can_meal",1,
			new int[]{10,14,16,18},
			new float[]{0.6f,0.8f,0.8f,1f},
			new String[]{
				RationMain.MODID+"."+"can_oatmeal",
				RationMain.MODID+"."+"can_mashedpotato",
				RationMain.MODID+"."+"can_brownedmeat",
				RationMain.MODID+"."+"can_meatstew"
			},
			new ItemStack[]{
					new ItemStack(can_opened),
					new ItemStack(can_opened),
					new ItemStack(can_opened),
					new ItemStack(can_opened)
			});
	public static ItemBase instant_bag = new ItemBase("instant_bag", 64, 
			new String[]{
					RationMain.MODID+"."+"coffee_instant_bag",
					RationMain.MODID+"."+"tea_instant_bag"
				}
			);
	public static ItemFoodBasic beverage_bar = new ItemFoodBasic("beverage_bar", 64,
			new int[]{4,4},
			new float[]{0.4f,0.4f},
			new String[]{
					RationMain.MODID+"."+"cocoa_beverage_bar",
					RationMain.MODID+"."+"lemon_beverage_bar"
				}
			);
	public static ItemFoodBasic food_bar = new ItemFoodBasic("food_bar", 64,
			new int[]{5,5,5,5},
			new float[]{0.4f,0.4f,0.4f,0.4f},
			new String[]{
					RationMain.MODID+"."+"oatmeal_bar",
					RationMain.MODID+"."+"potato_bar",
					RationMain.MODID+"."+"meat_bar",
					RationMain.MODID+"."+"stew_bar"
				}
			);
	public static ItemRationCan ration_can_small = new ItemRationCan("ration_can_small",
			new String[]{
					RationMain.MODID+"."+"ration_can_small"
				},
			new Item[]{
			net.langball.coffee.item.ItemLoader.dessert_1,
			net.langball.coffee.item.ItemLoader.materialFood
			},
			new int[][]{
				new int[]{5},
				new int[]{3,6,14}
			});
	public static ItemRationCan ration_can_normal = new ItemRationCan("ration_can_normal",
			new String[]{
					RationMain.MODID+"."+"ration_can_normal"
				},
			new Item[]{
					net.langball.coffee.item.ItemLoader.dessert_1,
					net.langball.coffee.item.ItemLoader.materialFood,
					instant_bag
			},
			new int[][]{
				new int[]{5},
				new int[]{3,6,14},
				new int[]{0,1}
			});
	public static ItemRationCan ration_can_large = new ItemRationCan("ration_can_large",
			new String[]{
					RationMain.MODID+"."+"ration_can_large"
				},
			new Item[]{
			net.langball.coffee.item.ItemLoader.dessert_1,
			net.langball.coffee.item.ItemLoader.materialFood,
			instant_bag,
			beverage_bar,
			food_bar
		},
			new int[][]{
				new int[]{5},
				new int[]{3,6,14},
				new int[]{0,1},
				new int[]{0,1},
				new int[]{0,1,2,3}
			});
	public static DrinkCoffee can_drink = new DrinkCoffeeCan("can_drink", 3, 
			new int[]{2,2,2,2},
			new float[]{0.2f,0.2f,0.2f,0.2f},
			new String[]{
					RationMain.MODID+"."+"can_coffee",
					RationMain.MODID+"."+"can_tea",
					RationMain.MODID+"."+"can_cocoa",
					RationMain.MODID+"."+"can_lemon"
				},
			new PotionEffect[][]{
				new PotionEffect[]{
						new PotionEffect(PotionLoader.caffeine,3600,0),
						new PotionEffect(ForgeRegistries.POTIONS.getValue(new ResourceLocation("minecraft", "haste")), 600, 0),
						new PotionEffect(ForgeRegistries.POTIONS.getValue(new ResourceLocation("minecraft", "speed")), 600, 0)
						},
				new PotionEffect[]{
						new PotionEffect(ForgeRegistries.POTIONS.getValue(new ResourceLocation("minecraft", "health_boost")), 600, 0),
						new PotionEffect(ForgeRegistries.POTIONS.getValue(new ResourceLocation("minecraft", "speed")), 600, 0),
						new PotionEffect(PotionLoader.caffeine, 3600,0)
				},
				new PotionEffect[]{
						new PotionEffect(ForgeRegistries.POTIONS.getValue(new ResourceLocation("minecraft", "health_boost")), 600, 0),
						new PotionEffect(ForgeRegistries.POTIONS.getValue(new ResourceLocation("minecraft", "strength")), 600, 0),
						new PotionEffect(PotionLoader.caffeine, 1200,0)
				},
				new PotionEffect[]{
						new PotionEffect(ForgeRegistries.POTIONS.getValue(new ResourceLocation("minecraft", "health_boost")), 600, 0),
						new PotionEffect(ForgeRegistries.POTIONS.getValue(new ResourceLocation("minecraft", "speed")), 600, 0),
						new PotionEffect(ForgeRegistries.POTIONS.getValue(new ResourceLocation("minecraft", "haste")), 600, 0)
				}
				});
	
	public ItemLoader(FMLPreInitializationEvent event) {
		register(ration_can_small);
		register(ration_can_normal);
		register(ration_can_large);
		register(can_opened);
		register(instant_bag);
		register(beverage_bar);
		register(food_bar);
		register(can_meal);
		register(can_drink);
	}
	@SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
		registerRender(can_drink);
		registerRender(beverage_bar);
		registerRender(ration_can_small);
		registerRender(ration_can_normal);
		registerRender(ration_can_large);
		registerRender(instant_bag);
		registerRender(food_bar);
		registerRender(can_opened);
		registerRender(can_meal);
    }

	private static void register(Item item)
    {
		item.setCreativeTab(CommonProxy.tab);
        ForgeRegistries.ITEMS.register(item.setRegistryName(item.getUnlocalizedName().substring(5+RationMain.MODID.length()+1)));
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(ItemBase item)
    {
    	registerRender(item, false);
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerRender(ItemFoodBasic item)
    {
    	registerRender(item, false);
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerRender(ItemBase item,boolean json_create)
    {
    	for(int i = 0;i<item.getSubNames().length;i++){
    		String name = item.getSubNames()[i].substring(RationMain.MODID.length()+1);
    		if(json_create)JSON_Creator.genItem(name, name, "json_create");
            ModelResourceLocation model = new ModelResourceLocation(new ResourceLocation(RationMain.MODID,name), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, i, model);
    	}
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerRender(ItemFoodBasic item,boolean json_create)
    {
    	
    	for(int i = 0;i<item.getSubNames().length;i++){
    		String name = item.getSubNames()[i].substring(RationMain.MODID.length()+1);
        	if(json_create)JSON_Creator.genItem(name, name, "json_create");
            ModelResourceLocation model = new ModelResourceLocation(new ResourceLocation(RationMain.MODID,name), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, i, model);
    	}
    }
    
	@SideOnly(Side.CLIENT)
    private static void registerRender(DrinkCoffee item)
    {
    	registerRender(item, false, false);
    }
	
    @SideOnly(Side.CLIENT)
    private static void registerRender(DrinkCoffee item,boolean genModel,boolean ice)
    {
    	for(int i = 0;i<item.getSubNames().length;i++){
    		String name = item.getSubNames()[i].substring(RationMain.MODID.length()+1);
    		if(genModel){
    		if(!ice)JSON_Creator.genCoffee(name, name, "json_create");
    		else JSON_Creator.genCoffeeIce(name, name.substring(0, name.length()-4), "json_create");
    		}
            ModelResourceLocation model = new ModelResourceLocation(new ResourceLocation(RationMain.MODID,name), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, i, model);
    	}
    }
	@SideOnly(Side.CLIENT)
    private static void registerRender(Item item)
    {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }
}
