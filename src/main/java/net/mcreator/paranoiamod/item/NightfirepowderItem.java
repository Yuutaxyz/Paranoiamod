
package net.mcreator.paranoiamod.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.paranoiamod.itemgroup.MobsParanoiaItemGroup;
import net.mcreator.paranoiamod.ParanoiamodModElements;

@ParanoiamodModElements.ModElement.Tag
public class NightfirepowderItem extends ParanoiamodModElements.ModElement {
	@ObjectHolder("paranoiamod:nightfirepowder")
	public static final Item block = null;
	public NightfirepowderItem(ParanoiamodModElements instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MobsParanoiaItemGroup.tab).maxStackSize(64));
			setRegistryName("nightfirepowder");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}
	}
}
