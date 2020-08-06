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
		bone13.render(matrixStack, buffer, packedLight, packedOverlay);
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}