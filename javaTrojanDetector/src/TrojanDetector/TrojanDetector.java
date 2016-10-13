package TrojanDetector;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import deviceArchitecture.Architecture;
import utilityClasses.Error;
import utilityClasses.ModifiedFrame;
import utilityClasses.ModifiedInstance;
import utilityClasses.ModifiedTile;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.XilinxConfigurationSpecification;
import edu.byu.ece.rapidSmith.bitstreamTools.examples.BitstreamDiff;
import edu.byu.ece.rapidSmith.design.Attribute;
import edu.byu.ece.rapidSmith.design.Design;
import edu.byu.ece.rapidSmith.design.Instance;
import edu.byu.ece.rapidSmith.design.Net;
import edu.byu.ece.rapidSmith.device.Device;
import edu.byu.ece.rapidSmith.device.PrimitiveSite;

public class TrojanDetector {
	BitstreamDiff diff; 
	ArrayList<ModifiedFrame> modifiedFrames;
	Device readDevice;
	Architecture architecture;
	Trojan trojan;
	File xdlFile;
	private Design design;
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

	public void loadDesign(File xdlFile){
		this.xdlFile = xdlFile;
		this.design = new Design(this.xdlFile.getAbsolutePath());
	}
	
	public void mapModifiedTilesToDesign(){
		//Collection<Instance> instances = this.design.getInstances();
		if(this.trojan == null){
			Error.printError("Please perform bitstream analysis", new Exception().getStackTrace()[0]);
		}
		HashSet<Instance> modifiedInstances = new HashSet<>();
		List<ModifiedTile> mT = this.trojan.getAffectedTiles();
		HashSet<Net> netSet = new HashSet<>();
		for(int i = 0; i < mT.size(); i++){
			PrimitiveSite[] pS = mT.get(i).getTile().getPrimitiveSites();
			if(pS != null){
				for(PrimitiveSite p : pS){
					Instance tI = this.design.getInstanceAtPrimitiveSite(p);
					if(tI != null){
						System.out.print(tI.getName() + "  " + tI.getType().toString());
//						tI.getModuleInstanceName()
//						Collection<Attribute> a = tI.getAttributes();
//						for(Attribute aI : a){
//							System.out.print(aI.toString() + "  ");
//						}
						System.out.print("\n");
						modifiedInstances.add(tI);
						netSet.addAll(tI.getNetList());
						mT.get(i).addInstance(new ModifiedInstance(tI, p));
					}
					
				}
			}
		}
		this.trojan.setAffectedTiles(mT);
		this.trojan.setAffectedNets(netSet);
		this.trojan.printAffectedNets();
		
	}
}
