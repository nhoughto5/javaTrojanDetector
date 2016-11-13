package TrojanDetector;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.JTextArea;

import joptsimple.OptionSet;
import deviceArchitecture.Architecture;
import trojanAttribute.AttributeManager;
import trojanAttribute.RelationMatrix;
import trojanAttribute.TrojanAttribute;
import utilityClasses.Error;
import utilityClasses.ModifiedFrame;
import utilityClasses.ModifiedInstance;
import utilityClasses.ModifiedTile;
import edu.byu.ece.rapidSmith.bitstreamTools.configuration.Frame;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.XilinxConfigurationSpecification;
import edu.byu.ece.rapidSmith.bitstreamTools.examples.BitstreamDiff;
import edu.byu.ece.rapidSmith.design.Design;
import edu.byu.ece.rapidSmith.design.Instance;
import edu.byu.ece.rapidSmith.design.Net;
import edu.byu.ece.rapidSmith.device.Device;
import edu.byu.ece.rapidSmith.device.PrimitiveSite;
import edu.byu.ece.rapidSmith.device.Tile;

public class TrojanDetector {
	private Architecture architecture;
	private String dbPath = "C:\\Users\\Nick\\git\\javaTrojanDetector\\javaTrojanDetector\\devices\\virtex5";
	private Design design;
	private BitstreamDiff diff;
	private ArrayList<ModifiedFrame> modifiedFrames;
	private Device readDevice;
	private Trojan trojan;
	private File xdlFile;
	private List<TrojanAttribute> trojanAttributes;
	private JTextArea messageArea;
	private List<Frame> goldenFrames, targetFrames;
	public TrojanDetector(JTextArea messageArea){
		this.messageArea = messageArea;
	}
	
	private List<String> getAllDBFileNames() {
		File folder = new File(dbPath);
		File[] listOfFiles = folder.listFiles();
		List<String> fileNames = new ArrayList<>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				fileNames.add(listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
			}
		}
		return fileNames;
	}

	private String getDBFileName(String partName) {
		List<String> names = getAllDBFileNames();
		for (String n : names) {
			if (n.contains(partName)) {
				return n;
			}
		}
		return "null";
	}

	public void loadDesign(File xdlFile) {
		this.xdlFile = xdlFile;
		this.design = new Design(this.xdlFile.getAbsolutePath());
	}

	public void mapModifiedTilesToDesign() {
		// Collection<Instance> instances = this.design.getInstances();
		if (this.trojan == null) {
			Error.printError("Please perform bitstream analysis",
					new Exception().getStackTrace()[0]);
		}
		HashSet<Instance> modifiedInstances = new HashSet<>();
		List<ModifiedTile> mT = this.trojan.getAffectedTiles();
		HashSet<Net> netSet = new HashSet<>();
		//Collection<Net> netMap = this.design.getNets();
		//List<String> netSourceTilesNames = getNetSourceTiles();
		HashMap<String, Net> netMap = this.design.getNetMap();
		for (int i = 0; i < mT.size(); i++) {
			PrimitiveSite[] pS = mT.get(i).getTile().getPrimitiveSites();
//			String tileName = mT.get(i).getTile().getName();
//			if(netSourceTilesNames.contains(tileName)){
//				netSet.add(this.design.getNetBySourceTileName(tileName));
//			}
			if (pS != null) {
				for (PrimitiveSite p : pS) {
					Instance tI = this.design.getInstanceAtPrimitiveSite(p);
					if (tI != null) {
						//System.out.println(tI.getName() + "  "+ tI.getType().toString());
						modifiedInstances.add(tI);
						netSet.addAll(tI.getNetList());
						mT.get(i).addInstance(new ModifiedInstance(tI, p));
					}

				}
			}
		}	
		
		this.trojan.setGoldenDesign(this.design);
		this.trojan.setAffectedTiles(mT);
		this.trojan.setAffectedNets(netSet);
		AttributeManager aM = new AttributeManager(this.trojan);
		RelationMatrix R = new RelationMatrix();
		this.trojanAttributes = R.analyzeMatrix(aM.getTrojanAttributes());
		printTrojanAttributes();
	}
	public List<String> getNetSourceTiles(){
		Collection<Net> netTiles = this.design.getNets();
		List<String> ret = new ArrayList<>();
		for(Net n : netTiles){
			Tile t = n.getSourceTile();
			if(t != null){
				ret.add(t.getName());
			}
			
		}
		return ret;
	}
	
	public void printAffectedTiles(JTextArea messageArea){
		this.trojan.printAffectedTiles(messageArea);
	}
	
	public void printAffectedInstances(JTextArea messageArea){
		this.trojan.printAffectedInstances(messageArea);
	}
	
	public void printAffectedNetNames(JTextArea messageArea){
		this.trojan.printAffectedNetNames(messageArea);
//		Collection<Net> nets = this.design.getNets();
//		messageArea.setText("");
//		StringBuffer sBuffer = new StringBuffer();
//		sBuffer.append("Interconnect Networks Modified " + "\n\n");
//		for(Net n : nets){
//			if(this.trojan.doesTrojanModifyTile(n.getSourceTile())){
//				sBuffer.append("Net: " + n.getName() + "\n");
//			}
//		}
//		messageArea.setText(sBuffer.toString());
	}
	
	public void printAffectedNets(JTextArea messageArea){
		this.trojan.printAffectedNets(messageArea);
	}
	
	public void printTrojanAttributes(){
		this.messageArea.setText("");
		StringBuffer sBuffer = new StringBuffer();
		if(this.trojan.getAffectedTiles().size() == 0){
			sBuffer.append("There is no trojan present");
		}
		else{
			for(TrojanAttribute t : this.trojanAttributes){
				sBuffer.append(t.toString() + "\n\n");
			}
		}

		this.messageArea.setText(sBuffer.toString());
	}
	private void matchFramesToTiles() {
		for (ModifiedFrame mF : modifiedFrames) {
			mF.mapTiles(this.architecture.getColumn(mF.getColumnNum()));
			trojan.addTiles(mF.getAffectedTiles());
		}
		return;
	}

	public boolean performDetection(File goldenBitFile, File targetBitFile) {
		this.trojan = new Trojan();
		String[] args = { "-i", goldenBitFile.getAbsolutePath(), "-c",
				targetBitFile.getAbsolutePath() };
		diff = new BitstreamDiff();
		modifiedFrames = diff.findDifferences(args);
		this.goldenFrames = this.diff.getGoldenFrames();
		this.targetFrames = this.diff.getTargetFrames();
		XilinxConfigurationSpecification partType = diff.getDeviceType();
		this.readDevice = selectedDevice(partType);
		this.architecture = new Architecture(this.readDevice, partType);
		matchFramesToTiles();
		if(this.modifiedFrames.size() == 0){
			return false;
		}
		else{
			return true;
		}
	}

	private Device selectedDevice(XilinxConfigurationSpecification partType) {
		Device device = new Device();
		String partName = partType.getDeviceName().toLowerCase();
		String f = dbPath + "\\" + getDBFileName(partName);
		device.readDeviceFromCompactFile(f);
		return device;
	}
}
