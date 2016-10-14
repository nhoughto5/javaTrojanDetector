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

	@Override
	public String toString() {
		return "TrojanAttribute: " + Id + ",\n Category: " + category
				+ ",\n Name: " + name + ",\n Description: " + description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrojanAttribute other = (TrojanAttribute) obj;
		if (Id != other.Id)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}