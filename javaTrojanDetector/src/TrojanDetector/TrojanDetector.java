package TrojanDetector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import UtilityClasses.DeviceCLBColumnSperator;
import UtilityClasses.ModifiedFrame;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.BlockSubType;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.BlockType;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.XilinxConfigurationSpecification;
import edu.byu.ece.rapidSmith.bitstreamTools.examples.BitstreamDiff;
import edu.byu.ece.rapidSmith.device.Device;
import edu.byu.ece.rapidSmith.device.PrimitiveSite;
import edu.byu.ece.rapidSmith.device.Tile;
import edu.byu.ece.rapidSmith.util.FamilyType;

public class TrojanDetector {
	BitstreamDiff diff; 
	ArrayList<ModifiedFrame> modifiedFrames;
	public void performDetection(File goldenBitFile, File targetBitFile){
		String[] args = {"-i", goldenBitFile.getAbsolutePath(), "-c", targetBitFile.getAbsolutePath()};
		diff = new BitstreamDiff();
		modifiedFrames = diff.findDifferences(args); 
		matchFramesToTiles(args);
	}
	
	private Device selectedDevice(){
		Device device = new Device();
		device.readDeviceFromCompactFile("C:\\Users\\Nick\\git\\javaTrojanDetector\\javaTrojanDetector\\devices\\virtex5\\xc5vlx20tff323_db.dat");
		return device;
	}
	
	private void matchFramesToTiles(String[] args){
		Device readDevice = selectedDevice();
		Tile[][] tiles = readDevice.getTiles();
		Tile test = tiles[13][21];
		PrimitiveSite[] sites = tiles[13][21].getPrimitiveSites();
		XilinxConfigurationSpecification deviceType = diff.getDeviceType();
		DeviceCLBColumnSperator seperator = new DeviceCLBColumnSperator(readDevice.getFamilyType().toString());
		for(ModifiedFrame frame : modifiedFrames){
			if(frame.getColumnFrameBlockSubType().equals("CLB")){
				frame.getColumn(); frame.getMinor();
				//PrimitiveSite[] sites = tiles[frame.getColumn()][]
			}
		}
	}
}
