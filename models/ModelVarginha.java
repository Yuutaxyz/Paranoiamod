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
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
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