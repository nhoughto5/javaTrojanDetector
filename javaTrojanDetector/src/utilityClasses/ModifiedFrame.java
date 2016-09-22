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
	protected int minor, address;
	protected String hexAddress, printOut, columnBlockType, familyName;
	protected BlockSubType columnFrameBlockSubType;
	protected DeviceColumnInfo selector;
	protected XilinxConfigurationSpecification spec;
	protected boolean isFrameTop;
	private Column column;
	private SubColumn subColumn;
	private List<Integer> modifiedFrameWordNumbers;
	private List<ModifiedTile> affectedTiles;
	
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
		this.affectedTiles = new ArrayList<ModifiedTile>();
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
		}
		//This frame configures the actual CLB of the CLB column
		else if((this.selector.getMaxIRFrameNumber() < this.minor) && (this.minor <= this.selector.getNumberOfFrames_CLBColumn())){
			this.subColumn = this.column.getSubColumnByType("CLB");
		}
		else{
			System.err.println("Mismatch frame address with column type");
			System.exit(-1);
		}
		
		List<Tile> regionTilesInColumn = this.subColumn.getAffectedTiles(totalNumRows, this.getNaturalRegionRowNumber());
		if(this.selector.getNumberOfTilesInCLBColumn() != regionTilesInColumn.size()){
			System.err.println("Unexpected number of Tiles returned");
			System.exit(-1);
		}
		int numberOfWordsPerTile = (this.spec.getFrameSize() - this.selector.getNumberOfClockWordsPerFrame()) / this.selector.getNumberOfTilesInCLBColumn();
		this.modifiedFrameWordNumbers = this.getModifiedWordNumbers();
		List<Integer> goldenData = goldenFrame.getData().getAllFrameWords();
		List<Integer> targetData = targetFrame.getData().getAllFrameWords();
		for (Integer i : this.modifiedFrameWordNumbers) {
			int subListStart = getTileStartWordNumber(i, numberOfWordsPerTile), subListEnd = getTileEndWordNumber(
					i, numberOfWordsPerTile);
			this.affectedTiles.add(new ModifiedTile(regionTilesInColumn
					.get(getTileId(i, numberOfWordsPerTile,
							regionTilesInColumn.size())), goldenData.subList(
					subListStart, subListEnd), targetData.subList(subListStart,
					subListEnd), numberOfWordsPerTile));
		}
	}
	
	private int getTileId(int i, int numberOfWordsPerTile, int numberOfTiles){	
		return numberOfWordsPerTile - ((int) i / numberOfTiles);
	}
	private void mapIOBColumn(){
		
	}
	
	private void mapDSPColumn(){
		
	}
	
	private void mapCLKColumn(){
		
	}
	
	private void mapBRAMColumn(){
		
	}
	private int getTileStartWordNumber(int ret, int numWordsPerTile){
		while((ret % numWordsPerTile) != 0){
			ret--;
		}
		return ret;
	}
	private int getTileEndWordNumber(int ret, int numWordsPerTile){
		while((ret % numWordsPerTile) != 0){
			ret--;
		}
		return ret + numWordsPerTile - 1;
	}
	private List<Integer> getModifiedWordNumbers(){
		List<Integer> ret = new ArrayList<>();
		List<Integer> goldenData = goldenFrame.getData().getAllFrameWords();
		List<Integer> targetData = targetFrame.getData().getAllFrameWords();
		if(goldenData.size() != targetData.size()){
			System.err.println("Target and Golden frame data length do not match!");
			System.exit(1);
		}
		for(int i = 0; i < goldenData.size(); ++i){
			if(goldenData.get(i) != targetData.get(i)){
				ret.add(i);
			}
		}
		return ret;
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
	
	public List<ModifiedTile> getAffectedTiles() {
		return affectedTiles;
	}

	public void setAffectedTiles(List<ModifiedTile> affectedTiles) {
		this.affectedTiles = affectedTiles;
	}

	public BlockSubType getColumnFrameBlockSubType() {
		return columnFrameBlockSubType;
	}

	public void setColumnFrameBlockSubType(BlockSubType columnFrameBlockSubType) {
		this.columnFrameBlockSubType = columnFrameBlockSubType;
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
