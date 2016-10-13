package trojanAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttributeInitializer {
	
	public List<TrojanAttribute> getAttributeList(){
		List<TrojanAttribute> ret = new ArrayList<>();
		
		//Insertion
		ret.add(new TrojanAttribute(1, "Insertion", "Specification", ""));
		ret.add(new TrojanAttribute(2, "Insertion", "Design", ""));
		ret.add(new TrojanAttribute(3, "Insertion", "Fabrication", ""));
		ret.add(new TrojanAttribute(4, "Insertion", "Testing", ""));
		ret.add(new TrojanAttribute(5, "Insertion", "Assembly", ""));
		
		//Abstraction
		ret.add(new TrojanAttribute(6, "Abstraction", "System", ""));
		ret.add(new TrojanAttribute(7, "Abstraction", "RTL", ""));
		ret.add(new TrojanAttribute(8, "Abstraction", "Development Environmnet", ""));
		ret.add(new TrojanAttribute(9, "Abstraction", "Logic", ""));
		ret.add(new TrojanAttribute(10, "Abstraction", "Transistor", ""));
		ret.add(new TrojanAttribute(11, "Abstraction", "Physical", ""));
		
		//Effect
		ret.add(new TrojanAttribute(12, "Effect", "Change in Functionailty", ""));
		ret.add(new TrojanAttribute(13, "Effect", "Information Leakage", ""));
		ret.add(new TrojanAttribute(14, "Effect", "Reduced Reliability", ""));
		ret.add(new TrojanAttribute(15, "Effect", "Denial of Service", ""));
		
		//Logic Type
		ret.add(new TrojanAttribute(16, "Logic Type", "Sequential", ""));
		ret.add(new TrojanAttribute(17, "Logic Type", "Combinational", ""));
		
		//Functionality
		ret.add(new TrojanAttribute(18, "Functionality", "Functional", ""));
		ret.add(new TrojanAttribute(19, "Functionality", "Parametric", ""));
		
		//Activation
		ret.add(new TrojanAttribute(20, "Activation", "Always On", ""));
		ret.add(new TrojanAttribute(21, "Activation", "Internally Triggered", ""));
		ret.add(new TrojanAttribute(22, "Activation", "Externally Triggered", ""));
		
		//Physical Layout
		ret.add(new TrojanAttribute(23, "Physical Layout", "Large", ""));
		ret.add(new TrojanAttribute(24, "Physical Layout", "Small", ""));
		ret.add(new TrojanAttribute(25, "Physical Layout", "Changed Layout", ""));
		ret.add(new TrojanAttribute(26, "Physical Layout", "Augmented", ""));
		ret.add(new TrojanAttribute(27, "Physical Layout", "Clustered", ""));
		ret.add(new TrojanAttribute(28, "Physical Layout", "Distributed", ""));
		
		//Location
		ret.add(new TrojanAttribute(29, "Location", "Processor", ""));
		ret.add(new TrojanAttribute(30, "Location", "Memory", ""));
		ret.add(new TrojanAttribute(31, "Location", "I/O", ""));
		ret.add(new TrojanAttribute(32, "Location", "Power Supply", ""));
		ret.add(new TrojanAttribute(33, "Location", "Clock Grid", ""));
		
		return ret;
	}
	public HashMap<Integer,TrojanAttribute> getAttributeMapByID(){
		List<TrojanAttribute> list = getAttributeList();
		HashMap<Integer,TrojanAttribute> ret = new HashMap<>();
		for(TrojanAttribute t : list){
			ret.put(t.getId(), t);
		}
		return ret;
	}
	
	public HashMap<String,TrojanAttribute> getAttributeMapByName(){
		List<TrojanAttribute> list = getAttributeList();
		HashMap<String,TrojanAttribute> ret = new HashMap<>();
		for(TrojanAttribute t : list){
			ret.put(t.getName(), t);
		}
		return ret;
	}
}
