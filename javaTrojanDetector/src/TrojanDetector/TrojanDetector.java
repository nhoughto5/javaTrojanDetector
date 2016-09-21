package TrojanDetector;

import java.io.File;
import java.util.ArrayList;
import deviceArchitecture.Architecture;
import utilityClasses.ModifiedFrame;
import edu.byu.ece.rapidSmith.bitstreamTools.examples.BitstreamDiff;
import edu.byu.ece.rapidSmith.device.Device;

public class TrojanDetector {
	BitstreamDiff diff; 
	ArrayList<ModifiedFrame> modifiedFrames;
	Device readDevice;
	Architecture architecture;

	public void performDetection(File goldenBitFile, File targetBitFile){
		String[] args = {"-i", goldenBitFile.getAbsolutePath(), "-c", targetBitFile.getAbsolutePath()};
		diff = new BitstreamDiff();
		this.readDevice = selectedDevice();
		modifiedFrames = diff.findDifferences(args);
		architecture = new Architecture(this.readDevice, diff.getDeviceType());
		architecture.loadArchitecture();
		matchFramesToTiles(args);
		return;
	}
	
	private Device selectedDevice(){
		Device device = new Device();
		device.readDeviceFromCompactFile("C:\\Users\\Nick\\git\\javaTrojanDetector\\javaTrojanDetector\\devices\\virtex5\\xc5vlx20tff323_db.dat");
		return device;
	}
	
	private void matchFramesToTiles(String[] args){
		for(ModifiedFrame mF : modifiedFrames){
			mF.createYCoordinateDifferenceseList(this.readDevice);
		}
	}
}
