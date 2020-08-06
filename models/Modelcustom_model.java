
public static class Modelcustom_model extends EntityModel<Entity> {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bb_main;

	public Modelcustom_model() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(2.0F, 20.0F, -4.0F);
		setRotationAngle(bone, -0.6545F, 0.0F, 0.0F);
		bone.setTextureOffset(17, 18).addBox(-4.0F, -6.0F, -4.0F, 4.0F, 4.0F, 5.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-2.0F, 21.0F, -2.0F);
		setRotationAngle(bone2, -0.1745F, 0.0F, 0.0F);
		bone2.setTextureOffset(20, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone2.setTextureOffset(15, 12).addBox(3.0F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(3.0F, 20.0F, -3.0F);
		setRotationAngle(bone3, -0.6109F, 0.0F, 0.0F);
		bone3.setTextureOffset(28, 8).addBox(-2.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(28, 28).addBox(-5.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-2.0F, 21.0F, 3.0F);
		setRotationAngle(bone4, 0.6981F, 0.0F, 0.0F);
		bone4.setTextureOffset(0, 23).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		bone4.setTextureOffset(0, 0).addBox(3.0F, -3.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(3.0F, 24.0F, 3.0F);
		setRotationAngle(bone5, -0.2182F, 0.0F, 0.0F);
		bone5.setTextureOffset(16, 27).addBox(-2.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone5.setTextureOffset(12, 27).addBox(-5.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(1.0F, 14.0F, -11.0F);
		setRotationAngle(bone6, -1.1345F, 0.0F, 0.0F);
		bone6.setTextureOffset(0, 12).addBox(-2.0F, -4.0F, 1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-4.0F, 22.0F, 0.0F);
		setRotationAngle(bone7, 0.0F, 0.0F, 0.4363F);
		bone7.setTextureOffset(15, 17).addBox(1.0F, -11.0F, -5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-1.0F, 24.0F, 0.0F);
		setRotationAngle(bone8, 0.0F, 0.0F, -0.3927F);
		bone8.setTextureOffset(0, 6).addBox(1.4115F, -10.741F, -5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 21.0F, -3.0F);
		setRotationAngle(bone9, -0.5236F, 0.0F, 0.0F);
		bone9.setTextureOffset(15, 12).addBox(-1.0F, -8.0F, 3.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(20, 0).addBox(-2.0F, -12.0F, -7.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-3.0F, -9.0F, -4.0F, 6.0F, 4.0F, 8.0F, 0.0F, false);
		bb_main.setTextureOffset(24, 27).addBox(-2.0F, -6.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 12).addBox(-2.0F, -8.0F, -4.0F, 4.0F, 4.0F, 7.0F, 0.0F, false);
		bb_main.setTextureOffset(23, 12).addBox(-2.0F, -8.0F, -5.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		bb_main.setTextureOffset(20, 27).addBox(1.0F, -6.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bb_main.setTextureOffset(26, 16).addBox(-2.0F, -1.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bb_main.setTextureOffset(6, 23).addBox(1.0F, -1.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bb_main.setTextureOffset(6, 27).addBox(1.0F, -1.0F, 2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 15).addBox(-2.0F, -1.0F, 2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bb_main.setTextureOffset(8, 23).addBox(-1.0F, -10.0F, -9.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
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
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}