
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
import net.minecraft.particles.ParticleTypes;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.paranoiamod.ParanoiamodModElements;

import java.util.Random;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@ParanoiamodModElements.ModElement.Tag
public class AlphaWerewolfEntity extends ParanoiamodModElements.ModElement {
	public static EntityType entity = null;
	public AlphaWerewolfEntity(ParanoiamodModElements instance) {
		super(instance, 34);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.6f, 1.8f))
						.build("alpha_werewolf").setRegistryName("alpha_werewolf");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -6710887, -65485, new Item.Properties().group(null)).setRegistryName("alpha_werewolf"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("plains")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 5, 1, 1));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::canMonsterSpawn);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modellobisgome(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("paranoiamod:textures/lobisomem_alpha.png");
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
			this.goalSelector.addGoal(6, new BreakDoorGoal(this, e -> true));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.GOLDEN_APPLE, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("paranoiamod:lobisomemsound"));
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
			if (source.getImmediateSource() instanceof ArrowEntity)
				return false;
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
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(1.5);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(46);
		}

		public void livingTick() {
			super.livingTick();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Random random = this.rand;
			Entity entity = this;
			if (true)
				for (int l = 0; l < 4; ++l) {
					double d0 = (x + random.nextFloat());
					double d1 = (y + random.nextFloat());
					double d2 = (z + random.nextFloat());
					int i1 = random.nextInt(2) * 2 - 1;
					double d3 = (random.nextFloat() - 0.5D) * 0.5D;
					double d4 = (random.nextFloat() - 0.5D) * 0.5D;
					double d5 = (random.nextFloat() - 0.5D) * 0.5D;
					world.addParticle(ParticleTypes.FLAME, d0, d1, d2, d3, d4, d5);
				}
		}
	}

	// Made with Blockbench 3.6.5
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modellobisgome extends EntityModel<Entity> {
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
		private final ModelRenderer bone13;
		private final ModelRenderer bone14;
		private final ModelRenderer bone15;
		private final ModelRenderer bone16;
		private final ModelRenderer bone17;
		private final ModelRenderer bone18;
		private final ModelRenderer bone19;
		private final ModelRenderer bone20;
		private final ModelRenderer bb_main;
		public Modellobisgome() {
			textureWidth = 128;
			textureHeight = 128;
			bone = new ModelRenderer(this);
			bone.setRotationPoint(-4.0F, 23.0F, -4.0F);
			setRotationAngle(bone, 0.0F, -0.3054F, 0.0F);
			bone.setTextureOffset(12, 46).addBox(1.2F, -1.0F, -0.6F, 2.0F, 2.0F, 3.0F, 0.0F, false);
			bone.setTextureOffset(28, 0).addBox(6.9223F, -1.0F, -2.4042F, 2.0F, 2.0F, 3.0F, 0.0F, false);
			bone2 = new ModelRenderer(this);
			bone2.setRotationPoint(-3.0F, 23.0F, -4.0F);
			setRotationAngle(bone2, 0.0F, 0.3054F, 0.0F);
			bone2.setTextureOffset(63, 25).addBox(-1.2F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			bone2.setTextureOffset(17, 35).addBox(4.5223F, -1.0F, 1.8042F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			bone3 = new ModelRenderer(this);
			bone3.setRotationPoint(0.5F, 10.0F, -1.5F);
			setRotationAngle(bone3, 0.2618F, 0.0F, 0.0F);
			bone3.setTextureOffset(20, 29).addBox(-1.5F, -1.3F, -0.5F, 3.0F, 2.0F, 1.0F, 0.0F, false);
			bone4 = new ModelRenderer(this);
			bone4.setRotationPoint(0.5F, 5.0F, -0.5F);
			setRotationAngle(bone4, 0.1309F, 0.0F, 0.0F);
			bone4.setTextureOffset(22, 36).addBox(-4.5F, -4.0F, -1.8F, 9.0F, 6.0F, 3.0F, 0.0F, false);
			bone5 = new ModelRenderer(this);
			bone5.setRotationPoint(0.5F, 1.5F, -1.0F);
			setRotationAngle(bone5, 0.1745F, 0.0F, 0.0F);
			bone5.setTextureOffset(19, 13).addBox(-5.0F, -0.6736F, -3.2848F, 10.0F, 1.0F, 5.0F, 0.0F, false);
			bone6 = new ModelRenderer(this);
			bone6.setRotationPoint(0.5F, 0.5F, -3.0F);
			setRotationAngle(bone6, 0.2618F, 0.0F, 0.0F);
			bone6.setTextureOffset(0, 0).addBox(-5.5F, -6.0F, -2.0F, 11.0F, 7.0F, 6.0F, 0.0F, false);
			bone7 = new ModelRenderer(this);
			bone7.setRotationPoint(0.5F, 3.0F, -2.5F);
			setRotationAngle(bone7, 0.48F, 0.0F, 0.0F);
			bone7.setTextureOffset(43, 36).addBox(-4.5F, -1.0F, -0.5F, 9.0F, 2.0F, 1.0F, 0.0F, false);
			bone8 = new ModelRenderer(this);
			bone8.setRotationPoint(0.5F, -5.5F, -4.0F);
			setRotationAngle(bone8, 0.2618F, 0.0F, 0.2618F);
			bone8.setTextureOffset(0, 26).addBox(-0.2753F, -1.317F, -2.383F, 7.0F, 3.0F, 6.0F, 0.0F, false);
			bone9 = new ModelRenderer(this);
			bone9.setRotationPoint(-4.5F, -4.5F, -4.0F);
			setRotationAngle(bone9, 0.2618F, 0.0F, -0.2618F);
			bone9.setTextureOffset(20, 20).addBox(-1.7588F, -0.767F, -2.45F, 7.0F, 3.0F, 6.0F, 0.0F, false);
			bone10 = new ModelRenderer(this);
			bone10.setRotationPoint(0.5F, -7.0F, -3.5F);
			setRotationAngle(bone10, 0.3054F, 0.0F, 0.0F);
			bone10.setTextureOffset(41, 41).addBox(-3.0F, -3.0F, -2.5F, 6.0F, 5.0F, 5.0F, 0.0F, false);
			bone11 = new ModelRenderer(this);
			bone11.setRotationPoint(0.5F, -11.0F, -6.5F);
			setRotationAngle(bone11, 0.3054F, 0.0F, 0.0F);
			bone11.setTextureOffset(0, 13).addBox(-3.0F, -3.0463F, -2.8007F, 6.0F, 6.0F, 7.0F, 0.0F, false);
			bone12 = new ModelRenderer(this);
			bone12.setRotationPoint(1.0F, -8.5F, -9.0F);
			setRotationAngle(bone12, 0.2182F, 0.0F, 0.0F);
			bone12.setTextureOffset(16, 45).addBox(-2.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, 0.0F, false);
			bone13 = new ModelRenderer(this);
			bone13.setRotationPoint(1.0F, -9.5F, -10.0F);
			setRotationAngle(bone13, 0.6109F, 0.0F, 0.0F);
			bone13.setTextureOffset(0, 57).addBox(-2.0F, -0.5F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
			bone14 = new ModelRenderer(this);
			bone14.setRotationPoint(-0.5F, -15.0F, -6.5F);
			setRotationAngle(bone14, 0.3054F, 0.0F, 0.0F);
			bone14.setTextureOffset(0, 35).addBox(-3.5F, -3.0463F, -2.8007F, 6.0F, 6.0F, 5.0F, -2.0F, false);
			bone14.setTextureOffset(34, 0).addBox(-0.5F, -3.0463F, -2.8007F, 6.0F, 6.0F, 5.0F, -2.0F, false);
			bone15 = new ModelRenderer(this);
			bone15.setRotationPoint(0.0F, 24.0F, 0.0F);
			bone15.setTextureOffset(31, 50).addBox(4.5F, -28.0F, -5.0F, 3.0F, 8.0F, 3.0F, 0.0F, false);
			bone15.setTextureOffset(0, 46).addBox(-6.5F, -28.0F, -5.0F, 3.0F, 8.0F, 3.0F, 0.0F, false);
			bone16 = new ModelRenderer(this);
			bone16.setRotationPoint(6.0F, 4.5F, -4.5F);
			setRotationAngle(bone16, -0.5672F, 0.0F, 0.0F);
			bone16.setTextureOffset(43, 51).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);
			bone16.setTextureOffset(49, 11).addBox(-12.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);
			bone16.setTextureOffset(57, 63).addBox(-1.0F, 0.7241F, -1.1939F, 2.0F, 4.0F, 2.0F, 0.0F, false);
			bone16.setTextureOffset(49, 63).addBox(-12.0F, 0.7241F, -1.1939F, 2.0F, 4.0F, 2.0F, 0.0F, false);
			bone16.setTextureOffset(29, 61).addBox(-12.0F, 4.4855F, -1.8061F, 2.0F, 3.0F, 3.0F, 0.0F, false);
			bone16.setTextureOffset(11, 61).addBox(-1.0F, 4.4855F, -1.8061F, 2.0F, 3.0F, 3.0F, 0.0F, false);
			bone17 = new ModelRenderer(this);
			bone17.setRotationPoint(0.5F, 8.0F, 3.0F);
			setRotationAngle(bone17, -0.7418F, 0.0F, 0.0F);
			bone17.setTextureOffset(59, 47).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
			bone17.setTextureOffset(20, 57).addBox(-1.0F, -1.0617F, -3.4129F, 2.0F, 2.0F, 4.0F, 0.0F, false);
			bone18 = new ModelRenderer(this);
			bone18.setRotationPoint(0.5F, 9.0F, 7.0F);
			setRotationAngle(bone18, -1.0472F, 0.0F, 0.0F);
			bone18.setTextureOffset(39, 59).addBox(-1.0F, 1.7186F, -1.7901F, 2.0F, 2.0F, 4.0F, 0.0F, false);
			bone19 = new ModelRenderer(this);
			bone19.setRotationPoint(0.5F, 11.0F, 9.0F);
			setRotationAngle(bone19, -0.6545F, 0.0F, 0.0F);
			bone19.setTextureOffset(58, 39).addBox(-1.0F, 1.7186F, -1.9901F, 2.0F, 2.0F, 4.0F, 0.0F, false);
			bone20 = new ModelRenderer(this);
			bone20.setRotationPoint(0.5F, 12.0F, 11.0F);
			setRotationAngle(bone20, -0.1309F, 0.0F, 0.0F);
			bone20.setTextureOffset(58, 8).addBox(-1.0F, 1.7186F, -1.9901F, 2.0F, 2.0F, 4.0F, 0.0F, false);
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			bb_main.setTextureOffset(56, 0).addBox(-4.0F, -10.0F, -2.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
			bb_main.setTextureOffset(21, 63).addBox(-3.5F, -5.0F, -1.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			bb_main.setTextureOffset(63, 34).addBox(-3.5F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			bb_main.setTextureOffset(55, 55).addBox(2.0F, -10.0F, -2.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 62).addBox(2.5F, -5.0F, -1.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			bb_main.setTextureOffset(47, 59).addBox(2.5F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 29).addBox(-3.5F, -11.0F, -2.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 26).addBox(2.5F, -11.0F, -2.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(54, 26).addBox(2.0F, -15.0F, -2.3F, 3.0F, 5.0F, 3.0F, 0.0F, false);
			bb_main.setTextureOffset(12, 53).addBox(-4.0F, -15.0F, -2.3F, 3.0F, 5.0F, 3.0F, 0.0F, false);
			bb_main.setTextureOffset(60, 16).addBox(2.0F, -15.0F, -2.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
			bb_main.setTextureOffset(28, 45).addBox(-4.0F, -15.0F, -2.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
			bb_main.setTextureOffset(26, 29).addBox(-4.5F, -18.0F, -2.5F, 10.0F, 3.0F, 4.0F, 0.0F, false);
			bb_main.setTextureOffset(60, 21).addBox(-1.0F, -15.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 13).addBox(-1.5F, -15.0F, -1.75F, 1.0F, 4.0F, 2.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 0).addBox(1.5F, -15.0F, -1.75F, 1.0F, 4.0F, 2.0F, 0.0F, false);
			bb_main.setTextureOffset(40, 19).addBox(-4.0F, -24.0F, 0.0F, 9.0F, 6.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(52, 52).addBox(-5.0F, -17.0F, -9.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(51, 27).addBox(4.0F, -17.0F, -9.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(51, 0).addBox(4.0F, -14.0F, -10.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(40, 51).addBox(-5.0F, -14.0F, -10.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(46, 39).addBox(-5.0F, -13.0F, -9.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(46, 26).addBox(4.0F, -13.0F, -9.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(9, 46).addBox(4.0F, -13.5F, -8.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(45, 12).addBox(-5.0F, -13.5F, -8.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(40, 11).addBox(5.0F, -14.0F, -9.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(34, 11).addBox(-6.0F, -14.0F, -9.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
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
			bone13.render(matrixStack, buffer, packedLight, packedOverlay);
			bone14.render(matrixStack, buffer, packedLight, packedOverlay);
			bone15.render(matrixStack, buffer, packedLight, packedOverlay);
			bone16.render(matrixStack, buffer, packedLight, packedOverlay);
			bone17.render(matrixStack, buffer, packedLight, packedOverlay);
			bone18.render(matrixStack, buffer, packedLight, packedOverlay);
			bone19.render(matrixStack, buffer, packedLight, packedOverlay);
			bone20.render(matrixStack, buffer, packedLight, packedOverlay);
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
