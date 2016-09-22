package TrojanDetector;

import java.util.HashSet;
import java.util.List;
import utilityClasses.ModifiedTile;

public class Trojan {
	public HashSet<ModifiedTile> affectedTiles;

	public Trojan() {
		affectedTiles = new HashSet<>();
	}

	public HashSet<ModifiedTile> getAffectedTiles() {
		return affectedTiles;
	}

	public void setAffectedTiles(HashSet<ModifiedTile> affectedTiles) {
		this.affectedTiles = affectedTiles;
	}
	
	public void addTiles(List<ModifiedTile> newTiles){
		affectedTiles.addAll(newTiles);
	}
	
}
