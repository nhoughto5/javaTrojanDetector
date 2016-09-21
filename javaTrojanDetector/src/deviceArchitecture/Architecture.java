package deviceArchitecture;

import java.util.ArrayList;
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
	List<ClockRegion> clockRegions;
	FrameAddressRegister far, farTemp;
	DeviceColumnInfo deviceInfo;
	Tile[][] tiles;
	int maxLocalX, maxLocalY, numGlobalColumns, numGlobalRows;
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
	}
	
	private void incrementFar(){
		if(this.far.validFARAddress()){
			this.far.incrementFAR();
		}
		else{
			System.err.println("Invalid FAR Value");
			System.exit(1);
		}
	}
	
	private List<Column> parseGlobalColumn(List<Tile> columnTiles, BlockSubType currentSubType){
		int numOfRows = this.spec.getTopNumberOfRows() + this.spec.getBottomNumberOfRows();
		List<Column> speratedColumns = new ArrayList<>();
		List<Tile> regionTiles = new ArrayList<>();
		Column col = new Column();
		if(currentSubType.getName().equals("CLB")){
			for(Tile tile : columnTiles){
				System.out.println(tile.getType());
			}
		}
		return speratedColumns;
	}

	private boolean isPrimaryTile(Tile tile) {
		if (Utils.isCLB(tile.getType()) || Utils.isBRAM(tile.getType())
				|| Utils.isDSP(tile.getType())
				|| Utils.isSwitchBox(tile.getType())
				|| Utils.isCNFG(tile.getType()) || Utils.isIOB(tile.getType())
				|| Utils.isCLK(tile.getType()) 
				|| Utils.isBUFS(tile.getType())) {
			return true;
		} else {
			return false;
		}
	}
	private String isNewColumnType(Tile tile){
		String tileType = null;
		if(Utils.isCLB(tile.getType())){
			tileType = "CLB";
		}
		else if(Utils.isBRAM(tile.getType())){
			tileType = "BRAM";
		}
		else if(Utils.isDSP(tile.getType())){
			tileType = "DSP";
		}
		else if(Utils.isSwitchBox(tile.getType())){
			tileType = "SM";
		}
		else if(Utils.isCNFG(tile.getType())){
			tileType = "CFG";
		}
		else if(Utils.isIOB(tile.getType())){
			tileType = "IOB";
		}
		else if(Utils.isCLK(tile.getType())){
			tileType = "CLK";
		}
		else if(Utils.isBUFS(tile.getType())){
			tileType = "BUFS";
		}
		else{
			tileType = null;
		}
		return tileType;
	}
	
	public void loadArchitecture() {
		List<BlockSubType> layout = this.spec.getOverallColumnLayout();
		List<Column> columns = new ArrayList<>();
		
		int currentLocalCol = 0;
		String columnType = null;
		Column currentColumn = new Column();
		for (int i = 0; i < numGlobalColumns; ++i) {	
			List<Tile> columnTiles = this.device.getTilesInColumn(i);
			columnType = null;
			for(Tile tile : columnTiles){
				//System.out.println(tile.getName());
				columnType = isNewColumnType(tile);
				if(columnType != null){
					int t = tile.getTileXCoordinate();
					if(t > currentLocalCol){
						columnType = layout.get(currentLocalCol).getName();
						currentLocalCol = t;
						currentColumn.setColumnType(columnType);
						columns.add(currentColumn);
						currentColumn = new Column();
					}
				}
			}
			//System.out.println(layout.get(currentLocalCol).getName());
			currentColumn.addTiles(columnTiles);
			
			//Add the final column of the device
			if(i == numGlobalColumns - 1){
				columnType = layout.get(currentLocalCol).getName();
				currentColumn.setColumnType(columnType);
				columns.add(currentColumn);
			}
		}
	}
	
}
