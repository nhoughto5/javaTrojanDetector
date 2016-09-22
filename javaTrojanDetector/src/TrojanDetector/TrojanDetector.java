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
	Trojan trojan;
	public void performDetection(File goldenBitFile, File targetBitFile){
		this.trojan = new Trojan();
		String[] args = {"-i", goldenBitFile.getAbsolutePath(), "-c", targetBitFile.getAbsolutePath()};
		diff = new BitstreamDiff();
		this.readDevice = selectedDevice();
		modifiedFrames = diff.findDifferences(args);
		this.architecture = new Architecture(this.readDevice, diff.getDeviceType());
		matchFramesToTiles();
		return;
	}
	
	private Device selectedDevice(){
		Device device = new Device();
		device.readDeviceFromCompactFile("C:\\Users\\Nick\\git\\javaTrojanDetector\\javaTrojanDetector\\devices\\virtex5\\xc5vlx20tff323_db.dat");
		return device;
	}
	
	private void matchFramesToTiles(){
		for(ModifiedFrame mF : modifiedFrames){
			mF.mapTiles(this.architecture.getColumn(mF.getColumnNum()));
			trojan.addTiles(mF.getAffectedTiles());
		}
		trojan.printTileNames();
		return;
	}
}
