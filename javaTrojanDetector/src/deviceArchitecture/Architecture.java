package deviceArchitecture;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		this.loadArchitecture();
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

	private void loadArchitecture() {
		List<BlockSubType> layout = this.spec.getOverallColumnLayout();
		//this.far.initFAR();
		int currentLocalCol = 0, colCount = 0;
		String columnType = null;
		Column currentColumn = new Column(this.spec, this.deviceInfo);
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
						currentColumn = new Column(this.spec, this.deviceInfo);
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
	private void makeTileMap(){
		File fout = new File("tileMapFile.txt");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fout);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		this.tiles = this.device.getTiles();
		int matrixX = 0, matrixY = tiles.length;
		for(int i = 0; i < matrixY; ++i){
			matrixX = tiles[i].length;
			for(int j = 0; j < matrixX; ++j){
				try {
					bw.write(this.tiles[i][j].getName() + " ");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				bw.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getColumnType(List<Tile> columnTiles){
		List<String> tileTypes = new ArrayList<>();
		for(Tile Y : columnTiles){
			tileTypes.add(Utils.getColumnSubType(Y));
		}
	    Map<String, Integer> m = new HashMap<String, Integer>();

	    for (String a : tileTypes) {
	    	Integer freq = m.get(a);
	        m.put(a, (freq == null) ? 1 : freq + 1);
	    }

	    int max = -1;
	    String mostFrequent = "";

	    for (Map.Entry<String, Integer> e : m.entrySet()) {
	        if (e.getValue() > max) {
	            mostFrequent = e.getKey();
	            max = e.getValue();
	        }
	    }
	    return mostFrequent;
	}
	public Column getColumn(int col){
		return this.columns.get(col);
	}
}
