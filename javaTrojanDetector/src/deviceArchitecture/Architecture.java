package deviceArchitecture;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import utilityClasses.DeviceColumnInfo;
import edu.byu.ece.rapidSmith.bitstreamTools.configuration.FrameAddressRegister;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.BlockSubType;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.XilinxConfigurationSpecification;
import edu.byu.ece.rapidSmith.device.Device;
import edu.byu.ece.rapidSmith.device.Tile;
import edu.byu.ece.rapidSmith.device.Utils;

public class Architecture {
	private Device device;
	private XilinxConfigurationSpecification spec;
	FrameAddressRegister far, farTemp;
	DeviceColumnInfo deviceInfo;
	Tile[][] tiles;
	int maxLocalX, maxLocalY, numGlobalColumns, numGlobalRows;
	List<Column> columns;
	List<ClockRegion> clockRegions;
	
	public Architecture(Device device, XilinxConfigurationSpecification spec) {
		this.device = device;
		this.spec = spec;
		this.far = new FrameAddressRegister(this.spec);
		this.tiles = device.getTiles();
		this.deviceInfo = new DeviceColumnInfo(this.spec.getDeviceFamily());
		this.maxLocalX = 0;
		this.maxLocalY = 0;
		this.numGlobalColumns = this.device.getColumns();
		this.numGlobalRows = this.device.getRows();
		this.columns = new ArrayList<>();
		this.clockRegions = new ArrayList<>();
		this.incrementFAR();
		//this.loadArchitecture();
	}
	private boolean isNewClockRegion(int Row, int Col){
		if(Row != this.far.getClockRegionY()){
			return true;
		}
		else if(Col != this.far.getClockRegionX()){
			return true;
		}
		else{
			return false;
		}
	}
	private void incrementFAR(){
		this.far.initFAR();
		int Row = this.far.getClockRegionY(), Col = this.far.getClockRegionX(), minors = 0;
		System.out.println("CR_X" + this.far.getClockRegionX() + "Y" + this.far.getClockRegionY());
		ClockRegion tempCR = new ClockRegion(this.far.getClockRegionX(),this.far.getClockRegionY(), this.spec, this.device);
		HashSet<String> CRs = new HashSet<>();
		for(; this.far.validFARAddress(); this.far.incrementFAR()){
			if(this.far.getBlockType() == 0){
				if(isNewClockRegion(Row, Col)){
					String answer = "CR_X" + this.far.getClockRegionX() + "Y" + this.far.getClockRegionY();
					if(!CRs.contains(answer)){
						System.out.println(answer  + " has " + minors + " minor columns    ");
						CRs.add(answer);
					}
					
					clockRegions.add(tempCR);
					minors = 0;
					Row = this.far.getClockRegionY();
					Col = this.far.getClockRegionX();
					tempCR = new ClockRegion(Col, Row, this.spec, this.device);
				}
				else{
					tempCR.addAddress(this.far.getAddress());
					++minors;
				}
			}
		}
		clockRegions.add(tempCR);
	}
	
	private void loadArchitecture() {
		List<BlockSubType> layout = this.spec.getOverallColumnLayout();
		//this.far.initFAR();
		int currentLocalCol = 0, colCount = 0;
		String columnType = null;
		Column currentColumn = new Column();
		for (int i = 0; i < numGlobalColumns; ++i) {	
			List<Tile> columnTiles = this.device.getTilesInColumn(i);
			columnType = null;
			for(Tile tile : columnTiles){
				columnType = Utils.getColumnSubType(tile);
				if(columnType != null){
					//System.out.println(tile.getName());
					int t = tile.getTileXCoordinate();
					if(t > currentLocalCol){
						currentLocalCol = t;
						currentColumn.setColumnType(layout.get(colCount).getName());
						columns.add(currentColumn);
						currentColumn = new Column();
						colCount++;
					}
				}
			}
			
			currentColumn.setColumn(colCount);
			currentColumn.addSubColumns(layout.get(colCount).getName(), columnTiles);
			//System.out.println("==============================");
			//Add the final column of the device
			if(i == numGlobalColumns - 1){
				columnType = layout.get(colCount).getName();
				currentColumn.setColumnType(columnType);
				columns.add(currentColumn);
			}
		}
	}
	
	public Column getColumn(int col){
		return this.columns.get(col);
	}
}
