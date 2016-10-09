package deviceArchitecture;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import utilityClasses.DeviceColumnInfo;
import utilityClasses.Error;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.BlockSubType;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.XilinxConfigurationSpecification;
import edu.byu.ece.rapidSmith.device.Device;

public class ClockRegion {
	private int X, Y;
	private List<Column> columns;
	private List<Integer> frameAddresses;
	private XilinxConfigurationSpecification spec;
	private List<BlockSubType> columnDefs;
	private Device device;
	private DeviceColumnInfo deviceInfo;
	public ClockRegion() {
		frameAddresses = new ArrayList<>();
	}
	
	public ClockRegion(int x, int y) {
		this.X = x;
		this.Y = y;
		frameAddresses = new ArrayList<>();
	}

	public ClockRegion(int x, int y, XilinxConfigurationSpecification spec, Device device) {
		this.X = x;
		this.Y = y;
		this.device = device;
		this.deviceInfo = new DeviceColumnInfo(this.spec.getDeviceFamily());
		this.spec = spec;
		List<BlockSubType> columnTypes = this.spec.getOverallColumnLayout();
		this.frameAddresses = new ArrayList<>();
		if(columnTypes.size() % 2 != 0){
			Error.printError("Uneven number of columns across device", new Exception().getStackTrace()[0]);
		}
		else{
			//Left half of device
			if(X == 0){
				this.columnDefs = columnTypes.subList(0, columnTypes.size() / 2);
			}
			else{
				this.columnDefs = columnTypes.subList(columnTypes.size() / 2, columnTypes.size());
			}
		}
	}
	public void makeColumns(){
		
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
