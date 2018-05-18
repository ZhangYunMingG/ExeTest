package other;

public enum EnumTest {

	SUCCESS("1", "qqq"), Failed("2", "fail");
	
	private String value;
	private String desc;
	private EnumTest(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static void main(String[] args) {
		System.out.println(EnumTest.SUCCESS.getValue());
	}
}

