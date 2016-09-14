package UtilityClasses;

public class DeviceColumnInfo {
	private int maxIRFrameNumber, maxInterfaceFrameNumber, numberOfFrames_CLBColumn, numberOfTilesInCLBColumn,
		numberOfClockWordsPerFrame, numberOfFrames_IOBColumn, numberOfFrames_DSPColumn, numberOfFrames_BRAMColumn, numberOfFrames_CLKColumn;

	public DeviceColumnInfo(String deviceName) {
		if(deviceName.equalsIgnoreCase("VIRTEX5")){
			this.maxIRFrameNumber = 25;
			this.maxInterfaceFrameNumber = 27;
			this.numberOfFrames_CLBColumn = 36;
			this.numberOfClockWordsPerFrame = 1;
			this.numberOfTilesInCLBColumn = 20;
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

	public int getNumberOfTilesInCLBColumn() {
		return numberOfTilesInCLBColumn;
	}

	public void setNumberOfTilesInCLBColumn(int numberOfTilesInCLBColumn) {
		this.numberOfTilesInCLBColumn = numberOfTilesInCLBColumn;
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
