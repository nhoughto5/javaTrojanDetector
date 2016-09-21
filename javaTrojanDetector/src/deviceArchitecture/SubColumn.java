package deviceArchitecture;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import edu.byu.ece.rapidSmith.device.Tile;
import edu.byu.ece.rapidSmith.device.Utils;

public class SubColumn {
	List<Tile> tiles;
	String subColumnType, primaryColumnType;
	int column;
	
	public SubColumn(String primaryColumnType, List<Tile> tiles, int column) {
		this.tiles = tiles;
		this.primaryColumnType = primaryColumnType;
		this.column = column;
		setConfigurable();
		findSubColumnType();
		System.out.println("P: " + primaryColumnType + " - " + "Sub: " + subColumnType + " Number: " + this.column);
	}
	
	private void setConfigurable(){
		for(int i = 0; i < tiles.size(); ++i){
			Tile currentTile = tiles.get(i);
			if(!Utils.isPrimaryTile(currentTile.getType())){
				currentTile.setConfigurable(false);
				tiles.set(i, currentTile);
			}
			else{
				currentTile.setConfigurable(true);
				tiles.set(i, currentTile);
			}
		}
		
	}
	
	private void findSubColumnType(){
		HashMap<String, Integer> map = new HashMap<>();
		for(Tile t : this.tiles){
			Integer val = map.get(t);
			if(Utils.getColumnSubType(t) != null){
				map.put(Utils.getColumnSubType(t), val == null ? 1 : val + 1);
			}
		}
		
	    Entry<String, Integer> max = null;

	    for (Entry<String, Integer> e : map.entrySet()) {
	        if (max == null || e.getValue() > max.getValue())
	            max = e;
	    }
	    if(!map.isEmpty()){
	    	this.subColumnType = max.getKey();
	    }
	}
	
	public List<Tile> getTiles() {
		return tiles;
	}
	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}
	public String getSubColumnType() {
		return subColumnType;
	}
	public void setSubColumnType(String subColumnType) {
		this.subColumnType = subColumnType;
	}
	
	
}
