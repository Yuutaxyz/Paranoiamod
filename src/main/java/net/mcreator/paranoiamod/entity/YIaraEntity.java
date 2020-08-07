
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
import net.minecraft.world.IWorldReader;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.entity.ai.controller.MovementController;
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

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@ParanoiamodModElements.ModElement.Tag
public class YIaraEntity extends ParanoiamodModElements.ModElement {
	public static EntityType entity = null;
	public YIaraEntity(ParanoiamodModElements instance) {
		super(instance, 30);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.6f, 1.8f)).build("y_iara")
						.setRegistryName("y_iara");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -8618996, -455925, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("y_iara"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("swamp")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 12, 1, 1));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::canMonsterSpawn);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modelsereia(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("paranoiamod:textures/sereiafdstexture.png");
				}
			};
		});
	}
	public static class CustomEntity extends ZombieEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			this.moveController = new MovementController(this) {
				@Override
				public void tick() {
					if (CustomEntity.this.areEyesInFluid(FluidTags.WATER))
						CustomEntity.this.setMotion(CustomEntity.this.getMotion().add(0, 0.005, 0));
					if (this.action == MovementController.Action.MOVE_TO && !CustomEntity.this.getNavigator().noPath()) {
						double dx = this.posX - CustomEntity.this.getPosX();
						double dy = this.posY - CustomEntity.this.getPosY();
						double dz = this.posZ - CustomEntity.this.getPosZ();
						dy = dy / (double) MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
						CustomEntity.this.rotationYaw = this.limitAngle(CustomEntity.this.rotationYaw,
								(float) (MathHelper.atan2(dz, dx) * (double) (180 / (float) Math.PI)) - 90, 90);
						CustomEntity.this.renderYawOffset = CustomEntity.this.rotationYaw;
						CustomEntity.this.setAIMoveSpeed(MathHelper.lerp(0.125f, CustomEntity.this.getAIMoveSpeed(),
								(float) (this.speed * CustomEntity.this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue())));
						CustomEntity.this.setMotion(CustomEntity.this.getMotion().add(0, CustomEntity.this.getAIMoveSpeed() * dy * 0.1, 0));
					} else {
						CustomEntity.this.setAIMoveSpeed(0);
					}
				}
			};
			this.navigator = new SwimmerPathNavigator(this, this.world);
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
			this.goalSelector.addGoal(5, new BreakDoorGoal(this, e -> true));
			this.goalSelector.addGoal(6, new SwimGoal(this));
			this.goalSelector.addGoal(7, new RandomSwimmingGoal(this, 5, 40));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEAD;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.TRIDENT, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("paranoiamod:sirensound"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.9);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(16);
		}

		@Override
		public boolean canBreatheUnderwater() {
			return true;
		}

		@Override
		public boolean isNotColliding(IWorldReader worldreader) {
			return worldreader.checkNoEntityCollision(this, VoxelShapes.create(this.getBoundingBox()));
		}

		@Override
		public boolean isPushedByWater() {
			return false;
		}
	}

	// Made with Blockbench 3.6.5
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelsereia extends EntityModel<Entity> {
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
		private final ModelRenderer bone21;
		private final ModelRenderer bone22;
		private final ModelRenderer bone23;
		private final ModelRenderer bone24;
		private final ModelRenderer bone25;
		private final ModelRenderer bone26;
		private final ModelRenderer bone27;
		private final ModelRenderer bone28;
		private final ModelRenderer bone29;
		private final ModelRenderer bone30;
		public Modelsereia() {
			textureWidth = 128;
			textureHeight = 128;
			bone = new ModelRenderer(this);
			bone.setRotationPoint(0.0F, 18.0F, -2.0F);
			setRotationAngle(bone, 0.3927F, 0.0F, 0.0F);
			bone.setTextureOffset(0, 25).addBox(-3.0F, -3.9239F, -1.6173F, 6.0F, 7.0F, 4.0F, 0.0F, false);
			bone2 = new ModelRenderer(this);
			bone2.setRotationPoint(0.0F, 22.0F, 0.5F);
			setRotationAngle(bone2, 0.7418F, 0.0F, 0.0F);
			bone2.setTextureOffset(40, 0).addBox(-2.5F, -2.4129F, -1.5617F, 5.0F, 3.0F, 4.0F, 0.0F, false);
			bone3 = new ModelRenderer(this);
			bone3.setRotationPoint(0.0F, 23.0F, 3.5F);
			setRotationAngle(bone3, 1.4835F, 0.0F, 0.0F);
			bone3.setTextureOffset(53, 14).addBox(-2.5F, -3.3038F, -1.0F, 5.0F, 4.0F, 3.0F, 0.0F, false);
			bone4 = new ModelRenderer(this);
			bone4.setRotationPoint(0.0F, 23.0F, 6.0F);
			setRotationAngle(bone4, 0.0873F, 0.0F, 0.0F);
			bone4.setTextureOffset(20, 0).addBox(-2.0F, -1.5F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
			bone5 = new ModelRenderer(this);
			bone5.setRotationPoint(0.0F, 21.0F, 9.0F);
			setRotationAngle(bone5, 0.6109F, 0.0F, 0.0F);
			bone5.setTextureOffset(54, 27).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
			bone6 = new ModelRenderer(this);
			bone6.setRotationPoint(0.0F, 19.0F, 11.0F);
			setRotationAngle(bone6, 0.9163F, 0.0F, 0.0F);
			bone6.setTextureOffset(54, 21).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
			bone7 = new ModelRenderer(this);
			bone7.setRotationPoint(-1.5F, 15.0F, 12.0F);
			setRotationAngle(bone7, 0.0F, -0.0873F, -0.5672F);
			bone7.setTextureOffset(58, 42).addBox(-1.6598F, -3.0F, -0.0735F, 4.0F, 6.0F, 0.0F, 0.0F, false);
			bone8 = new ModelRenderer(this);
			bone8.setRotationPoint(1.5F, 16.0F, 12.0F);
			setRotationAngle(bone8, 0.0F, 0.0873F, 0.5672F);
			bone8.setTextureOffset(58, 0).addBox(-3.3402F, -3.4627F, -0.0735F, 4.0F, 6.0F, 0.0F, 0.0F, false);
			bone9 = new ModelRenderer(this);
			bone9.setRotationPoint(0.0F, 14.0F, -3.5F);
			setRotationAngle(bone9, 0.2182F, 0.0F, 0.0F);
			bone9.setTextureOffset(0, 0).addBox(-3.5F, -2.0F, -2.0F, 7.0F, 4.0F, 6.0F, 0.0F, false);
			bone10 = new ModelRenderer(this);
			bone10.setRotationPoint(0.0F, 15.0F, -1.0F);
			setRotationAngle(bone10, -0.3927F, 0.0F, 0.0F);
			bone10.setTextureOffset(0, 20).addBox(-3.0F, -1.0F, -0.5F, 6.0F, 2.0F, 2.0F, 0.0F, false);
			bone11 = new ModelRenderer(this);
			bone11.setRotationPoint(0.0F, 9.5F, -4.0F);
			setRotationAngle(bone11, 0.0873F, 0.0F, 0.0F);
			bone11.setTextureOffset(24, 6).addBox(-3.0F, -3.5F, -1.5F, 6.0F, 7.0F, 4.0F, 0.0F, false);
			bone12 = new ModelRenderer(this);
			bone12.setRotationPoint(0.0F, 4.5F, -4.0F);
			setRotationAngle(bone12, -0.0436F, 0.0F, 0.0F);
			bone12.setTextureOffset(0, 10).addBox(-3.5F, -2.5F, -2.5F, 7.0F, 5.0F, 5.0F, 0.0F, false);
			bone13 = new ModelRenderer(this);
			bone13.setRotationPoint(0.0F, 4.5F, -5.0F);
			setRotationAngle(bone13, -0.48F, 0.0F, 0.0F);
			bone13.setTextureOffset(0, 58).addBox(0.3F, -1.5F, -2.5F, 3.0F, 4.0F, 2.0F, 0.0F, false);
			bone13.setTextureOffset(55, 33).addBox(-3.3F, -1.5F, -2.5F, 3.0F, 4.0F, 2.0F, 0.0F, false);
			bone14 = new ModelRenderer(this);
			bone14.setRotationPoint(0.0F, 0.5F, -4.0F);
			setRotationAngle(bone14, -0.0873F, 0.0F, 0.2182F);
			bone14.setTextureOffset(48, 7).addBox(0.5F, 0.3F, -2.28F, 3.0F, 2.0F, 5.0F, 0.0F, false);
			bone15 = new ModelRenderer(this);
			bone15.setRotationPoint(-4.0F, 0.5F, -4.0F);
			setRotationAngle(bone15, -0.0873F, 0.0F, -0.2182F);
			bone15.setTextureOffset(44, 35).addBox(0.5F, 1.2F, -2.28F, 3.0F, 2.0F, 5.0F, 0.0F, false);
			bone16 = new ModelRenderer(this);
			bone16.setRotationPoint(-2.0F, 0.5F, -4.0F);
			setRotationAngle(bone16, -0.0873F, 0.0F, 0.0F);
			bone16.setTextureOffset(53, 53).addBox(1.0F, 0.5F, -2.28F, 2.0F, 2.0F, 5.0F, 0.0F, false);
			bone17 = new ModelRenderer(this);
			bone17.setRotationPoint(0.0F, 0.0F, -4.0F);
			setRotationAngle(bone17, 0.1309F, 0.0F, 0.0F);
			bone17.setTextureOffset(34, 51).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 4.0F, 4.0F, 0.0F, false);
			bone18 = new ModelRenderer(this);
			bone18.setRotationPoint(0.0F, -3.0F, -5.0F);
			setRotationAngle(bone18, 0.0873F, 0.0F, 0.0F);
			bone18.setTextureOffset(18, 18).addBox(-2.5F, -2.5F, -3.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);
			bone19 = new ModelRenderer(this);
			bone19.setRotationPoint(0.0F, -6.0F, -6.0F);
			setRotationAngle(bone19, 0.0873F, 0.0F, 0.0F);
			bone19.setTextureOffset(40, 7).addBox(-2.5F, 0.5F, -3.0F, 5.0F, 2.0F, 1.0F, 0.0F, false);
			bone20 = new ModelRenderer(this);
			bone20.setRotationPoint(0.0F, -8.0F, -6.0F);
			setRotationAngle(bone20, 0.0873F, 0.0F, 0.0F);
			bone20.setTextureOffset(20, 29).addBox(-2.5F, 2.0F, -2.5F, 5.0F, 1.0F, 6.0F, 0.0F, false);
			bone21 = new ModelRenderer(this);
			bone21.setRotationPoint(0.0F, -1.0F, -3.0F);
			setRotationAngle(bone21, 0.4363F, 0.0F, 0.0F);
			bone21.setTextureOffset(39, 12).addBox(1.0F, -4.5F, -3.0F, 2.0F, 7.0F, 5.0F, 0.0F, false);
			bone22 = new ModelRenderer(this);
			bone22.setRotationPoint(-4.0F, -1.0F, -3.0F);
			setRotationAngle(bone22, 0.4363F, 0.0F, 0.0F);
			bone22.setTextureOffset(30, 36).addBox(1.0F, -4.5F, -3.0F, 2.0F, 7.0F, 5.0F, 0.0F, false);
			bone23 = new ModelRenderer(this);
			bone23.setRotationPoint(0.0F, -0.5F, -1.0F);
			setRotationAngle(bone23, 0.4363F, 0.0F, 0.0F);
			bone23.setTextureOffset(0, 48).addBox(-2.5F, -5.5F, -1.0F, 5.0F, 8.0F, 2.0F, 0.0F, false);
			bone24 = new ModelRenderer(this);
			bone24.setRotationPoint(0.0F, 8.5F, 1.0F);
			setRotationAngle(bone24, 0.1745F, 0.0F, 0.0F);
			bone24.setTextureOffset(16, 36).addBox(-2.5F, -7.5F, -1.0F, 5.0F, 10.0F, 2.0F, 0.0F, false);
			bone25 = new ModelRenderer(this);
			bone25.setRotationPoint(3.0F, 7.5F, 1.0F);
			setRotationAngle(bone25, 0.1745F, 0.0F, -0.4363F);
			bone25.setTextureOffset(44, 44).addBox(-2.5F, -6.5F, -1.0F, 5.0F, 9.0F, 2.0F, 0.0F, false);
			bone26 = new ModelRenderer(this);
			bone26.setRotationPoint(-3.0F, 7.5F, 1.0F);
			setRotationAngle(bone26, 0.1745F, 0.0F, 0.4363F);
			bone26.setTextureOffset(40, 24).addBox(-2.5F, -6.5F, -1.0F, 5.0F, 9.0F, 2.0F, 0.0F, false);
			bone27 = new ModelRenderer(this);
			bone27.setRotationPoint(0.0F, 8.5F, -1.0F);
			setRotationAngle(bone27, 0.3491F, 0.0F, 0.0F);
			bone27.setTextureOffset(0, 36).addBox(-2.5F, -8.3794F, -0.316F, 5.0F, 9.0F, 3.0F, 0.0F, false);
			bone28 = new ModelRenderer(this);
			bone28.setRotationPoint(0.0F, 10.5F, 1.0F);
			setRotationAngle(bone28, 0.829F, 0.0F, 0.0F);
			bone28.setTextureOffset(32, 0).addBox(-2.5F, -2.3794F, -0.316F, 5.0F, 3.0F, 1.0F, 0.0F, false);
			bone29 = new ModelRenderer(this);
			bone29.setRotationPoint(3.5F, 7.5F, -3.3F);
			setRotationAngle(bone29, 0.0F, 0.0F, -0.1309F);
			bone29.setTextureOffset(24, 48).addBox(-1.0F, -5.5F, -1.5F, 2.0F, 11.0F, 3.0F, 0.0F, false);
			bone30 = new ModelRenderer(this);
			bone30.setRotationPoint(-3.5F, 7.5F, -3.3F);
			setRotationAngle(bone30, 0.0F, 0.0F, 0.1309F);
			bone30.setTextureOffset(14, 48).addBox(-1.0F, -5.5F, -1.5F, 2.0F, 11.0F, 3.0F, 0.0F, false);
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
			bone21.render(matrixStack, buffer, packedLight, packedOverlay);
			bone22.render(matrixStack, buffer, packedLight, packedOverlay);
			bone23.render(matrixStack, buffer, packedLight, packedOverlay);
			bone24.render(matrixStack, buffer, packedLight, packedOverlay);
			bone25.render(matrixStack, buffer, packedLight, packedOverlay);
			bone26.render(matrixStack, buffer, packedLight, packedOverlay);
			bone27.render(matrixStack, buffer, packedLight, packedOverlay);
			bone28.render(matrixStack, buffer, packedLight, packedOverlay);
			bone29.render(matrixStack, buffer, packedLight, packedOverlay);
			bone30.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
