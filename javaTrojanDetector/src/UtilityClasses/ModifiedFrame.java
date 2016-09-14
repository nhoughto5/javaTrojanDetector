package UtilityClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.byu.ece.rapidSmith.bitstreamTools.configuration.Frame;
import edu.byu.ece.rapidSmith.bitstreamTools.configuration.FrameAddressRegister;
import edu.byu.ece.rapidSmith.bitstreamTools.configuration.FrameData;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.AbstractConfigurationSpecification;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.BlockSubType;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.XilinxConfigurationSpecification;
import edu.byu.ece.rapidSmith.device.Tile;

public class ModifiedFrame {
	protected Frame goldenFrame, targetFrame;
	protected FrameAddressRegister frameAddressRegister;
	protected XilinxConfigurationSpecification configSpec;
	protected int top_bottom;
	protected int blockType;
	protected int row;
	protected int column;
	protected int minor, address, numberOfWordsPerTile;
	protected String hexAddress, printOut, columnBlockType, familyName;
	protected BlockSubType columnFrameBlockSubType;
	protected DeviceColumnInfo selector;
	protected XilinxConfigurationSpecification spec;
	protected boolean isFrameTop;
	protected List<Tile> tiles;
	
	public ModifiedFrame(Frame goldenFrame, Frame targetFrame, FrameAddressRegister frameAddressRegister, XilinxConfigurationSpecification spec) {
		this.goldenFrame = goldenFrame;
		this.targetFrame = targetFrame;
		this.top_bottom = frameAddressRegister.getTopBottom();
		this.blockType = frameAddressRegister.getBlockType(); 
		this.row = frameAddressRegister.getRow();
		this.column = frameAddressRegister.getColumn();
		this.minor = frameAddressRegister.getColumn();
		this.address = frameAddressRegister.getAddress();
		this.hexAddress = frameAddressRegister.getHexAddress();
		this.printOut = frameAddressRegister.toString(1);
		this.columnBlockType = frameAddressRegister.getFrameBlockType();
		this.columnFrameBlockSubType = frameAddressRegister.getFrameBlockSubType();
		this.spec = spec;
		this.isFrameTop = frameAddressRegister.isFrameTop();
		this.selector = new DeviceColumnInfo(this.spec.getDeviceFamily());
		createYCoordinateDifferenceseList();
	}
	
	private void createYCoordinateDifferenceseList(){
		List<Integer> goldenData = goldenFrame.getData().getAllFrameWords();
		List<Integer> targetData = targetFrame.getData().getAllFrameWords();
		if(goldenData.size() != targetData.size()){
			System.err.println("Target and Golden frame length do not match!");
			System.exit(1);
		}
		int numTopRows = spec.getTopNumberOfRows();
		int numBottomRows = spec.getBottomNumberOfRows();
		List<Integer> differentWordNumber = new ArrayList<>();
		if(this.columnFrameBlockSubType.getName().equalsIgnoreCase("CLB")){
			numberOfWordsPerTile = (this.spec.getFrameSize() - this.selector.getNumberOfClockWordsPerFrame()) / this.selector.getNumberOfTilesInCLBColumn();
			for(int i = 0; i < goldenData.size(); ++i){
				if(goldenData.get(i) != targetData.get(i)){
					differentWordNumber.add(i);
				}
			}
			for(int diffWordNum : differentWordNumber){
				int globalY = 
			}
		}
		else{
			System.err.println("Unknown frame type " + this.columnFrameBlockSubType.getName());
		}
	}
	
	
	
	public BlockSubType getColumnFrameBlockSubType() {
		return columnFrameBlockSubType;
	}

	public void setColumnFrameBlockSubType(BlockSubType columnFrameBlockSubType) {
		this.columnFrameBlockSubType = columnFrameBlockSubType;
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(List<Tile> tiles) {
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
