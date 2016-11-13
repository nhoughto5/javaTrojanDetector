package trojanAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utilityClasses.ScatterScoreUtil;
import TrojanDetector.Trojan;

public class AttributeManager {

	HashMap<Integer, TrojanAttribute> attributesById;
	HashMap<String, TrojanAttribute> attributesByName;
	List<TrojanAttribute> attributesList;
	Trojan trojan;
	ScatterScoreUtil scatterScoreManager;
	
	public AttributeManager(Trojan trojan) {
		this.trojan = trojan;
		AttributeInitializer init = new AttributeInitializer();
		this.scatterScoreManager = new ScatterScoreUtil(this.trojan);
		this.attributesById = init.getAttributeMapByID();
		this.attributesByName = init.getAttributeMapByName();
		this.attributesList = init.getAttributeList();
	}

	public List<TrojanAttribute> getAbstractionAttributes(){
		List<TrojanAttribute> ret = new ArrayList<>();
		ret.add(attributesByName.get("System"));
		ret.add(attributesByName.get("RTL"));
		return ret;
	}
	public List<TrojanAttribute> getActivationAttributes(){
		List<TrojanAttribute> ret = new ArrayList<>();
		
		return ret;
	}
	
	public List<TrojanAttribute> getEffectAttributes(){
		List<TrojanAttribute> ret = new ArrayList<>();
		
		return ret;
	}
	
	public List<TrojanAttribute> getFunctionalityAttributes(){
		List<TrojanAttribute> ret = new ArrayList<>();
		
		return ret;
	}
	
	public List<TrojanAttribute> getInsertionAttributes(){
		List<TrojanAttribute> ret = new ArrayList<>();
		
		//Fabrication
		ret.add(attributesByName.get("Fabrication"));
		ret.add(attributesByName.get("Testing"));
		ret.add(attributesByName.get("Assembly"));
		return ret;
	}
	
	public List<TrojanAttribute> getLocationAttributes(){
		List<TrojanAttribute> ret = new ArrayList<>();
		if(this.trojan.doesTrojanModifyCLB()){
			ret.add(attributesByName.get("Processor"));
		}
		if(this.trojan.doesTrojanModifyIOB()){
			ret.add(attributesByName.get("I/O"));
		}
		if(this.trojan.doesTrojanModifyMemory()){
			ret.add(attributesByName.get("Memory"));
		}
		if(this.trojan.doesTrojanModifyPower()){
			ret.add(attributesByName.get("Power Supply"));
		}
		if(this.trojan.doesTrojanModifyClockGrid()){
			ret.add(attributesByName.get("Clock Grid"));
		}
		return ret;
	}
	
	public List<TrojanAttribute> getLogicTypeAttributes(){
		List<TrojanAttribute> ret = new ArrayList<>();
		
		return ret;
	}
	
	public List<TrojanAttribute> getPhysicalLayoutAttributes(){
		List<TrojanAttribute> ret = new ArrayList<>();
		
		return ret;
	}
	
	public List<TrojanAttribute> getTrojanAttributes(){
		List<TrojanAttribute> ret = new ArrayList<>();
		ret.addAll(getInsertionAttributes());
		ret.addAll(getAbstractionAttributes());
		ret.addAll(getEffectAttributes());
		ret.addAll(getLogicTypeAttributes());
		ret.addAll(getFunctionalityAttributes());
		ret.addAll(getActivationAttributes());
		ret.addAll(getPhysicalLayoutAttributes());
		ret.addAll(getLocationAttributes());
		return ret;
	}
}
