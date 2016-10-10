package utilityClasses;

public class DeviceColumnInfo {
	private int maxIRFrameNumber, maxInterfaceFrameNumber, numberOfFrames_CLBColumn, numberOfPrimaryTilesInColumn,
		numberOfClockWordsPerFrame, numberOfFrames_IOBColumn, numberOfFrames_DSPColumn, numberOfFrames_BRAMColumn, numberOfFrames_CLKColumn;
	public DeviceColumnInfo(String deviceName) {
		
		/*According to Ug_191: The frames are numbered from left to right, starting with 0. For each block, except the clock
		column, frames numbered 0 to 25 access the Interconnect for that column. For all blocks,
		except the CLB and the clock column, frames numbered 26 and 27 access the Interface for
		that column. All other frames are specific to that block.*/
		//Refer to Table 16-6, page 133 of ug191_Virtex5_ConfigGuide
		if(deviceName.equalsIgnoreCase("VIRTEX5")){
			this.maxIRFrameNumber = 25;
			this.maxInterfaceFrameNumber = 27;
			this.numberOfFrames_CLBColumn = 36;
			this.numberOfClockWordsPerFrame = 1;
			this.numberOfPrimaryTilesInColumn = 20;
			this.numberOfFrames_IOBColumn = 54;
			this.numberOfFrames_DSPColumn = 28;
			this.numberOfFrames_BRAMColumn = 30;
			this.numberOfFrames_CLKColumn = 4;
		}
		else{
			System.err.println("Unrecognized Family Name");
			System.exit(1);
		}
		
	}

	public String clbFrameSubType(int minorAddress){
		if(minorAddress <= this.maxIRFrameNumber){
			return "IR";
		}
		else if((minorAddress <= this.maxInterfaceFrameNumber) && (minorAddress > this.maxIRFrameNumber)){
			return "interFace";
		}
		else if ((minorAddress <= this.numberOfFrames_CLBColumn) && (minorAddress > this.maxInterfaceFrameNumber)){
			return "CLB";
		}
		else{
			System.err.println("Unexpected minor address");
			System.exit(1);
			return "NA";
		}
	}
	public int getNumberOfFrames_IOBColumn() {
		return numberOfFrames_IOBColumn;
	}

	public void setNumberOfFrames_IOBColumn(int numberOfFrames_IOBColumn) {
		this.numberOfFrames_IOBColumn = numberOfFrames_IOBColumn;
	}

	public int getNumberOfFrames_DSPColumn() {
		return numberOfFrames_DSPColumn;
	}

	public void setNumberOfFrames_DSPColumn(int numberOfFrames_DSPColumn) {
		this.numberOfFrames_DSPColumn = numberOfFrames_DSPColumn;
	}

	public int getNumberOfFrames_BRAMColumn() {
		return numberOfFrames_BRAMColumn;
	}

	public void setNumberOfFrames_BRAMColumn(int numberOfFrames_BRAMColumn) {
		this.numberOfFrames_BRAMColumn = numberOfFrames_BRAMColumn;
	}

	public int getNumberOfFrames_CLKColumn() {
		return numberOfFrames_CLKColumn;
	}

	public void setNumberOfFrames_CLKColumn(int numberOfFrames_CLKColumn) {
		this.numberOfFrames_CLKColumn = numberOfFrames_CLKColumn;
	}

	
	public int getNumberOfPrimaryTilesInColumn() {
		return numberOfPrimaryTilesInColumn;
	}

	public void setNumberOfPrimaryTilesInColumn(int numberOfPrimaryTilesInColumn) {
		this.numberOfPrimaryTilesInColumn = numberOfPrimaryTilesInColumn;
	}

	public int getNumberOfClockWordsPerFrame() {
		return numberOfClockWordsPerFrame;
	}

	public void setNumberOfClockWordsPerFrame(int numberOfClockWordsPerFrame) {
		this.numberOfClockWordsPerFrame = numberOfClockWordsPerFrame;
	}

	public int getNumberOfFrames_CLBColumn() {
		return numberOfFrames_CLBColumn;
	}

	public void setNumberOfFrames_CLBColumn(int numberOfFrames_CLBColumn) {
		this.numberOfFrames_CLBColumn = numberOfFrames_CLBColumn;
	}

	public int getMaxIRFrameNumber() {
		return maxIRFrameNumber;
	}

	public void setMaxIRFrameNumber(int maxIRFrameNumber) {
		this.maxIRFrameNumber = maxIRFrameNumber;
	}

	public int getMaxInterfaceFrameNumber() {
		return maxInterfaceFrameNumber;
	}

	public void setMaxInterfaceFrameNumber(int maxInterfaceFrameNumber) {
		this.maxInterfaceFrameNumber = maxInterfaceFrameNumber;
	}
}
