
package net.mcreator.paranoiamod.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.material.Material;

import net.mcreator.paranoiamod.ParanoiamodModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@ParanoiamodModElements.ModElement.Tag
public class ArmadiloEntity extends ParanoiamodModElements.ModElement {
	public static EntityType entity = null;
	public ArmadiloEntity(ParanoiamodModElements instance) {
		super(instance, 22);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("armadilo")
						.setRegistryName("armadilo");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -2237120, -1583602, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("armadilo"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("savanna")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("savanna_plateau")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 20, 4, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos,
						random) -> (world.getBlockState(pos.down()).getMaterial() == Material.ORGANIC && world.getLightSubtracted(pos, 0) > 8));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modeltatu(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("paranoiamod:textures/tatutexture.png");
				}
			};
		});
	}
	public static class CustomEntity extends OcelotEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(5, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.GOLD_NUGGET, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.CACTUS)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}
	}

	// Made with Blockbench 3.6.5
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modeltatu extends EntityModel<Entity> {
		private final ModelRenderer bone;
		private final ModelRenderer bone2;
		private final ModelRenderer bone3;
		private final ModelRenderer bone4;
		private final ModelRenderer bone5;
		private final ModelRenderer bone6;
		private final ModelRenderer bone7;
		private final ModelRenderer bone8;
		private final ModelRenderer bone9;
		private final ModelRenderer bone10;
		private final ModelRenderer bone11;
		private final ModelRenderer bone12;
		private final ModelRenderer bb_main;
		public Modeltatu() {
			textureWidth = 64;
			textureHeight = 64;
			bone = new ModelRenderer(this);
			bone.setRotationPoint(0.0F, 21.0F, -3.0F);
			setRotationAngle(bone, 0.2618F, 0.0F, 0.0F);
			bone.setTextureOffset(24, 0).addBox(-1.5F, -1.0681F, -0.5176F, 3.0F, 3.0F, 2.0F, 0.0F, false);
			bone.setTextureOffset(25, 18).addBox(-1.0F, -0.1341F, -1.4836F, 2.0F, 2.0F, 1.0F, 0.0F, false);
			bone2 = new ModelRenderer(this);
			bone2.setRotationPoint(0.0F, 21.5F, -4.5F);
			setRotationAngle(bone2, 0.3491F, 0.0F, 0.0F);
			bone2.setTextureOffset(24, 11).addBox(-0.5F, 0.1397F, -1.842F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			bone3 = new ModelRenderer(this);
			bone3.setRotationPoint(0.0F, 21.7F, -5.5F);
			setRotationAngle(bone3, 0.1745F, 0.0F, 0.0F);
			bone3.setTextureOffset(0, 4).addBox(-0.5F, 0.6585F, -0.6888F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			bone4 = new ModelRenderer(this);
			bone4.setRotationPoint(0.0F, 21.5F, -6.5F);
			setRotationAngle(bone4, 0.6109F, 0.0F, 0.0F);
			bone4.setTextureOffset(17, 0).addBox(-0.5F, 0.6927F, -0.5544F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bone5 = new ModelRenderer(this);
			bone5.setRotationPoint(-0.8F, 18.5F, -2.5F);
			setRotationAngle(bone5, 0.3491F, 0.0F, 0.0F);
			bone5.setTextureOffset(0, 4).addBox(-0.5F, -0.0603F, -0.842F, 1.0F, 2.0F, 0.0F, 0.0F, false);
			bone5.setTextureOffset(0, 0).addBox(1.1F, -0.0603F, -0.842F, 1.0F, 2.0F, 0.0F, 0.0F, false);
			bone6 = new ModelRenderer(this);
			bone6.setRotationPoint(2.0F, 21.0F, 1.0F);
			setRotationAngle(bone6, -0.2618F, 0.0F, 0.0F);
			bone6.setTextureOffset(22, 25).addBox(0.0F, -0.0341F, -0.7412F, 1.0F, 2.0F, 2.0F, 0.0F, false);
			bone6.setTextureOffset(16, 25).addBox(-5.0F, -0.0341F, -0.7412F, 1.0F, 2.0F, 2.0F, 0.0F, false);
			bone7 = new ModelRenderer(this);
			bone7.setRotationPoint(-2.0F, 21.3F, 2.5F);
			setRotationAngle(bone7, -0.2182F, 0.0F, 0.0F);
			bone7.setTextureOffset(17, 0).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 2.0F, 5.0F, 0.0F, false);
			bone7.setTextureOffset(11, 13).addBox(4.0F, -1.0F, -2.0F, 1.0F, 2.0F, 5.0F, 0.0F, false);
			bone7.setTextureOffset(19, 19).addBox(4.0F, -2.1927F, -1.2401F, 1.0F, 2.0F, 4.0F, 0.0F, false);
			bone7.setTextureOffset(18, 12).addBox(-1.0F, -2.1927F, -1.2401F, 1.0F, 2.0F, 4.0F, 0.0F, false);
			bone8 = new ModelRenderer(this);
			bone8.setRotationPoint(0.0F, 20.0F, 6.0F);
			setRotationAngle(bone8, -0.3927F, 0.0F, 0.0F);
			bone8.setTextureOffset(24, 7).addBox(-0.5F, -1.4239F, -1.3827F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			bone9 = new ModelRenderer(this);
			bone9.setRotationPoint(0.0F, 21.0F, 8.5F);
			setRotationAngle(bone9, -0.5236F, 0.0F, 0.0F);
			bone10 = new ModelRenderer(this);
			bone10.setRotationPoint(0.0F, 21.5F, 10.5F);
			setRotationAngle(bone10, -0.2618F, 0.0F, 0.0F);
			bone10.setTextureOffset(8, 20).addBox(-0.5F, -1.4659F, -3.2588F, 1.0F, 1.0F, 4.0F, 0.0F, false);
			bone11 = new ModelRenderer(this);
			bone11.setRotationPoint(0.0F, 21.6F, 10.0F);
			setRotationAngle(bone11, -0.1309F, 0.0F, 0.0F);
			bone12 = new ModelRenderer(this);
			bone12.setRotationPoint(0.0F, 19.5F, -0.5F);
			setRotationAngle(bone12, 0.5672F, 0.0F, 0.0F);
			bone12.setTextureOffset(0, 18).addBox(-1.5F, -0.9F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			bb_main.setTextureOffset(0, 23).addBox(-1.5F, -4.5F, -2.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 0).addBox(-2.5F, -6.0F, 0.0F, 5.0F, 5.0F, 7.0F, 0.0F, false);
			bb_main.setTextureOffset(10, 25).addBox(2.0F, -2.7F, 4.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 0).addBox(-3.0F, -2.7F, 4.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
			bb_main.setTextureOffset(11, 14).addBox(-2.5F, -1.0F, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 14).addBox(1.0F, -1.0F, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(11, 12).addBox(-2.5F, -1.0F, 4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 12).addBox(1.5F, -1.0F, 4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 12).addBox(-1.5F, -6.5F, 0.5F, 3.0F, 1.0F, 5.0F, 0.0F, false);
			bb_main.setTextureOffset(14, 20).addBox(-1.5F, -5.0F, -1.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		}

		@Override
		public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			// previously the render function, render code was moved to a method below
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			bone.render(matrixStack, buffer, packedLight, packedOverlay);
			bone2.render(matrixStack, buffer, packedLight, packedOverlay);
			bone3.render(matrixStack, buffer, packedLight, packedOverlay);
			bone4.render(matrixStack, buffer, packedLight, packedOverlay);
			bone5.render(matrixStack, buffer, packedLight, packedOverlay);
			bone6.render(matrixStack, buffer, packedLight, packedOverlay);
			bone7.render(matrixStack, buffer, packedLight, packedOverlay);
			bone8.render(matrixStack, buffer, packedLight, packedOverlay);
			bone9.render(matrixStack, buffer, packedLight, packedOverlay);
			bone10.render(matrixStack, buffer, packedLight, packedOverlay);
			bone11.render(matrixStack, buffer, packedLight, packedOverlay);
			bone12.render(matrixStack, buffer, packedLight, packedOverlay);
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
