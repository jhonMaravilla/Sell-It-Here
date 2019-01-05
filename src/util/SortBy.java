package util;

public enum SortBy {
	OLD_NEW("created_at", "ASC"), 
	NEW_OLD("created_at", "DESC"),
	LOW_HIGH("price", "ASC"),
	HIGH_LOW("price","DESC");
	
	private String field;
	private String order;
	
	public String getField() {
		return this.field;
	}
	public String getOrder() {
		return this.order;
	}
	
	public String getValue() {
		return field + " " + order;
	}
	private SortBy(String field, String order) {
		this.field = field;
		this.order = order;
	}
}
