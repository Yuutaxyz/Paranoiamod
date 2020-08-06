
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
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.monster.MonsterEntity;
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

import net.mcreator.paranoiamod.ParanoiamodModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@ParanoiamodModElements.ModElement.Tag
public class PanteraoncaEntity extends ParanoiamodModElements.ModElement {
	public static EntityType entity = null;
	public PanteraoncaEntity(ParanoiamodModElements instance) {
		super(instance, 11);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("panteraonca")
						.setRegistryName("panteraonca");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -205, -16777216, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("panteraonca"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("forest")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("taiga")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("river")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("jungle")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("jungle_hills")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("jungle_hills")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("savanna")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 21, 6, 8));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::canMonsterSpawn);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modeloncapintada(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("paranoiamod:textures/oncapintadatextura.png");
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
			return CreatureAttribute.UNDEAD;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("paranoiamod:oncasom"));
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
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(17);
		}
	}

	// Made with Blockbench 3.6.5
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modeloncapintada extends EntityModel<Entity> {
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
		private final ModelRenderer bb_main;
		public Modeloncapintada() {
			textureWidth = 64;
			textureHeight = 64;
			bone = new ModelRenderer(this);
			bone.setRotationPoint(2.0F, 11.0F, -25.0F);
			setRotationAngle(bone, 0.5672F, 0.0F, 0.0F);
			bone.setTextureOffset(0, 13).addBox(-4.0F, -3.0F, 0.0F, 4.0F, 3.0F, 3.0F, 0.0F, false);
			bone2 = new ModelRenderer(this);
			bone2.setRotationPoint(2.0F, 8.0F, -26.0F);
			setRotationAngle(bone2, -0.7418F, 0.0F, 0.0F);
			bone2.setTextureOffset(14, 34).addBox(-4.0F, -5.0F, 0.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
			bone3 = new ModelRenderer(this);
			bone3.setRotationPoint(0.0F, 10.0F, -8.0F);
			setRotationAngle(bone3, -0.3054F, 0.0F, 0.0F);
			bone3.setTextureOffset(12, 26).addBox(0.0F, -1.0F, -8.0F, 0.0F, 1.0F, 5.0F, 4.0F, false);
			bone4 = new ModelRenderer(this);
			bone4.setRotationPoint(1.0F, 1.0F, 6.0F);
			setRotationAngle(bone4, -0.6981F, 0.0F, 0.0F);
			bone4.setTextureOffset(0, 20).addBox(-2.0F, -3.2856F, 9.5321F, 2.0F, 2.0F, 9.0F, 0.0F, false);
			bone5 = new ModelRenderer(this);
			bone5.setRotationPoint(1.0F, 6.0F, 14.0F);
			setRotationAngle(bone5, -0.3927F, 0.0F, 0.0F);
			bone5.setTextureOffset(0, 24).addBox(-2.0F, -1.658F, -0.9397F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			bone6 = new ModelRenderer(this);
			bone6.setRotationPoint(1.0F, 13.0F, 23.0F);
			setRotationAngle(bone6, -0.4363F, 0.0F, 0.0F);
			bone6.setTextureOffset(0, 20).addBox(-2.0F, -2.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			bone7 = new ModelRenderer(this);
			bone7.setRotationPoint(1.0F, 6.0F, 9.0F);
			setRotationAngle(bone7, -0.0436F, 0.0F, 0.0F);
			bone7.setTextureOffset(13, 20).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
			bone8 = new ModelRenderer(this);
			bone8.setRotationPoint(0.0F, 35.0F, 8.0F);
			setRotationAngle(bone8, 0.4363F, 0.0F, 0.0F);
			bone8.setTextureOffset(49, 6).addBox(-1.0F, -13.0F, 29.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
			bone9 = new ModelRenderer(this);
			bone9.setRotationPoint(0.0F, 30.0F, 3.0F);
			setRotationAngle(bone9, 0.2182F, 0.0F, 0.0F);
			bone9.setTextureOffset(11, 0).addBox(-1.0F, -13.0F, 28.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			bone10 = new ModelRenderer(this);
			bone10.setRotationPoint(3.0F, 3.0F, -23.0F);
			setRotationAngle(bone10, -0.8727F, 0.0F, 0.0F);
			bone10.setTextureOffset(22, 20).addBox(-6.0F, -1.0F, 0.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
			bone11 = new ModelRenderer(this);
			bone11.setRotationPoint(5.0F, 20.0F, -9.0F);
			setRotationAngle(bone11, -0.4363F, 0.0F, 0.0F);
			bone11.setTextureOffset(19, 43).addBox(-3.0F, -2.7255F, -0.9962F, 2.0F, 6.0F, 3.0F, 0.0F, false);
			bone11.setTextureOffset(9, 43).addBox(-9.0F, -2.7255F, -0.9962F, 2.0F, 6.0F, 3.0F, 0.0F, false);
			bone12 = new ModelRenderer(this);
			bone12.setRotationPoint(5.0F, 19.0F, 8.0F);
			setRotationAngle(bone12, 0.9599F, 0.0F, 0.0F);
			bone12.setTextureOffset(0, 0).addBox(-3.0F, -7.7544F, 1.3927F, 3.0F, 8.0F, 5.0F, 0.0F, false);
			bone12.setTextureOffset(22, 22).addBox(-10.0F, -6.7544F, 1.3927F, 3.0F, 7.0F, 5.0F, 0.0F, false);
			bone13 = new ModelRenderer(this);
			bone13.setRotationPoint(0.0F, 19.0F, -1.0F);
			setRotationAngle(bone13, -0.48F, 0.0F, 0.0F);
			bone13.setTextureOffset(38, 39).addBox(2.0F, -9.4617F, 8.887F, 2.0F, 9.0F, 2.0F, 0.0F, false);
			bone13.setTextureOffset(30, 39).addBox(-4.0F, -9.4617F, 8.887F, 2.0F, 9.0F, 2.0F, 0.0F, false);
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			bb_main.setTextureOffset(12, 32).addBox(-1.0F, -19.0F, -19.0F, 2.0F, 2.0F, 1.0F, 3.0F, false);
			bb_main.setTextureOffset(0, 31).addBox(-3.0F, -24.0F, -21.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(11, 13).addBox(1.0F, -24.0F, -21.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 34).addBox(-3.0F, -23.0F, -20.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(13, 27).addBox(1.0F, -23.0F, -20.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 0).addBox(1.0F, -14.0F, -6.0F, -2.0F, -2.0F, 9.0F, 6.0F, false);
			bb_main.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -11.0F, 8.0F, 1.0F, 19.0F, 0.0F, false);
			bb_main.setTextureOffset(32, 32).addBox(-4.0F, -21.0F, -11.0F, 3.0F, 1.0F, 6.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 31).addBox(1.0F, -21.0F, -11.0F, 3.0F, 1.0F, 6.0F, 0.0F, false);
			bb_main.setTextureOffset(35, 0).addBox(-1.0F, -13.0F, 23.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
			bb_main.setTextureOffset(35, 8).addBox(-3.0F, -21.0F, -23.0F, 6.0F, 7.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(38, 20).addBox(2.0F, -10.0F, -9.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 38).addBox(-5.0F, -10.0F, -9.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
			bb_main.setTextureOffset(42, 46).addBox(2.0F, -2.0F, -13.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
			bb_main.setTextureOffset(45, 12).addBox(-4.0F, -2.0F, -13.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 49).addBox(2.0F, -10.0F, 9.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);
			bb_main.setTextureOffset(46, 39).addBox(-4.0F, -10.0F, 9.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);
			bb_main.setTextureOffset(45, 0).addBox(2.0F, -2.0F, 5.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
			bb_main.setTextureOffset(44, 28).addBox(-4.0F, -2.0F, 5.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
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
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
		}
	}
}
