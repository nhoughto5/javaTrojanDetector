package trojanAttribute;

public class MatrixCell {
	private int rowId;
	private int columnId;
	private boolean value;
	private String subMatrix;
	private int cellId;
	
	public MatrixCell(int rowId, int columnId, boolean value, String subMatrix) {
		this.rowId = rowId;
		this.columnId = columnId;
		this.value = value;
		this.subMatrix = subMatrix;
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public String getSubMatrix() {
		return subMatrix;
	}

	public void setSubMatrix(String subMatrix) {
		this.subMatrix = subMatrix;
	}

}
