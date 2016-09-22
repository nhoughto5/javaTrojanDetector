package deviceArchitecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import utilityClasses.Error;
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
		int typeCount = 0;
		for(SubColumn sC: subColumns){
			if(sC.getSubColumnType().equals(type)){
				ret = sC;
				++typeCount;
			}
		}
		if(typeCount > 1){
			Error.printError("Multiple SubColumns of type " + type, new Exception().getStackTrace()[0]);
		}
		if(ret == null){
			Error.printError("SubColumn Type: " + type + " not found", new Exception().getStackTrace()[0]);	
			return ret;
		}
		else{
			return ret;
		}
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

	public List<SubColumn> getSubColumns() {
		return subColumns;
	}

	public void setSubColumns(List<SubColumn> subColumns) {
		this.subColumns = subColumns;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
}
