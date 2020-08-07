
package net.mcreator.paranoiamod.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.paranoiamod.ParanoiamodModElements;

@ParanoiamodModElements.ModElement.Tag
public class NightfireswordItem extends ParanoiamodModElements.ModElement {
	@ObjectHolder("paranoiamod:nightfiresword")
	public static final Item block = null;
	public NightfireswordItem(ParanoiamodModElements instance) {
		super(instance, 32);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 100;
			}

			public float getEfficiency() {
				return 10f;
			}

			public float getAttackDamage() {
				return 18f;
			}

			public int getHarvestLevel() {
				return 1;
			}

			public int getEnchantability() {
				return 10;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(NightfirepowderItem.block, (int) (1)));
			}
		}, 3, -2.5f, new Item.Properties().group(ItemGroup.COMBAT)) {
		}.setRegistryName("nightfiresword"));
	}
}
