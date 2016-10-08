package TrojanDetector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import deviceArchitecture.Architecture;
import utilityClasses.ModifiedFrame;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.XilinxConfigurationSpecification;
import edu.byu.ece.rapidSmith.bitstreamTools.examples.BitstreamDiff;
import edu.byu.ece.rapidSmith.device.Device;

public class TrojanDetector {
	BitstreamDiff diff; 
	ArrayList<ModifiedFrame> modifiedFrames;
	Device readDevice;
	Architecture architecture;
	Trojan trojan;
	String dbPath = "C:\\Users\\Nick\\git\\javaTrojanDetector\\javaTrojanDetector\\devices\\virtex5";
	public void performDetection(File goldenBitFile, File targetBitFile){
		this.trojan = new Trojan();
		String[] args = {"-i", goldenBitFile.getAbsolutePath(), "-c", targetBitFile.getAbsolutePath()};
		diff = new BitstreamDiff();
		modifiedFrames = diff.findDifferences(args);
		XilinxConfigurationSpecification partType = diff.getDeviceType();
		this.readDevice = selectedDevice(partType);
		this.architecture = new Architecture(this.readDevice, partType);
		matchFramesToTiles();
		return;
	}
	
	private Device selectedDevice(XilinxConfigurationSpecification partType){
		Device device = new Device();
		String partName = partType.getDeviceName().toLowerCase();
		String f = dbPath +"\\" + getDBFileName(partName);
		device.readDeviceFromCompactFile(f);
		return device;
	}
	private String getDBFileName(String partName){
		List<String> names = getAllDBFileNames();
		for(String n : names){
			if(n.contains(partName)){
				return n;
			}
		}
		return "null";
	}
	private List<String> getAllDBFileNames(){
		File folder = new File(dbPath);
		File[] listOfFiles = folder.listFiles();
		List<String> fileNames = new ArrayList<>();
	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	        //System.out.println("File " + listOfFiles[i].getName());
	    	  fileNames.add(listOfFiles[i].getName());
	      } else if (listOfFiles[i].isDirectory()) {
	        //System.out.println("Directory " + listOfFiles[i].getName());
	      }
	    }
	    return fileNames;
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
