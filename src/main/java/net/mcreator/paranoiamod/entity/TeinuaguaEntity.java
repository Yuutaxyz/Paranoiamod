
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.paranoiamod.item.LizardRubyGemItem;
import net.mcreator.paranoiamod.ParanoiamodModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@ParanoiamodModElements.ModElement.Tag
public class TeinuaguaEntity extends ParanoiamodModElements.ModElement {
	public static EntityType entity = null;
	public TeinuaguaEntity(ParanoiamodModElements instance) {
		super(instance, 21);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.AMBIENT).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.6f, 1.8f))
						.build("teinuagua").setRegistryName("teinuagua");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -65536, -16777216, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("teinuagua"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("desert")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("savanna")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.AMBIENT).add(new Biome.SpawnListEntry(entity, 19, 4, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canSpawnOn);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modelsalamandra(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("paranoiamod:textures/teinigua.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
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
			this.entityDropItem(new ItemStack(LizardRubyGemItem.block, (int) (1)));
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
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.LIGHTNING_BOLT)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(13);
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
	public static class Modelsalamandra extends EntityModel<Entity> {
		private final ModelRenderer bone;
		private final ModelRenderer bone2;
		private final ModelRenderer bone4;
		private final ModelRenderer bone3;
		private final ModelRenderer bone5;
		private final ModelRenderer bone6;
		private final ModelRenderer bone7;
		private final ModelRenderer bone8;
		private final ModelRenderer bone9;
		private final ModelRenderer bone10;
		private final ModelRenderer bone11;
		private final ModelRenderer bone12;
		private final ModelRenderer bone13;
		private final ModelRenderer bone14;
		private final ModelRenderer bb_main;
		public Modelsalamandra() {
			textureWidth = 32;
			textureHeight = 32;
			bone = new ModelRenderer(this);
			bone.setRotationPoint(0.0F, 21.5F, -7.0F);
			setRotationAngle(bone, 0.48F, 0.0F, 0.0F);
			bone.setTextureOffset(6, 14).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			bone2 = new ModelRenderer(this);
			bone2.setRotationPoint(1.0F, 22.0F, -8.5F);
			setRotationAngle(bone2, -1.0036F, 0.0F, 0.0F);
			bone2.setTextureOffset(0, 18).addBox(0.0F, -3.5F, 0.0F, 0.0F, 3.0F, 1.0F, 0.0F, false);
			bone2.setTextureOffset(0, 11).addBox(-2.0F, -3.5F, 0.0F, 0.0F, 3.0F, 1.0F, 0.0F, false);
			bone4 = new ModelRenderer(this);
			bone4.setRotationPoint(0.0F, 21.5F, 0.5F);
			setRotationAngle(bone4, -0.1309F, 0.0F, 0.0F);
			bone4.setTextureOffset(18, 4).addBox(-0.5F, -0.4F, -0.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bone3 = new ModelRenderer(this);
			bone3.setRotationPoint(0.0F, 22.5F, 1.5F);
			setRotationAngle(bone3, -0.48F, 0.0F, 0.0F);
			bone3.setTextureOffset(0, 16).addBox(-0.5F, -0.85F, -1.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			bone5 = new ModelRenderer(this);
			bone5.setRotationPoint(0.0F, -0.2F, 0.8F);
			bone3.addChild(bone5);
			setRotationAngle(bone5, -0.0436F, -0.3054F, 0.2182F);
			bone5.setTextureOffset(17, 18).addBox(-0.7F, -0.55F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bone6 = new ModelRenderer(this);
			bone6.setRotationPoint(-0.5F, 23.1F, 3.0F);
			setRotationAngle(bone6, -0.3491F, -0.4363F, 0.3927F);
			bone6.setTextureOffset(14, 14).addBox(-0.5F, -0.6F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			bone7 = new ModelRenderer(this);
			bone7.setRotationPoint(-1.7F, 23.3F, 5.2F);
			setRotationAngle(bone7, -0.2618F, -0.9599F, 0.6109F);
			bone7.setTextureOffset(0, 12).addBox(-1.0F, -0.7F, -1.1F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			bone8 = new ModelRenderer(this);
			bone8.setRotationPoint(-3.7F, 22.3F, 7.2F);
			setRotationAngle(bone8, -0.2618F, -0.1745F, 0.6109F);
			bone8.setTextureOffset(17, 0).addBox(-0.4F, -0.1F, -1.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bone9 = new ModelRenderer(this);
			bone9.setRotationPoint(-2.7F, 22.3F, 8.2F);
			setRotationAngle(bone9, -0.3054F, 0.3491F, 0.6109F);
			bone9.setTextureOffset(14, 17).addBox(-0.5F, 0.5F, -1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bone10 = new ModelRenderer(this);
			bone10.setRotationPoint(-1.7F, 23.3F, 9.2F);
			setRotationAngle(bone10, -0.3054F, 0.7418F, 0.6545F);
			bone11 = new ModelRenderer(this);
			bone11.setRotationPoint(1.5F, 23.5F, -0.5F);
			setRotationAngle(bone11, 0.5236F, 0.0F, 0.0F);
			bone11.setTextureOffset(10, 17).addBox(-0.5F, -0.5F, -0.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bone11.setTextureOffset(16, 10).addBox(-3.5F, -0.5F, -0.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bone12 = new ModelRenderer(this);
			bone12.setRotationPoint(-1.5F, 22.5F, -3.5F);
			setRotationAngle(bone12, -0.1745F, 0.0F, 0.0F);
			bone12.setTextureOffset(8, 7).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			bone12.setTextureOffset(0, 0).addBox(2.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			bone13 = new ModelRenderer(this);
			bone13.setRotationPoint(-1.5F, 23.5F, -3.5F);
			setRotationAngle(bone13, -0.5236F, 0.0F, 0.0F);
			bone13.setTextureOffset(12, 14).addBox(-0.5F, -0.3F, -0.9F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bone13.setTextureOffset(5, 12).addBox(2.5F, -0.3F, -0.9F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bone14 = new ModelRenderer(this);
			bone14.setRotationPoint(-1.5F, 22.5F, -0.5F);
			setRotationAngle(bone14, -1.0472F, 0.0F, 0.0F);
			bone14.setTextureOffset(6, 17).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			bone14.setTextureOffset(0, 6).addBox(2.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			bb_main.setTextureOffset(11, 4).addBox(-1.0F, -2.0F, -8.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
			bb_main.setTextureOffset(16, 8).addBox(-1.0F, -3.0F, -6.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(9, 9).addBox(-1.0F, -2.8F, -6.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 0).addBox(-1.5F, -3.0F, -4.0F, 3.0F, 2.0F, 4.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 6).addBox(-1.0F, -2.7F, -4.5F, 2.0F, 2.0F, 4.0F, 0.0F, false);
			bb_main.setTextureOffset(10, 0).addBox(-1.0F, -3.5F, -3.5F, 2.0F, 1.0F, 3.0F, 0.0F, false);
			bb_main.setTextureOffset(9, 0).addBox(1.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 9).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(7, 6).addBox(-2.0F, 0.0F, -4.7F, 1.0F, 0.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 3).addBox(1.0F, 0.0F, -4.7F, 1.0F, 0.0F, 1.0F, 0.0F, false);
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
			bone4.render(matrixStack, buffer, packedLight, packedOverlay);
			bone3.render(matrixStack, buffer, packedLight, packedOverlay);
			bone6.render(matrixStack, buffer, packedLight, packedOverlay);
			bone7.render(matrixStack, buffer, packedLight, packedOverlay);
			bone8.render(matrixStack, buffer, packedLight, packedOverlay);
			bone9.render(matrixStack, buffer, packedLight, packedOverlay);
			bone10.render(matrixStack, buffer, packedLight, packedOverlay);
			bone11.render(matrixStack, buffer, packedLight, packedOverlay);
			bone12.render(matrixStack, buffer, packedLight, packedOverlay);
			bone13.render(matrixStack, buffer, packedLight, packedOverlay);
			bone14.render(matrixStack, buffer, packedLight, packedOverlay);
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
