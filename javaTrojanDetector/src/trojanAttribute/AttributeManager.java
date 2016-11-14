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

	public List<TrojanAttribute> getAbstractionAttributes() {
		List<TrojanAttribute> ret = new ArrayList<>();
		ret.add(attributesByName.get("System"));
		ret.add(attributesByName.get("RTL"));
		return ret;
	}

	public List<TrojanAttribute> getActivationAttributes() {
		List<TrojanAttribute> ret = new ArrayList<>();
		ret.add(attributesByName.get("Always On"));
		return ret;
	}

	public List<TrojanAttribute> getEffectAttributes() {
		List<TrojanAttribute> ret = new ArrayList<>();
		boolean clb = this.trojan.doesTrojanModifyCLB();
		boolean clk = this.trojan.doesTrojanModifyClockGrid();
		boolean dsp = this.trojan.doesTrojanModifyDSP();
		boolean iob = this.trojan.doesTrojanModifyIOB();
		boolean mem = this.trojan.doesTrojanModifyMemory();
		boolean pwr = this.trojan.doesTrojanModifyPower();
		boolean rut = this.trojan.doesTrojanModifyRouting();
		if((clb || rut) && !clk && ! dsp && !iob && !mem && !pwr ){
			ret.add(attributesByName.get("Change in Functionailty"));
		}
		else if(iob){
			ret.add(attributesByName.get("Information Leakage"));
		}
		else if(clb && clk && ! dsp && !iob && !mem && !pwr && rut ){
			ret.add(attributesByName.get("Reduced Reliability"));
		}
		else{
			ret.add(attributesByName.get("Denial of Service"));
		}
		return ret;
	}

	public List<TrojanAttribute> getFunctionalityAttributes() {
		List<TrojanAttribute> ret = new ArrayList<>();
		boolean clk = this.trojan.doesTrojanModifyClockGrid();
		boolean pwr = this.trojan.doesTrojanModifyPower();
		if(pwr && clk){
			ret.add(attributesByName.get("Parametric"));
		}
		else{
			ret.add(attributesByName.get("Functional"));
		}
		return ret;
	}

	public List<TrojanAttribute> getInsertionAttributes() {
		List<TrojanAttribute> ret = new ArrayList<>();

		// Fabrication
		ret.add(attributesByName.get("Fabrication"));
		ret.add(attributesByName.get("Testing"));
		ret.add(attributesByName.get("Assembly"));
		return ret;
	}

	public List<TrojanAttribute> getLocationAttributes() {
		List<TrojanAttribute> ret = new ArrayList<>();
		if (this.trojan.doesTrojanModifyCLB()) {
			ret.add(attributesByName.get("Processor"));
		}
		if (this.trojan.doesTrojanModifyIOB()) {
			ret.add(attributesByName.get("I/O"));
		}
		if (this.trojan.doesTrojanModifyMemory()) {
			ret.add(attributesByName.get("Memory"));
		}
		if (this.trojan.doesTrojanModifyPower()) {
			ret.add(attributesByName.get("Power Supply"));
		}
		if (this.trojan.doesTrojanModifyClockGrid()) {
			ret.add(attributesByName.get("Clock Grid"));
		}
		return ret;
	}

	public List<TrojanAttribute> getLogicTypeAttributes() {
		List<TrojanAttribute> ret = new ArrayList<>();
		if(this.trojan.doesTrojanModifyClockGrid() && this.trojan.doesTrojanModifyMemory()){
			ret.add(attributesByName.get("Sequential"));
		}
		else{
			ret.add(attributesByName.get("Combinational"));
		}
		return ret;
	}

	public List<TrojanAttribute> getPhysicalLayoutAttributes() {
		List<TrojanAttribute> ret = new ArrayList<>();

		if (this.scatterScoreManager.isSmall()) {
			ret.add(attributesByName.get("Small"));
		} else {
			ret.add(attributesByName.get("Large"));
		}

		if (this.scatterScoreManager.isAugmented()) {
			ret.add(attributesByName.get("Augmented"));
		} else {
			ret.add(attributesByName.get("Clustered"));
		}

		if (this.scatterScoreManager.isDistributed()) {
			ret.add(attributesByName.get("Distributed"));
		} else {
			ret.add(attributesByName.get("Clustered"));
		}
		return ret;
	}

	public List<TrojanAttribute> getTrojanAttributes() {
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
