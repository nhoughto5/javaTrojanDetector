package deviceArchitecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import utilityClasses.DeviceColumnInfo;
import utilityClasses.Error;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.XilinxConfigurationSpecification;
import edu.byu.ece.rapidSmith.device.Tile;
import edu.byu.ece.rapidSmith.device.Utils;

public class SubColumn {
	List<Tile> tiles;
	String subColumnType, primaryColumnType;
	int column;
	private XilinxConfigurationSpecification spec;
	private DeviceColumnInfo deviceInfo;
	public SubColumn(String primaryColumnType, List<Tile> tiles, int column, XilinxConfigurationSpecification spec, DeviceColumnInfo deviceInfo) {
		this.tiles = tiles;
		this.primaryColumnType = primaryColumnType;
		this.column = column;
		this.spec = spec;
		this.deviceInfo = deviceInfo;
		setConfigurable();
		setIsPrimarySeat();
		findSubColumnType();
	}
	
	//A gate-array is organized into a regular structure. 
	//Set a flag for tiles which sit in a primary configurable location in the column.
	private void setIsPrimarySeat(){
		int totalNumRows = this.spec.getTopNumberOfRows() + this.spec.getBottomNumberOfRows();
		int numPrimaryTilesPerRow = this.deviceInfo.getNumberOfPrimaryTilesInColumn();
		
		//Add for clock tile and starting null tile
		int numRowsPerRow = numPrimaryTilesPerRow + 2;
		for(int row = 0; row < totalNumRows; ++row){
			for(int i = 0; i < numRowsPerRow; ++i){
				Tile currentTile = tiles.get(i + (row * numRowsPerRow));
				if(i != 0){
					currentTile.setPrimarySeat(true);
				}
			}
		}
		//Get the very last tile, this isn't necessary but do it to be verbose
		Tile currentTile = tiles.get(tiles.size() - 1);
		currentTile.setPrimarySeat(false);
		
	}
	private void setConfigurable(){
		for(int i = 0; i < tiles.size(); ++i){
			Tile currentTile = tiles.get(i);
			if(Utils.isPrimaryTile(currentTile.getType()) || Utils.isHorizontalClockTile(currentTile.getType())){
				currentTile.setConfigurable(true);
				tiles.set(i, currentTile);
			}
			else{
				currentTile.setConfigurable(false);
				tiles.set(i, currentTile);
			}
		}
		
	}
	
	public List<Tile> getAffectedTiles(int totalNumRows, int rowNum){
		List<Tile> ret = new ArrayList<>();
		int numPrimaryTilesPerRow = this.deviceInfo.getNumberOfPrimaryTilesInColumn();
		//Add for clock tile and starting null tile
		int numRowsPerRow = numPrimaryTilesPerRow + 2;
		for(int i = 0; i < numRowsPerRow; ++i){
			Tile currentTile = tiles.get(i + (rowNum * numRowsPerRow));
			if(currentTile.isPrimarySeat() && currentTile.isConfigurable()){
				ret.add(currentTile);
			}
		}
		return ret;
	}
	private List<Tile> getOnlyConfigurableTiles(){
		List<Tile> ret = new ArrayList<Tile>();
		for(Tile tile : tiles){
			if(tile.isConfigurable()){
				ret.add(tile);
			}
		}
		return ret;
	}
	private int getNumOfConfigurableTilesInSubColumn(){
		int count = 0;
		for(Tile tile : tiles){
			if(tile.isConfigurable()){
				count++;
			}
		}
		return count;
	}
	private void findSubColumnType(){
		HashMap<String, Integer> map = new HashMap<>();
		for(Tile t : this.tiles){
			if(Utils.getColumnSubType(t) != null){
				Integer val = map.get(Utils.getColumnSubType(t));
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
