package UtilityClasses;

import edu.byu.ece.rapidSmith.bitstreamTools.configuration.Frame;
import edu.byu.ece.rapidSmith.bitstreamTools.configuration.FrameAddressRegister;
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
	protected int minor, address;
	protected String hexAddress, printOut, columnBlockType, columnFrameBlockSubType, familyName;
	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	protected Tile tile;
	
	public ModifiedFrame(Frame goldenFrame, Frame targetFrame, FrameAddressRegister frameAddressRegister, String familyName) {
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
		this.familyName = familyName;
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
	public String getColumnFrameBlockSubType() {
		return columnFrameBlockSubType;
	}
	public void setColumnFrameBlockSubType(String columnFrameBlockSubType) {
		this.columnFrameBlockSubType = columnFrameBlockSubType;
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
	public Tile getTile() {
		return tile;
	}
	public void setTile(Tile tile) {
		this.tile = tile;
	}
}
