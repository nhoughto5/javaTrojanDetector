package deviceArchitecture;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ClockRegion {
	private int X, Y;
	private List<Column> columns;
	private List<Integer> frameAddresses;
	
	public ClockRegion() {
		frameAddresses = new ArrayList<>();
	}
	
	public ClockRegion(int x, int y) {
		this.X = x;
		this.Y = y;
		frameAddresses = new ArrayList<>();
	}

	public ClockRegion(int x, int y, List<Column> columns, ArrayList<Integer> frameAddresses) {
		this.X = x;
		this.Y = y;
		this.columns = columns;
		this.frameAddresses = frameAddresses;
	}
	
	public void addAddress(int address){
		this.frameAddresses.add(address);
	}
	
	public void cleanUp(){
		this.columns.clear();
		this.frameAddresses.clear();
	}
	
	public List<Column> getColumns() {
		return columns;
	}
	public List<Integer> getFrameAddresses() {
		return frameAddresses;
	}
	public int getX() {
		return X;
	}
	public int getY() {
		return Y;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	public void setFrameAddresses(List<Integer> frameAddresses) {
		this.frameAddresses = frameAddresses;
	}
	public void setX(int x) {
		X = x;
	}
	public void setY(int y) {
		Y = y;
	}
	
}
