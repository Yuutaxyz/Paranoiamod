// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modeltucano extends EntityModel<Entity> {
	private final ModelRenderer bone2;
	private final ModelRenderer bone;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone6;
	private final ModelRenderer bone5;
	private final ModelRenderer bb_main;

	public Modeltucano() {
		textureWidth = 32;
		textureHeight = 32;

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(2.0F, 23.0F, -1.0F);
		setRotationAngle(bone2, -0.0873F, 0.0F, 0.0F);
		bone2.setTextureOffset(0, 10).addBox(-3.0F, -2.9962F, 1.9128F, 2.0F, 0.0F, 4.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(2.0F, 23.0F, 0.0F);
		setRotationAngle(bone, 0.9163F, 0.0F, 0.0F);
		bone.setTextureOffset(0, 0).addBox(-4.0F, -6.0F, 0.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 16.0F, -3.0F);
		setRotationAngle(bone3, 0.1309F, 0.0F, 0.0F);
		bone3.setTextureOffset(16, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 15.0F, -5.0F);
		setRotationAngle(bone4, 0.0436F, 0.0F, 0.0F);
		bone4.setTextureOffset(16, 12).addBox(-1.0F, -1.9564F, -1.001F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(1.0F, 13.0F, -8.0F);
		setRotationAngle(bone6, -1.1781F, 0.0F, 0.0F);
		bone6.setTextureOffset(14, 8).addBox(-2.0F, -1.6993F, 0.9537F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 24.0F, -1.0F);
		setRotationAngle(bone5, -0.2182F, 0.0F, 0.0F);
		bone5.setTextureOffset(0, 16).addBox(2.0F, -6.0F, -3.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		bone5.setTextureOffset(10, 12).addBox(-3.0F, -6.0F, -3.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(2, 0).addBox(1.0F, -3.0F, 0.0F, 0.0F, 3.0F, 1.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-1.0F, -3.0F, 0.0F, 0.0F, 3.0F, 1.0F, 0.0F, false);
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
		bone6.render(matrixStack, buffer, packedLight, packedOverlay);
		bone5.render(matrixStack, buffer, packedLight, packedOverlay);
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}