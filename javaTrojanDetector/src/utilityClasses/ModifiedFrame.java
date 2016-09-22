package utilityClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import deviceArchitecture.Column;
import deviceArchitecture.SubColumn;
import edu.byu.ece.rapidSmith.bitstreamTools.configuration.Frame;
import edu.byu.ece.rapidSmith.bitstreamTools.configuration.FrameAddressRegister;
import edu.byu.ece.rapidSmith.bitstreamTools.configuration.FrameData;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.AbstractConfigurationSpecification;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.BlockSubType;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.XilinxConfigurationSpecification;
import edu.byu.ece.rapidSmith.device.Device;
import edu.byu.ece.rapidSmith.device.Tile;

public class ModifiedFrame {
	protected Frame goldenFrame, targetFrame;
	protected FrameAddressRegister frameAddressRegister;
	protected XilinxConfigurationSpecification configSpec;
	protected int top_bottom;
	protected int blockType;
	protected int rowNum;
	protected int columnNum;
	protected int minor, address, numberOfWordsPerTile;
	protected String hexAddress, printOut, columnBlockType, familyName;
	protected BlockSubType columnFrameBlockSubType;
	protected DeviceColumnInfo selector;
	protected XilinxConfigurationSpecification spec;
	protected boolean isFrameTop;
	protected HashSet<Tile> tiles;
	private Column column;
	private SubColumn subColumn;
	
	public ModifiedFrame(Frame goldenFrame, Frame targetFrame, FrameAddressRegister frameAddressRegister, XilinxConfigurationSpecification spec) {
		this.goldenFrame = goldenFrame;
		this.targetFrame = targetFrame;
		this.top_bottom = frameAddressRegister.getTopBottom();
		this.blockType = frameAddressRegister.getBlockType(); 
		this.rowNum = frameAddressRegister.getRow();
		this.columnNum = frameAddressRegister.getColumn();
		this.minor = frameAddressRegister.getMinor();
		this.address = frameAddressRegister.getAddress();
		this.hexAddress = frameAddressRegister.getHexAddress();
		this.printOut = frameAddressRegister.toString(1);
		this.columnBlockType = frameAddressRegister.getFrameBlockType();
		this.columnFrameBlockSubType = frameAddressRegister.getFrameBlockSubType();
		this.spec = spec;
		this.isFrameTop = frameAddressRegister.isFrameTop();
		this.selector = new DeviceColumnInfo(this.spec.getDeviceFamily());
	}
	
	public void mapTiles(Column column){
		this.column = column;
		if(this.column.getColumn() != this.columnNum){
			System.err.println("Wrong Column Number");
			System.exit(-1);
		}		
		String colType = column.getColumnType();
		switch(colType){
			case "CLB":
				mapCLBColumn();
				break;
			case "DSP":
				mapDSPColumn();
				break;
			case "IOB":
				mapIOBColumn();
				break;
			case "BRAMINTERCONNECT":
				mapBRAMColumn();
				break;
			case "CLK":
				mapCLKColumn();
				break;
			default:
				System.err.println("Modified Frame does not match know Column Type: " + colType + ": " + this.column.getColumn());
				System.exit(-1);
			break;
		}
	}
	
	private void mapCLBColumn(){
		int totalNumRows = this.spec.getTopNumberOfRows() + this.spec.getBottomNumberOfRows();
		//This frame configures the Interconnect of the CLB column
		if(this.minor <= this.selector.getMaxIRFrameNumber()){
			this.subColumn = this.column.getSubColumnByType("INT");
			List<Tile> affectedTiles = this.subColumn.getAffecctedTiles(totalNumRows, this.getNaturalRegionRowNumber());
		}
		//This frame configures the actual CLB of the CLB column
		else if((this.selector.getMaxIRFrameNumber() < this.minor) && (this.minor <= this.selector.getNumberOfFrames_CLBColumn())){
			this.subColumn = this.column.getSubColumnByType("CLB");
			List<Tile> affectedTiles = this.subColumn.getAffecctedTiles(totalNumRows, this.getNaturalRegionRowNumber());
		}
		else{
			System.err.println("Mismatch frame address with column type");
			System.exit(-1);
		}
	}
	
	private void mapIOBColumn(){
		
	}
	
	private void mapDSPColumn(){
		
	}
	
	private void mapCLKColumn(){
		
	}
	
	private void mapBRAMColumn(){
		
	}
	
	//Returns which row the frame is in a more natural semantic.
	//Rows begin at the bottom of the device at 0.
	private int getNaturalRegionRowNumber() {
		if (this.isFrameTop) {
			return this.spec.getBottomNumberOfRows() + this.rowNum + 1;
		} else {
			return this.spec.getBottomNumberOfRows() - rowNum;
		}
	}
	
	public void createYCoordinateDifferenceseList(Device readDevice){
		tiles = new HashSet<Tile>();
		List<Integer> goldenData = goldenFrame.getData().getAllFrameWords();
		List<Integer> targetData = targetFrame.getData().getAllFrameWords();
		if(goldenData.size() != targetData.size()){
			System.err.println("Target and Golden frame length do not match!");
			System.exit(1);
		}
		//int numTopRows = spec.getTopNumberOfRows();
		int numBottomRows = spec.getBottomNumberOfRows();
		int numLowerTiles;
		if(this.columnFrameBlockSubType.getName().equalsIgnoreCase("CLB")){
			numberOfWordsPerTile = (this.spec.getFrameSize() - this.selector.getNumberOfClockWordsPerFrame()) / this.selector.getNumberOfTilesInCLBColumn();
			for(int i = 0; i < goldenData.size(); ++i){
				if(goldenData.get(i) != targetData.get(i)){
					if(this.isFrameTop){
						numLowerTiles = numBottomRows + this.rowNum;
					}
					else{
						numLowerTiles = numBottomRows - this.rowNum - 1;
					}
					//TODO is this necessary?
					numLowerTiles = (numLowerTiles < 0) ? 0 : numLowerTiles;
					int rowNum = (numLowerTiles * this.selector.getNumberOfTilesInCLBColumn()) + ((int) i / this.numberOfWordsPerTile);
					Tile tempTile = readDevice.getCLBTileByLocalCoordinates(rowNum, this.columnNum, this.selector.clbFrameSubType(this.minor));
					if(tempTile == null){
						System.err.println("Tile at row: " + rowNum + " col: " + this.columnNum + " does not exist");
					}
					else{
						tiles.add(tempTile);
						System.out.print("Tile: " + tempTile.getName() +": G = 0x" + String.format("%08X", goldenData.get(i)) + "  T = 0x" + String.format("%08X", targetData.get(i))+ "     ");
						//System.out.print("Tile: " + tempTile.getName() +": G = 0x" + Integer.toHexString(goldenData.get(i)) + "  T = 0x" + Integer.toHexString(targetData.get(i))+ "     ");
					}
				}
			}
			System.out.println("");
		}
		else{
			System.err.println("Unknown frame type " + this.columnFrameBlockSubType.getName());
		}
		
		return;
	}
	
	
	
	public BlockSubType getColumnFrameBlockSubType() {
		return columnFrameBlockSubType;
	}

	public void setColumnFrameBlockSubType(BlockSubType columnFrameBlockSubType) {
		this.columnFrameBlockSubType = columnFrameBlockSubType;
	}

	public HashSet<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(HashSet<Tile> tiles) {
		this.tiles = tiles;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public DeviceColumnInfo getSelector() {
		return selector;
	}

	public void setSelector(DeviceColumnInfo selector) {
		this.selector = selector;
	}

	public boolean isFrameTop() {
		return isFrameTop;
	}

	public void setFrameTop(boolean isFrameTop) {
		this.isFrameTop = isFrameTop;
	}

	public XilinxConfigurationSpecification getSpec() {
		return spec;
	}

	public void setSpec(XilinxConfigurationSpecification spec) {
		this.spec = spec;
	}
	
	public XilinxConfigurationSpecification getConfigSpec() {
		return configSpec;
	}
	public void setConfigSpec(XilinxConfigurationSpecification configSpec) {
		this.configSpec = configSpec;
	}
	public int getTop_bottom() {
		return top_bottom;
	}
	public void setTop_bottom(int top_bottom) {
		this.top_bottom = top_bottom;
	}
	public int getBlockType() {
		return blockType;
	}
	public void setBlockType(int blockType) {
		this.blockType = blockType;
	}
	
	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public int getMinor() {
		return minor;
	}
	public void setMinor(int minor) {
		this.minor = minor;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	public String getHexAddress() {
		return hexAddress;
	}
	public void setHexAddress(String hexAddress) {
		this.hexAddress = hexAddress;
	}
	public String getColumnBlockType() {
		return columnBlockType;
	}
	public void setColumnBlockType(String columnBlockType) {
		this.columnBlockType = columnBlockType;
	}
	public Frame getGoldenFrame() {
		return goldenFrame;
	}
	public void setGoldenFrame(Frame goldenFrame) {
		this.goldenFrame = goldenFrame;
	}
	public Frame getTargetFrame() {
		return targetFrame;
	}
	public void setTargetFrame(Frame targetFrame) {
		this.targetFrame = targetFrame;
	}
	public FrameAddressRegister getFrameAddressRegister() {
		return frameAddressRegister;
	}
	public void setFrameAddressRegister(FrameAddressRegister frameAddressRegister) {
		this.frameAddressRegister = frameAddressRegister;
	}
	
	public String printModifiedFrame(){
		return printOut;
	}
}
