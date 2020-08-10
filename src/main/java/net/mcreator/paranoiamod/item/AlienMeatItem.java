
package net.mcreator.paranoiamod.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.mcreator.paranoiamod.itemgroup.MobsParanoiaItemGroup;
import net.mcreator.paranoiamod.ParanoiamodModElements;

@ParanoiamodModElements.ModElement.Tag
public class AlienMeatItem extends ParanoiamodModElements.ModElement {
	@ObjectHolder("paranoiamod:alien_meat")
	public static final Item block = null;
	public AlienMeatItem(ParanoiamodModElements instance) {
		super(instance, 36);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(MobsParanoiaItemGroup.tab).maxStackSize(64)
					.food((new Food.Builder()).hunger(10).saturation(0.6f).meat().build()));
			setRegistryName("alien_meat");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}
	}
}
