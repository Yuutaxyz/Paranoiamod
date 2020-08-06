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
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
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