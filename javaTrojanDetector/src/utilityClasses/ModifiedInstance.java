package utilityClasses;

import edu.byu.ece.rapidSmith.design.Instance;
import edu.byu.ece.rapidSmith.device.PrimitiveSite;

public class ModifiedInstance {
	private Instance goldenInstance;
	private PrimitiveSite primitiveSite;

	public ModifiedInstance(Instance goldenInstance, PrimitiveSite primitiveSite) {
		this.goldenInstance = goldenInstance;
		this.primitiveSite = primitiveSite;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModifiedInstance other = (ModifiedInstance) obj;
		if (goldenInstance == null) {
			if (other.goldenInstance != null)
				return false;
		} else if (!goldenInstance.equals(other.goldenInstance))
			return false;
		if (primitiveSite == null) {
			if (other.primitiveSite != null)
				return false;
		} else if (!primitiveSite.equals(other.primitiveSite))
			return false;
		return true;
	}

	public Instance getGoldenInstance() {
		return goldenInstance;
	}

	public PrimitiveSite getPrimitiveSite() {
		return primitiveSite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((goldenInstance == null) ? 0 : goldenInstance.hashCode());
		result = prime * result
				+ ((primitiveSite == null) ? 0 : primitiveSite.hashCode());
		return result;
	}

	public void setGoldenInstance(Instance goldenInstance) {
		this.goldenInstance = goldenInstance;
	}

	public void setPrimitiveSite(PrimitiveSite primitiveSite) {
		this.primitiveSite = primitiveSite;
	}

}
