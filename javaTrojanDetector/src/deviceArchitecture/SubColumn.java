package deviceArchitecture;

import edu.byu.ece.rapidSmith.bitstreamTools.configuration.Frame;

public class SubColumn {
	private Frame frame;
	private int frameAddress;
	
	public SubColumn() {

	}
	
	public SubColumn(int frameAddress) {
		this.frameAddress = frameAddress;
	}

	public SubColumn(Frame frame, int frameAddress) {
		this.frame = frame;
		this.frameAddress = frameAddress;
	}
	public Frame getFrame() {
		return frame;
	}
	public void setFrame(Frame frame) {
		this.frame = frame;
	}
	public int getFrameAddress() {
		return frameAddress;
	}
	public void setFrameAddress(int frameAddress) {
		this.frameAddress = frameAddress;
	}
}
