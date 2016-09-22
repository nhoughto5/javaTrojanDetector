package utilityClasses;

import edu.byu.ece.rapidSmith.bitstreamTools.configuration.FrameAddressRegister;

public class TileWord {
	int value, address;
	FrameAddressRegister far;
	public TileWord(int value, int address) {
		this.value = value;
		this.address = address;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}
	
	public int getMinorAddress(){
		return far.getMinorFromAddress(this.address);
	}
	
	public int getColumnAddress(){
		return far.getColumnFromAddress(address);
	}
	public boolean isTopRow(){
		return far.isFrameTopFromAddress(address);
	}

}
