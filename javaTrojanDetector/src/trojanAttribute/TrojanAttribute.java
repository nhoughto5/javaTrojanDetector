package trojanAttribute;

public class TrojanAttribute {
	private int Id;
	private String category;
	private String name;
	private String description;

	public TrojanAttribute(final int id, final String category,
			final String name, final String description) {
		this.Id = id;
		this.category = category;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return this.Id;
	}

	public void setId(final int id) {
		this.Id = id;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

}