package deviceArchitecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.byu.ece.rapidSmith.device.Tile;

public class Column {
	private HashMap<String, List<SubColumn>> subColumns;
	private HashMap<Integer, List<Tile>> tiles;
	private int row, column, numColsAdded;
	private String columnType;
	public Column() {
		this.numColsAdded = 0;
		tiles = new HashMap<>();
		subColumns = new HashMap<>();
	}

	public void Clear(){
		row = column = 0;
		tiles.clear();
		subColumns.clear();
	}
	public void addTiles(List<Tile> value){
		tiles.put(numColsAdded, value);
		numColsAdded++;
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


	public HashMap<Integer, List<Tile>> getTiles() {
		return tiles;
	}


	public void setTiles(HashMap<Integer, List<Tile>> tiles) {
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

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
}
