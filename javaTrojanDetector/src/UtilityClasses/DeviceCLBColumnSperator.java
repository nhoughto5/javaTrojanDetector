package UtilityClasses;

public class DeviceCLBColumnSperator {
	private int maxIRFrameNumber, maxInterfaceFrameNumber, numberOfFrames_CLBColumn, numberOfTilesInCLBColumn;

	public DeviceCLBColumnSperator(String deviceName) {
		if(deviceName.equals("VIRTEX5")){
			maxIRFrameNumber = 25;
			maxInterfaceFrameNumber = 27;
			numberOfFrames_CLBColumn = 36;
		}
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
