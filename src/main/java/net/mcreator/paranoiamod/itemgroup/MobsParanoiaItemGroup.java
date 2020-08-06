
package net.mcreator.paranoiamod.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.paranoiamod.item.BrazilianlogoItem;
import net.mcreator.paranoiamod.ParanoiamodModElements;

@ParanoiamodModElements.ModElement.Tag
public class MobsParanoiaItemGroup extends ParanoiamodModElements.ModElement {
	public MobsParanoiaItemGroup(ParanoiamodModElements instance) {
		super(instance, 12);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabmobsparanoia") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(BrazilianlogoItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}
	public static ItemGroup tab;
}
