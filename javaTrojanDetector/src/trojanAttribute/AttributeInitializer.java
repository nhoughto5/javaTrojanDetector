package trojanAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttributeInitializer {

	public List<TrojanAttribute> getAttributeList() {
		List<TrojanAttribute> ret = new ArrayList<>();

		// Insertion
		ret.add(new TrojanAttribute(
				1,
				"Insertion",
				"Specification",
				"Specification is the phase in which structure, behaviour and system features are initially described. The customer and design team often work together to layout the essential details of the hardware to be made. It is possible for attackers to infiltrate this phase and modify design requirements such as size, structure, type, intended function, power, timing or delay. Specification is grouped into the Insertion sub-category."));
		ret.add(new TrojanAttribute(
				2,
				"Insertion",
				"Design",
				"The design team is responsible for decisions concerning the logical, functional, timing and physical constraints. The sheer number of decisions made during the many stages of attack make the design phase a relatively vulnerable insertion point. Attackers have many opportunities to make modifications undetected. Due to the size of the design process designers often use third-party intellectual property blocks, design tools, standard cell libraries and templates in design of new hardware. These third-party mechanisms are frequently used as attack vectors. Design is grouped into the Insertion sub-category."));
		ret.add(new TrojanAttribute(
				3,
				"Insertion",
				"Fabrication",
				"The fabrication and production phase of an IC typically involves hundreds of steps that can be targeted by attackers. CAD tools are used to define the doping, metallization and glass region masks to transfer the design onto a silicon wafer. This process is repeated to embed the masks for the IC layers on the silicon. Each layer is tested for functionality and defective chips are discarded after the wafer is cut into individual ICs. The chips are then bonded to a mounting package and contact leads are attached. The mounting package is encapsulated with a plastic coating for protection and identifying part numbers and other data are added. Fabrication is grouped into the Insertion sub-category."));
		ret.add(new TrojanAttribute(
				4,
				"Insertion",
				"Testing",
				"Trojans can be inserted or concealed during the testing phase. It is a critical step as it is the primary avenue for discovery of fault or attack. Testing is grouped into the Insertion sub-category."));
		ret.add(new TrojanAttribute(
				5,
				"Insertion",
				"Assembly",
				"In the assembly phase, hardware components are interfaced on the chip. Every interface between components is a possible trojan insertion point. At an interface, improper termination or improper shielding against phenomena such as electromagnetic coupling makes the chip susceptible to exploitation by an attacker. Assembly is grouped into the Insertion sub-category."));

		// Abstraction
		ret.add(new TrojanAttribute(
				6,
				"Abstraction",
				"System",
				"A trojan can be introduced in the system level by altering interconnections, hardware modules of communication protocols. System is grouped into the Abstraction sub-category."));
		ret.add(new TrojanAttribute(
				7,
				"Abstraction",
				"RTL",
				"At the register-transfer level (RTL), signals, registers and boolean functions are described in the function modules. Trojan insertion opportunities are very high in this level since an attacker can gain control over the hardware functionality. RTL is grouped into the Abstraction sub-category."));
		ret.add(new TrojanAttribute(
				8,
				"Abstraction",
				"Development Environmnet",
				"The development environment of an IC involves the simulation, verification, validation and synthesis. Software can be inserted into this abstraction level through CAD tools and scripts to hide the effects of hardware trojans. Environment is grouped into the Abstraction sub-category."));
		ret.add(new TrojanAttribute(
				9,
				"Abstraction",
				"Logic",
				"The logic (gate level) is prone to trojan insertion but is considered relatively secure since tampering requires a high level of sophistication. Logic design alteration is possible at the gate level or the netlist design. Logic is grouped into the Abstraction sub-category."));
		ret.add(new TrojanAttribute(
				10,
				"Abstraction",
				"Transistor",
				"The transistor level provides attackers with the opportunity to control circuit parameters such as power and timing, and trojans can be inserted by resizing or deleting existing transistors, or inserting new transistors, to modify the circuit functionality and characteristics. Transistor is grouped into the Abstraction sub-category."));
		ret.add(new TrojanAttribute(
				11,
				"Abstraction",
				"Physical",
				"Modifications at the physical level involve the transistors and/or layout and are typically achieved by changing circuit parameters that affect the reliability and functionality. Physical is grouped into the Abstraction sub-category."));

		// Effect
		ret.add(new TrojanAttribute(
				12,
				"Effect",
				"Change in Functionailty",
				"A change in functionality is caused by trojans that introduce new logic or bypass existing logic to produce unexpected results. This can also be achieved by deleting logic. Change in Functionality is grouped into the Effect sub-category."));
		ret.add(new TrojanAttribute(
				13,
				"Effect",
				"Information Leakage",
				"A trojan can also cause information leakage through a covert or existing channel. These channels may be radio frequency based or via JTAG or RS232 interfaces, and provide backdoor access to assist in compromising the chip. Information such as encryption keys can also be leaked through thermal or optical patterns created by the hardware. Information Leakage is grouped into the Effect sub-category."));
		ret.add(new TrojanAttribute(
				14,
				"Effect",
				"Reduced Reliability",
				"Hardware trojans can also cause reduced reliability by altering interface, functional or circuit characteristics such as path delay and power consumption. Increased power consumption may cause the ambient temperature of the circuit to rise above normal operating levels and/or cause quicker battery depletion. Reduced Reliability is grouped into the Effect sub-category."));
		ret.add(new TrojanAttribute(
				15,
				"Effect",
				"Denial of Service",
				"A denial of service (DOS) trojan modifies device parameters to exhaust on-board resources such as power or memory, or introduces computational delays to degrade performance or create malfunctions. Denial of Service is grouped into the Effect sub-category."));

		// Logic Type
		ret.add(new TrojanAttribute(
				16,
				"Logic Type",
				"Sequential",
				"A sequential trojan is triggered by a sequence of conditions after a given period of operation. Sequential is grouped into the Logic Type sub-category."));
		ret.add(new TrojanAttribute(
				17,
				"Logic Type",
				"Combinational",
				"A combinational trojan uses a particular logic value at one or more circuit locations as the trigger. Combinational is grouped into the Logic Type sub-category."));

		// Functionality
		ret.add(new TrojanAttribute(
				18,
				"Functionality",
				"Functional",
				"A functional trojan introduces a change in the functionality of the device. Functional is grouped into the Functionality sub-category."));
		ret.add(new TrojanAttribute(
				19,
				"Functionality",
				"Parametric",
				"A parametric trojan exploits the parametric effects of the device circuitry such as power consumption, thermal and delay profiles. This is achieved by weakening transistors, modifying the length and/or thickness of wires, or changing physical geometries. Parametric is grouped into the Functionality sub-category."));

		// Activation
		ret.add(new TrojanAttribute(
				20,
				"Activation",
				"Always On",
				"A trojan virus that is constantly activated. Always On is grouped into the Activation sub-category."));
		ret.add(new TrojanAttribute(
				21,
				"Activation",
				"Internally Triggered",
				"An internally triggered trojan waits for an internal condition which can be a sequence of one or more events that occur in the system. This condition is typically an internal logic state or a pattern of input/output signals. Internally Triggered is grouped into the Activation sub-category."));
		ret.add(new TrojanAttribute(
				22,
				"Activation",
				"Externally Triggered",
				"An externally triggered trojan is activated via an external signal received by an antenna or sensor that interacts with the outside world. Externally Triggered is grouped into the Activation sub-category."));

		// Physical Layout
		ret.add(new TrojanAttribute(
				23,
				"Physical Layout",
				"Large",
				"A large trojan that often employs unused circuitry in the device to avoid detection. The size of trojans is determined by the number of deleted, added or compromised components in the chip. Large is grouped into the Physical Layout sub-category."));
		ret.add(new TrojanAttribute(
				24,
				"Physical Layout",
				"Small",
				"Some trojans are very small and thus are virtually undetectable by inspection of the power consumption or heat dissipation. The size of trojans is determined by the number of deleted, added or compromised components in the chip. Small is grouped into the Physical Layout sub-category."));
		ret.add(new TrojanAttribute(
				25,
				"Physical Layout",
				"Changed Layout",
				"Some trojans employ a changed layout structure where an existing layout is modified. Changed Layout is grouped into the Physical Layout sub-category."));
		ret.add(new TrojanAttribute(
				26,
				"Physical Layout",
				"Augmented",
				"The layout of the trojan is additional to the layout of the victim circuit. Augmented is grouped into the Physical Layout sub-category."));
		ret.add(new TrojanAttribute(
				27,
				"Physical Layout",
				"Clustered",
				"A clustered trojan has a topology in which the components are close to each other. Clustered is grouped into the Physical Layout sub-category."));
		ret.add(new TrojanAttribute(
				28,
				"Physical Layout",
				"Distributed",
				"A distributed trojan has a sporadic topology and can be dispersed throughout the chip. Distributed is grouped into the Physical Layout sub-category."));

		// Location
		ret.add(new TrojanAttribute(
				29,
				"Location",
				"Processor",
				"A trojan injected into the processor can be located in the logical units so that it can change the instruction or execution cycles. Processor is grouped into the Location sub-category."));
		ret.add(new TrojanAttribute(
				30,
				"Location",
				"Memory",
				"Trojans in the memory units or interfaces can create incorrect addresses, modify memory contents, or enable/disable read/write instructions. Memory is grouped into the Location sub-category."));
		ret.add(new TrojanAttribute(
				31,
				"Location",
				"I/O",
				"The input/output peripherals have shared interfaces with external devices through communication and data buses such as serial ports. Trojans located here can modify the data or alter the way these devices communicate with the IC components. I/O is grouped into the Location sub-category."));
		ret.add(new TrojanAttribute(
				32,
				"Location",
				"Power Supply",
				"The power supply is a location where trojans can be placed to create effects such as denial of service or reduced reliability. These effects are produced by varying the current and/or voltage supply to the chip to cause malfunctions or abnormal behaviour. Power Supply is grouped into the Location sub-category."));
		ret.add(new TrojanAttribute(
				33,
				"Location",
				"Clock Grid",
				"A trojan in the clock grid can cause variations in the clock frequency, or skip or freeze the clock signals supplied to chip modules. Clock Grid is grouped into the Location sub-category."));

		return ret;
	}

	public HashMap<Integer, TrojanAttribute> getAttributeMapByID() {
		List<TrojanAttribute> list = getAttributeList();
		HashMap<Integer, TrojanAttribute> ret = new HashMap<>();
		for (TrojanAttribute t : list) {
			ret.put(t.getId(), t);
		}
		return ret;
	}

	public TrojanAttribute getAttributeById(int id) {
		HashMap<Integer, TrojanAttribute> map = getAttributeMapByID();
		return map.get(id);
	}

	public HashMap<String, TrojanAttribute> getAttributeMapByName() {
		List<TrojanAttribute> list = getAttributeList();
		HashMap<String, TrojanAttribute> ret = new HashMap<>();
		for (TrojanAttribute t : list) {
			ret.put(t.getName(), t);
		}
		return ret;
	}
}
