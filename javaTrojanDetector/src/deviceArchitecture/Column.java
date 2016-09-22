package deviceArchitecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.byu.ece.rapidSmith.device.Tile;
import edu.byu.ece.rapidSmith.device.Utils;

public class Column {
	private List<SubColumn> subColumns;
	private int row, column;
	private String columnType;
	public Column() {
		subColumns = new ArrayList<>();
	}
	
	public SubColumn getSubColumnByType(String type){
		SubColumn ret = null;
		for(SubColumn sC: subColumns){
			if(sC.getSubColumnType().equals(type)){
				return sC;
			}
		}
		System.err.println("SubColumn Type: " + type + " not found");
		System.exit(-1);
		return ret; //Should never be reached
	}
	
	public void Clear(){
		row = column = 0;
		subColumns.clear();
	}
	public void addSubColumns(String columnType, List<Tile> tiles){
		subColumns.add(new SubColumn(columnType, tiles, this.column));
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
