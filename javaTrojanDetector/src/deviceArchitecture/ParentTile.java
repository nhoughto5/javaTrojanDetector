package deviceArchitecture;

import java.util.ArrayList;
import java.util.List;

import edu.byu.ece.rapidSmith.device.Tile;

public class ParentTile {
	List<Tile> tiles;
	int localX, localY;
	
	
	public ParentTile(int localX, int localY) {
		this.localX = localX;
		this.localY = localY;
		tiles = new ArrayList<Tile>();
	}
	public void addTile(Tile t){
		this.tiles.add(t);
	}
	public List<Tile> getTiles() {
		return tiles;
	}
	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}
	public int getLocalX() {
		return localX;
	}
	public void setLocalX(int localX) {
		this.localX = localX;
	}
	public int getLocalY() {
		return localY;
	}
	public void setLocalY(int localY) {
		this.localY = localY;
	}
	
	
}
