package deviceArchitecture;

import java.util.ArrayList;
import java.util.List;

import utilityClasses.DeviceColumnInfo;
import edu.byu.ece.rapidSmith.bitstreamTools.configuration.FrameAddressRegister;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.BlockSubType;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.XilinxConfigurationSpecification;
import edu.byu.ece.rapidSmith.device.Device;
import edu.byu.ece.rapidSmith.device.Tile;

public class Architecture {
	private Device device;
	private XilinxConfigurationSpecification spec;
	List<ClockRegion> clockRegions;
	FrameAddressRegister far, farTemp;
	DeviceColumnInfo deviceInfo;
	Tile[][] tiles;
	List<ParentTile> parentTiles;
	int maxLocalX, maxLocalY;
	public Architecture(Device device, XilinxConfigurationSpecification spec) {
		this.device = device;
		this.spec = spec;
		this.far = new FrameAddressRegister(this.spec);
		this.tiles = device.getTiles();
		this.deviceInfo = new DeviceColumnInfo(this.spec.getDeviceFamily());
		this.maxLocalX = 0;
		this.maxLocalY = 0;
		parentTiles = new ArrayList<>();
		findParentTiles();
	}
	
	private void findParentTiles(){
		this.tiles = this.device.getTiles();
		int matrixX = 0, matrixY = tiles.length;
		for(int i = 0; i < matrixY; ++i){
			matrixX = tiles[i].length;
			for(int j = 0; j < matrixX; ++j){
				if(this.tiles[i][j].getTileXCoordinate() > maxLocalX){
					maxLocalX = this.tiles[i][j].getTileXCoordinate();
				}
				if(this.tiles[i][j].getTileYCoordinate() > maxLocalY){
					maxLocalY = this.tiles[i][j].getTileYCoordinate();
				}
			}
		}
		matrixX = 0;
		matrixY = tiles.length;
		for(int row = 0; row < maxLocalY; ++row){
			for(int col = 0; col < maxLocalX; ++col){
				ParentTile pT = new ParentTile(col, row);
				for(int i = 0; i < matrixY; ++i){
					matrixX = tiles[i].length;
					for(int j = 0; j < matrixX; ++j){
						if(this.tiles[i][j].getTileYCoordinate() == row && this.tiles[i][j].getTileXCoordinate() == col){
							pT.addTile(this.tiles[i][j]);
						}
					}
				}
				parentTiles.add(pT);
			}
		}
		
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
	
	public void loadArchitecture() {
		List<BlockSubType> layout = this.spec.getOverallColumnLayout();
		List<Column> columns = new ArrayList<Column>();
		for (int i = 0; i < layout.size(); ++i) {
			List<Tile> columnTiles = this.device.getTilesInColumn(i);
			System.out.println(layout.get(i).getName());
			//columns.addAll(parseGlobalColumn(columnTiles, layout.get(i)));
			for(Tile tile : columnTiles){
				System.out.println(tile.getType());
			}
		}
	}
	
}
