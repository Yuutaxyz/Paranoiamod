// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelfogofatuo extends EntityModel<Entity> {
	private final ModelRenderer bone2;
	private final ModelRenderer bone;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bb_main;

	public Modelfogofatuo() {
		textureWidth = 32;
		textureHeight = 32;

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(2.0F, 25.0F, -2.0F);
		setRotationAngle(bone2, 0.3491F, 0.0F, 0.0F);
		bone2.setTextureOffset(0, 8).addBox(-3.0F, -7.658F, 2.9397F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(2.0F, 24.0F, -4.0F);
		setRotationAngle(bone, 0.7418F, 0.0F, 0.0F);
		bone.setTextureOffset(0, 0).addBox(-3.5F, -1.9465F, 4.4237F, 3.0F, 5.0F, 3.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 25.0F, 0.0F);
		setRotationAngle(bone3, -0.0873F, 0.0F, 0.0F);
		bone3.setTextureOffset(8, 8).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(1.0F, 18.0F, -3.0F);
		setRotationAngle(bone4, -0.829F, 0.0F, 0.0F);
		bone4.setTextureOffset(10, 12).addBox(-1.5F, -1.7F, -0.49F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 22.0F, 4.0F);
		setRotationAngle(bone5, 0.5672F, 0.0F, 0.0F);
		bone5.setTextureOffset(4, 8).addBox(-0.5F, -8.0F, -1.7688F, 1.0F, 0.0F, 2.0F, 0.0F, false);
		bone5.setTextureOffset(7, 0).addBox(-1.0F, -1.0965F, -0.2383F, 2.0F, 0.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(3.0F, 22.0F, 3.0F);
		setRotationAngle(bone6, 1.1345F, 0.0F, 0.0F);
		bone6.setTextureOffset(12, 12).addBox(-2.0F, -4.5F, -0.4F, 1.0F, 3.0F, 2.0F, 0.0F, false);
		bone6.setTextureOffset(6, 12).addBox(-5.0F, -4.5F, -0.4F, 1.0F, 3.0F, 2.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone7.setTextureOffset(12, 1).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(0, 7).addBox(-0.7F, -2.0F, 0.5F, 0.0F, 2.0F, 1.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(0.7F, -2.0F, 0.5F, 0.0F, 2.0F, 1.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-0.5F, -7.1F, -2.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		bone2.render(matrixStack, buffer, packedLight, packedOverlay);
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
		bone3.render(matrixStack, buffer, packedLight, packedOverlay);
		bone4.render(matrixStack, buffer, packedLight, packedOverlay);
		bone5.render(matrixStack, buffer, packedLight, packedOverlay);
		bone6.render(matrixStack, buffer, packedLight, packedOverlay);
		bone7.render(matrixStack, buffer, packedLight, packedOverlay);
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}