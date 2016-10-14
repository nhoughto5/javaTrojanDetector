package utilityClasses;

import java.util.ArrayList;
import java.util.List;

import deviceArchitecture.Column;
import deviceArchitecture.SubColumn;
import edu.byu.ece.rapidSmith.bitstreamTools.configuration.Frame;
import edu.byu.ece.rapidSmith.bitstreamTools.configuration.FrameAddressRegister;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.BlockSubType;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.XilinxConfigurationSpecification;
import edu.byu.ece.rapidSmith.device.Tile;

public class ModifiedFrame {
	private List<ModifiedTile> affectedTiles;
	protected int blockType;
	private Column column;
	protected BlockSubType columnFrameBlockSubType;
	protected int columnNum;
	protected XilinxConfigurationSpecification configSpec;
	protected DeviceColumnInfo deviceInfo;
	protected FrameAddressRegister frameAddressRegister;
	protected Frame goldenFrame, targetFrame;
	protected String hexAddress, printOut, columnBlockType, familyName;
	protected boolean isFrameTop;
	protected int minor, address;
	private List<Integer> modifiedFrameWordNumbers;
	protected int rowNum;
	protected XilinxConfigurationSpecification spec;
	private SubColumn subColumn;
	protected int top_bottom;

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
		this.deviceInfo = new DeviceColumnInfo(this.spec.getDeviceFamily());
		this.affectedTiles = new ArrayList<ModifiedTile>();
		this.frameAddressRegister = new FrameAddressRegister(this.spec,
				this.address);
	}

	private void findModifiedTiles() {
		int totalNumRows = this.spec.getTopNumberOfRows()+ this.spec.getBottomNumberOfRows();
		int naturalRowNum = this.getNaturalRegionRowNumber();
		List<Tile> regionTilesInColumn = this.subColumn.getAffectedTiles(totalNumRows, naturalRowNum);
		int numOfWordsPerFrameMinusClockTile = (this.spec.getFrameSize() - this.deviceInfo.getNumberOfClockWordsPerFrame());
		int numberOfWordsPerTile = numOfWordsPerFrameMinusClockTile	/ (regionTilesInColumn.size() - this.deviceInfo.getNumberOfClockWordsPerFrame());
		this.modifiedFrameWordNumbers = this.getModifiedWordNumbers();
		List<Integer> goldenData = goldenFrame.getData().getAllFrameWords();
		List<Integer> targetData = targetFrame.getData().getAllFrameWords();
		for (Integer i : this.modifiedFrameWordNumbers) {
			int subListStart = getTileStartWordNumber(i, numberOfWordsPerTile,
					numOfWordsPerFrameMinusClockTile);
			int subListEnd = getTileEndWordNumber(i, numberOfWordsPerTile,
					numOfWordsPerFrameMinusClockTile);

			int tileID = getTileId(i, numberOfWordsPerTile,
					regionTilesInColumn.size());
			Tile t = regionTilesInColumn.get(tileID);
			List<TileWord> tWords = getTileWords(goldenData, subListStart,
					subListEnd);
			List<TileWord> tWords2 = getTileWords(targetData, subListStart,
					subListEnd);

			this.affectedTiles.add(new ModifiedTile(t, tWords, tWords2,
					numberOfWordsPerTile, this.spec, this.frameAddressRegister
							.getAddress(), this.subColumn.getSubColumnType()));
		}
	}

	public int getAddress() {
		return address;
	}

	public List<ModifiedTile> getAffectedTiles() {
		return affectedTiles;
	}

	public int getBlockType() {
		return blockType;
	}

	public Column getColumn() {
		return column;
	}

	public String getColumnBlockType() {
		return columnBlockType;
	}

	public BlockSubType getColumnFrameBlockSubType() {
		return columnFrameBlockSubType;
	}

	public int getColumnNum() {
		return columnNum;
	}

	public XilinxConfigurationSpecification getConfigSpec() {
		return configSpec;
	}

	public DeviceColumnInfo getdeviceInfo() {
		return deviceInfo;
	}

	public String getFamilyName() {
		return familyName;
	}

	public FrameAddressRegister getFrameAddressRegister() {
		return frameAddressRegister;
	}

	public Frame getGoldenFrame() {
		return goldenFrame;
	}

	public String getHexAddress() {
		return hexAddress;
	}

	public int getMinor() {
		return minor;
	}

	private List<Integer> getModifiedWordNumbers() {
		List<Integer> ret = new ArrayList<>();
		List<Integer> goldenData = goldenFrame.getData().getAllFrameWords();
		List<Integer> targetData = targetFrame.getData().getAllFrameWords();
		if (goldenData.size() != targetData.size()) {
			Error.printError(
					"Target and Golden frame data length do not match!",
					new Exception().getStackTrace()[0]);
		}
		for (int i = 0; i < goldenData.size(); ++i) {
			if (goldenData.get(i) != targetData.get(i)) {
				ret.add(i);
			}
		}
		return ret;
	}

	// Returns which row the frame is in, in a more natural semantic.
	// Rows begin at the bottom of the device at 0.
	private int getNaturalRegionRowNumber() {
		if (this.isFrameTop) {
			return this.spec.getBottomNumberOfRows() + this.rowNum;
		} else {
			return this.spec.getBottomNumberOfRows() - rowNum - 1;
		}
	}

	public int getRowNum() {
		return rowNum;
	}

	public XilinxConfigurationSpecification getSpec() {
		return spec;
	}

	public Frame getTargetFrame() {
		return targetFrame;
	}

	private int getTileEndWordNumber(int ret, int numWordsPerTile,
			int maxWordNum) {
		if (ret == maxWordNum) {
			return ret;
		} else {
			while ((ret % numWordsPerTile) != 0) {
				ret--;
			}
			return ret + numWordsPerTile - 1;
		}
	}

	private int getTileId(int j, int numberOfWordsPerPrimaryTile,
			int numberOfTiles) {
		int numberOfClockWords = 1, numWords = this.spec.getFrameSize();
		int middleClockWord = (numWords - numberOfClockWords) / 2;
		int tileNum = 0;
		if (j < middleClockWord) {
			tileNum = (int) (j / numberOfWordsPerPrimaryTile);
		} else if (j == middleClockWord) {
			tileNum = (numberOfTiles - 1) / numberOfWordsPerPrimaryTile;
		} else {
			tileNum = (int) ((j - 1) / numberOfWordsPerPrimaryTile) + 1;
		}
		return tileNum;
	}

	private int getTileStartWordNumber(int ret, int numWordsPerTile,
			int maxWordNum) {
		if (ret == maxWordNum) {
			ret = ret - numWordsPerTile + 1;
		} else {
			while ((ret % numWordsPerTile) != 0) {
				ret--;
			}
		}
		return ret;
	}

	private List<TileWord> getTileWords(List<Integer> data, int from, int to) {
		List<TileWord> words = new ArrayList<>();
		List<Integer> tempWords = new ArrayList<>();
		try {
			tempWords = data.subList(from, to + 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Integer i : tempWords) {
			words.add(new TileWord(i, this.address));
		}
		return words;
	}

	public int getTop_bottom() {
		return top_bottom;
	}

	public boolean isFrameTop() {
		return isFrameTop;
	}

	private void mapBRAMColumn() {
		// This frame configures the Interconnect of the BRAM column
		if (this.minor <= this.deviceInfo.getMaxIRFrameNumber()) {
			this.subColumn = this.column.getSubColumnByType("INTERCONNECT");
		}
		// This frame configures the interface of the BRAM column
		else if ((this.deviceInfo.getMaxIRFrameNumber() < this.minor)
				&& (this.minor <= this.deviceInfo.getMaxInterfaceFrameNumber())) {
			this.subColumn = this.column.getSubColumnByType("INTERFACE");
		}
		// This frame configures the actual CLB of the BRAM column
		else if ((this.deviceInfo.getMaxInterfaceFrameNumber() < this.minor)
				&& (this.minor <= this.deviceInfo.getNumberOfFrames_CLBColumn())) {
			this.subColumn = this.column.getSubColumnByType("BRAMINTERCONNECT");
		} else {
			Error.printError("Mismatch frame address with column type: CLB",
					new Exception().getStackTrace()[0]);
		}
		findModifiedTiles();
	}

	private void mapCLBColumn() {

		// This frame configures the Interconnect of the CLB column
		if (this.minor <= this.deviceInfo.getMaxIRFrameNumber()) {
			this.subColumn = this.column.getSubColumnByType("INTERCONNECT");
		}
		// This frame configures the actual CLB of the CLB column
		else if ((this.deviceInfo.getMaxIRFrameNumber() < this.minor)
				&& (this.minor <= this.deviceInfo.getNumberOfFrames_CLBColumn())) {
			this.subColumn = this.column.getSubColumnByType("CLB");
		} else {
			Error.printError("Mismatch frame address with column type: CLB",
					new Exception().getStackTrace()[0]);
		}
		findModifiedTiles();
	}

	private void mapCLKColumn() {
		// This frame configures the Interconnect of the BRAM column
		if (this.minor <= this.deviceInfo.getMaxIRFrameNumber()) {
			this.subColumn = this.column.getSubColumnByType("BUFS");
		}
		// This frame configures the interface of the BRAM column
		else if ((this.deviceInfo.getMaxIRFrameNumber() < this.minor)
				&& (this.minor <= this.deviceInfo.getMaxInterfaceFrameNumber())) {
			this.subColumn = this.column.getSubColumnByType("INTERCONNECT");
		}
		// This frame configures the actual CLB of the BRAM column
		else if ((this.deviceInfo.getMaxInterfaceFrameNumber() < this.minor)
				&& (this.minor <= this.deviceInfo.getNumberOfFrames_CLBColumn())) {
			this.subColumn = this.column.getSubColumnByType("CLB");
		} else {
			Error.printError("Mismatch frame address with column type: CLB",
					new Exception().getStackTrace()[0]);
		}
		findModifiedTiles();
	}

	private void mapDSPColumn() {
		// This frame configures the Interconnect of the BRAM column
		if (this.minor <= this.deviceInfo.getMaxIRFrameNumber()) {
			this.subColumn = this.column.getSubColumnByType("INTERCONNECT");
		}
		// This frame configures the interface of the BRAM column
		else if ((this.deviceInfo.getMaxIRFrameNumber() < this.minor)
				&& (this.minor <= this.deviceInfo.getMaxInterfaceFrameNumber())) {
			this.subColumn = this.column.getSubColumnByType("INTERFACE");
		}
		// This frame configures the actual CLB of the BRAM column
		else if ((this.deviceInfo.getMaxInterfaceFrameNumber() < this.minor)
				&& (this.minor <= this.deviceInfo.getNumberOfFrames_CLBColumn())) {
			this.subColumn = this.column.getSubColumnByType("DSP");
		} else {
			Error.printError("Mismatch frame address with column type: CLB",
					new Exception().getStackTrace()[0]);
		}
		findModifiedTiles();
	}

	private void mapIOBColumn() {

		// This frame configures the interconnect tiles of the IOB column
		if (this.minor <= this.deviceInfo.getMaxIRFrameNumber()) {
			this.subColumn = this.column.getSubColumnByType("INTERCONNECT");
		} else if ((this.minor > this.deviceInfo.getMaxIRFrameNumber())
				&& (this.minor <= this.deviceInfo.getMaxInterfaceFrameNumber())) {
			this.subColumn = this.column.getSubColumnByType("INTERFACE");
		} else if ((this.minor > this.deviceInfo.getMaxInterfaceFrameNumber())
				&& (this.minor <= this.deviceInfo.getNumberOfFrames_IOBColumn())) {
			this.subColumn = this.column.getSubColumnByType("IOB");
		} else {
			Error.printError("Mismatch frame address with column type: IOB",
					new Exception().getStackTrace()[0]);
		}
		findModifiedTiles();
	}

	public void mapTiles(Column column) {
		this.column = column;

		// Logic frames
		if (this.frameAddressRegister.getBlockType() == 0) {
			BlockSubType bST = this.spec.getBlockSubtype(this.spec,
					this.frameAddressRegister);
			if (this.column.getColumn() != this.columnNum) {
				Error.printError("Wrong Column Number",
						new Exception().getStackTrace()[0]);
			}
			if (!this.column.getColumnType().equals(bST.getName())) {
				Error.printError("Wrong Column Type, Col Number ("
						+ this.columnNum + ")",
						new Exception().getStackTrace()[0]);
			}
			String colType = column.getColumnType();
			switch (bST.getName()) {
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
				Error.printError(
						"Modified Frame does not match know Column Type: "
								+ colType + ": " + this.column.getColumn(),
						new Exception().getStackTrace()[0]);
				break;
			}
		}
		// BRAM Content
		else if (this.frameAddressRegister.getBlockType() == 1) {

		} else {
			Error.printError(
					"New block type: "
							+ this.frameAddressRegister.getBlockType(),
					new Exception().getStackTrace()[0]);
		}
	}

	public String printModifiedFrame() {
		return printOut;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public void setAffectedTiles(List<ModifiedTile> affectedTiles) {
		this.affectedTiles = affectedTiles;
	}

	public void setBlockType(int blockType) {
		this.blockType = blockType;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public void setColumnBlockType(String columnBlockType) {
		this.columnBlockType = columnBlockType;
	}

	public void setColumnFrameBlockSubType(BlockSubType columnFrameBlockSubType) {
		this.columnFrameBlockSubType = columnFrameBlockSubType;
	}

	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}

	public void setConfigSpec(XilinxConfigurationSpecification configSpec) {
		this.configSpec = configSpec;
	}

	public void setdeviceInfo(DeviceColumnInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public void setFrameAddressRegister(
			FrameAddressRegister frameAddressRegister) {
		this.frameAddressRegister = frameAddressRegister;
	}

	public void setFrameTop(boolean isFrameTop) {
		this.isFrameTop = isFrameTop;
	}

	public void setGoldenFrame(Frame goldenFrame) {
		this.goldenFrame = goldenFrame;
	}

	public void setHexAddress(String hexAddress) {
		this.hexAddress = hexAddress;
	}

	public void setMinor(int minor) {
		this.minor = minor;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public void setSpec(XilinxConfigurationSpecification spec) {
		this.spec = spec;
	}

	public void setTargetFrame(Frame targetFrame) {
		this.targetFrame = targetFrame;
	}

	public void setTop_bottom(int top_bottom) {
		this.top_bottom = top_bottom;
	}
}
