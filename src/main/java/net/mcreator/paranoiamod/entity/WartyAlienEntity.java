
package net.mcreator.paranoiamod.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.OpenDoorGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.BlockState;

import net.mcreator.paranoiamod.item.AlienMeatItem;
import net.mcreator.paranoiamod.ParanoiamodModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@ParanoiamodModElements.ModElement.Tag
public class WartyAlienEntity extends ParanoiamodModElements.ModElement {
	public static EntityType entity = null;
	public WartyAlienEntity(ParanoiamodModElements instance) {
		super(instance, 35);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.6f, 1.8f))
						.build("warty_alien").setRegistryName("warty_alien");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -12763902, -648164, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("warty_alien"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelVarginha(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("paranoiamod:textures/varginhatexture.png");
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
			experienceValue = 10;
			setNoAI(false);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new OpenDoorGoal(this, false));
			this.goalSelector.addGoal(2, new FollowMobGoal(this, (float) 1, 10, 5));
			this.goalSelector.addGoal(3, new OpenDoorGoal(this, true));
			this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(5, new BreakDoorGoal(this, e -> true));
			this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(7, new SwimGoal(this));
			this.goalSelector.addGoal(8, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(9, new RandomWalkingGoal(this, 1));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEAD;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(AlienMeatItem.block, (int) (1)));
		}

		@Override
		public void playStepSound(BlockPos pos, BlockState blockIn) {
			this.playSound((net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("paranoiamod:etsom")), 0.15f,
					1);
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
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.9);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10);
		}
	}

	// Made with Blockbench 3.6.5
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelVarginha extends EntityModel<Entity> {
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
		private final ModelRenderer bb_main;
		public ModelVarginha() {
			textureWidth = 64;
			textureHeight = 64;
			bone = new ModelRenderer(this);
			bone.setRotationPoint(0.0F, 10.0F, -3.0F);
			setRotationAngle(bone, 0.48F, 0.0F, 0.0F);
			bone.setTextureOffset(0, 0).addBox(-3.0F, -6.548F, -1.153F, 6.0F, 6.0F, 6.0F, 0.0F, false);
			bone2 = new ModelRenderer(this);
			bone2.setRotationPoint(0.0F, 12.5F, 0.0F);
			setRotationAngle(bone2, 1.0036F, 0.0F, 0.0F);
			bone2.setTextureOffset(0, 0).addBox(-1.0F, -3.6492F, 2.3736F, 2.0F, 3.0F, 2.0F, 0.0F, false);
			bone3 = new ModelRenderer(this);
			bone3.setRotationPoint(0.0F, 14.5F, 2.0F);
			setRotationAngle(bone3, 0.3927F, 0.0F, 0.0F);
			bone3.setTextureOffset(0, 0).addBox(-2.5F, -5.6955F, -0.4693F, 5.0F, 5.0F, 4.0F, 0.0F, false);
			bone4 = new ModelRenderer(this);
			bone4.setRotationPoint(0.0F, 16.5F, 3.0F);
			setRotationAngle(bone4, 0.2182F, 0.0F, 0.0F);
			bone4.setTextureOffset(0, 0).addBox(-1.5F, -5.8004F, -0.8018F, 3.0F, 5.0F, 3.0F, 0.0F, false);
			bone5 = new ModelRenderer(this);
			bone5.setRotationPoint(0.0F, 24.0F, 0.0F);
			bone5.setTextureOffset(0, 0).addBox(-2.0F, -9.0F, 1.2F, 4.0F, 2.0F, 4.0F, 0.0F, false);
			bone6 = new ModelRenderer(this);
			bone6.setRotationPoint(-1.0F, 18.5F, 0.0F);
			setRotationAngle(bone6, -0.9163F, 0.0F, 0.0F);
			bone6.setTextureOffset(0, 0).addBox(-0.5F, -3.5F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
			bone7 = new ModelRenderer(this);
			bone7.setRotationPoint(1.0F, 18.5F, 0.0F);
			setRotationAngle(bone7, -0.9163F, 0.0F, 0.0F);
			bone7.setTextureOffset(0, 0).addBox(-0.5F, -3.5F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
			bone8 = new ModelRenderer(this);
			bone8.setRotationPoint(1.0F, 21.5F, 1.0F);
			setRotationAngle(bone8, 0.829F, 0.0F, 0.0F);
			bone8.setTextureOffset(0, 0).addBox(-0.5F, -2.5F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);
			bone9 = new ModelRenderer(this);
			bone9.setRotationPoint(-1.0F, 21.5F, 1.0F);
			setRotationAngle(bone9, 0.8727F, 0.0F, 0.0F);
			bone9.setTextureOffset(0, 0).addBox(-0.5F, -2.5F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);
			bone10 = new ModelRenderer(this);
			bone10.setRotationPoint(0.0F, 24.0F, 0.0F);
			bone10.setTextureOffset(0, 0).addBox(-1.5F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			bone10.setTextureOffset(0, 0).addBox(0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			bone11 = new ModelRenderer(this);
			bone11.setRotationPoint(-2.5F, 1.5F, -4.5F);
			setRotationAngle(bone11, 0.48F, 0.0F, 0.0F);
			bone11.setTextureOffset(0, 0).addBox(-0.2F, 1.274F, -1.4235F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			bone11.setTextureOffset(0, 0).addBox(3.2F, 1.274F, -1.4235F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			bone11.setTextureOffset(0, 0).addBox(1.5F, 1.2375F, -3.6593F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			bone11.setTextureOffset(0, 0).addBox(0.0F, 3.2473F, -4.6193F, 2.0F, 3.0F, 1.0F, 0.0F, false);
			bone11.setTextureOffset(0, 0).addBox(3.0F, 3.2473F, -4.6193F, 2.0F, 3.0F, 1.0F, 0.0F, false);
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			bb_main.setTextureOffset(0, 0).addBox(2.0F, -14.0F, 0.0F, 1.0F, 10.0F, 2.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 0).addBox(-3.0F, -14.0F, 0.0F, 1.0F, 10.0F, 2.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 0).addBox(2.5F, -15.0F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 0).addBox(-3.5F, -15.0F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
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
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
