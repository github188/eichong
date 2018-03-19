package com.wanma.ims.constants;

public enum CompanyEnum {

	INVEST("投资公司", 1), CHANNEL("渠道公司", 2), PILEOWNER("桩主公司", 3), LANDOWNER("业主公司", 4);

	private String name;
	private int val;

	private CompanyEnum(String name, int val){
		this.name = name;
		this.val = val;
	}

	public static String getName(int val) {
		for (CompanyEnum c : CompanyEnum.values()) {
			if (c.getVal() == val) {
				return c.getName();
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

}
