package deviceArchitecture;

import java.util.HashMap;
import java.util.List;

import edu.byu.ece.rapidSmith.device.Tile;

public class Column {
	private HashMap<String, List<SubColumn>> subColumns;
	private HashMap<String, List<Tile>> tiles;
	private int row, column;

	public Column() {
		
	}

	public void addTiles(String key, List<Tile> value){
		tiles.put(key, value);
	}
	public void addSubColumns(String key, List<SubColumn> value){
		subColumns.put(key, value);
	}

	public HashMap<String, List<SubColumn>> getSubColumns() {
		return subColumns;
	}

	public void setSubColumns(HashMap<String, List<SubColumn>> subColumns) {
		this.subColumns = subColumns;
	}


	public HashMap<String, List<Tile>> getTiles() {
		return tiles;
	}


	public void setTiles(HashMap<String, List<Tile>> tiles) {
		this.tiles = tiles;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
}
