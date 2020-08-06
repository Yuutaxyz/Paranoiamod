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
		bone12.render(matrixStack, buffer, packedLight, packedOverlay);
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}