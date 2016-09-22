package TrojanDetector;

import java.util.HashSet;
import java.util.List;

import edu.byu.ece.rapidSmith.device.Tile;

public class Trojan {
	public HashSet<Tile> affectedTiles;

	
	
	public Trojan() {
		affectedTiles = new HashSet<>();
	}

	public HashSet<Tile> getAffectedTiles() {
		return affectedTiles;
	}

	public void setAffectedTiles(HashSet<Tile> affectedTiles) {
		this.affectedTiles = affectedTiles;
	}
	
	public void addTiles(List<Tile> newTiles){
		affectedTiles.addAll(newTiles);
	}
	
}
