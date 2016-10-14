package trojanAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import utilityClasses.Error;

public class RelationMatrix {
	private HashMap<Integer, MatrixCell> cellMapByCellId;
	private List<MatrixCell> cells;

	public RelationMatrix() {
		RelationMatrixInit rInit = new RelationMatrixInit();
		this.cells = rInit.getCells();
		this.addCellNumbersAndMakeHashMap();
	}

	private List<TrojanAttribute> abstractionOnly(
			List<TrojanAttribute> userChosen) {
		List<Integer> abstraction = attrToInt(userChosen);
		List<Integer> properties = testRowTrue(abstraction, "R23");
		List<Integer> locations = testRowTrue(properties, "R34");
		if ((abstraction.size() == 0) || (properties.size() == 0)
				|| (locations.size() == 0)) {
			selectionNotPossible();
			return new ArrayList<TrojanAttribute>();
		} else {
			List<Connection> Connections = new ArrayList<Connection>();
			List<MatrixCell> tempCol = new ArrayList<MatrixCell>();
			HashSet<Integer> nodeSet = new HashSet<Integer>();
			boolean tempDirect = false;
			int maxAbstraction = Collections.max(abstraction);

			// Adds nodes in Abstraction and Insertion Category
			for (int i = maxAbstraction; i > 0; --i) {
				tempCol = scanColumnTrue(i, null);
				nodeSet.add(i);
				for (MatrixCell X : tempCol) {
					tempDirect = directConnectionBackwards(X, i);
					Connections
							.add(new Connection(X.getRowId(), i, tempDirect));
				}
			}
			List<Integer> Nodes = new ArrayList<>();
			Nodes.addAll(nodeSet);
			Nodes.addAll(locations);
			Nodes.addAll(properties);
			Nodes = nodeFilter(Nodes, Collections.max(abstraction), Connections);
			Connections = connectionFilter(Nodes, Connections);
			Collections.sort(Nodes);
			return getAttributesFromIntegers(Nodes);
		}
	}

	private void addCellNumbersAndMakeHashMap() {
		this.cellMapByCellId = new HashMap<>();
		for (int i = 0; i < this.cells.size(); ++i) {
			this.cells.get(i).setCellId(i);
			this.cellMapByCellId.put(i, this.cells.get(i));
		}
	}

	public void analyzeMatrix(List<TrojanAttribute> attributes) {
		HashSet<String> categorySet = getCategorySet(attributes);
		HashSet<TrojanAttribute> ret = new HashSet<>();
		if (!categorySet.contains("Insertion")
				&& !categorySet.contains("Abstraction")
				&& categorySet.contains("Properties")
				&& !categorySet.contains("Location")) {
			ret.addAll(propertiesOnly(attributes));
		}
		// #2 Used for IAPL: 0100
		else if (!categorySet.contains("Insertion")
				&& categorySet.contains("Abstraction")
				&& !categorySet.contains("Properties")
				&& !categorySet.contains("Location")) {
			ret.addAll(abstractionOnly(attributes));
		}
		// #3 Used for IAPL: 0001
		else if (!categorySet.contains("Insertion")
				&& !categorySet.contains("Abstraction")
				&& !categorySet.contains("Properties")
				&& categorySet.contains("Location")) {
			ret.addAll(locationOnly(attributes));
		}
		// #4 Used for IAPL: 1000
		else if (categorySet.contains("Insertion")
				&& !categorySet.contains("Abstraction")
				&& !categorySet.contains("Properties")
				&& !categorySet.contains("Location")) {
			ret.addAll(insertionOnly(attributes));
		}
		// #5 Used for IAPL: 0101 1100 1101 => ( B . C'. D ) + ( A . B . C')
		else if ((categorySet.contains("Abstraction")
				&& !categorySet.contains("Properties") && categorySet
					.contains("Location"))
				|| (categorySet.contains("Insertion")
						&& categorySet.contains("Abstraction") && !categorySet
							.contains("Properties"))) {
			ret.addAll(forwardPropagation(attributes));
		}
		// # 7 Used for IAPL: 0011 1010 1011 => ( B'. C . D ) + ( A . B'. C )
		else if ((!categorySet.contains("Abstraction")
				&& categorySet.contains("Properties") && categorySet
					.contains("Location"))
				|| (categorySet.contains("Insertion")
						&& !categorySet.contains("Abstraction") && categorySet
							.contains("Properties"))) {
			ret.addAll(backPropagationNoAbstraction(attributes));
		}
		// #8 Used for IAPL: 0110 0111 1110 1111 => ( B . C )
		else if (categorySet.contains("Abstraction")
				&& categorySet.contains("Properties")) {
			ret.addAll(backPropagationNoAbstraction(attributes));
		}
		// #9 Used for IAPL: 1001 => ( A . B'. C'. D )
		else if (categorySet.contains("Insertion")
				&& !categorySet.contains("Abstraction")
				&& !categorySet.contains("Properties")
				&& categorySet.contains("Location")) {
			ret.addAll(splitPropagation(attributes));
		}
		// #X Used for IAPL: 0000
		else {
			selectionNotPossible();
		}
	}

	// Convert a list of attributes to a list of ints (Attribute ID)
	private List<Integer> attrToInt(List<TrojanAttribute> attributes) {
		List<Integer> Ints = new ArrayList<Integer>();
		for (TrojanAttribute A : attributes) {
			Ints.add(A.getId());
		}
		return Ints;
	}

	private List<TrojanAttribute> backPropagationNoAbstraction(
			List<TrojanAttribute> userChosen) {
		List<Integer> properties = new ArrayList<Integer>();
		List<Integer> locations = new ArrayList<Integer>();
		List<Integer> insert = new ArrayList<Integer>();
		List<Integer> abstraction = new ArrayList<Integer>();

		for (TrojanAttribute A : userChosen) {
			if (A.getCategory() == "Properties") {
				properties.add(A.getId());
			} else if (A.getCategory() == "Location") {
				locations.add(A.getId());
			} else if (A.getCategory() == "Abstraction") {
				abstraction.add(A.getId());
			} else {
				insert.add(A.getId());
			}
		}
		List<Integer> scannedAbstraction = testColumnTrue(properties, "R23");
		if (abstraction.size() == 0) {
			abstraction = scannedAbstraction;
		}
		if (!scannedAttributesCheck(scannedAbstraction, abstraction)) {
			selectionNotPossible();
			return new ArrayList<TrojanAttribute>();
		} else {
			List<Integer> scannedLocation = testRowTrue(properties, "R34");
			if (locations.size() == 0) {
				locations = scannedLocation;
			}
			if (!scannedAttributesCheck(scannedLocation, locations)) {
				selectionNotPossible();
				return new ArrayList<TrojanAttribute>();
			} else {
				// add Nodes
				HashSet<Integer> insertSet = new HashSet<Integer>();
				HashSet<Integer> locationSet = new HashSet<Integer>(locations);
				List<Connection> Connections = new ArrayList<Connection>();
				List<MatrixCell> testCol = new ArrayList<MatrixCell>();

				// Find Nodes
				for (int X : abstraction) {
					testCol = scanColumnTrue(X, null);
					for (MatrixCell M : testCol) {
						if (M.getRowId() <= 5) {
							insertSet.add(M.getRowId());
						}
					}
				}
				testCol.clear();
				for (int X : insert) {
					if (!insertSet.contains(X)) {
						selectionNotPossible();
						return new ArrayList<TrojanAttribute>();
					} else {
						insertSet.add(X);
					}
				}
				int maxAbs = Collections.max(abstraction);

				for (int i = maxAbs; i >= 6; --i) {
					if (!abstraction.contains(i)) {
						abstraction.add(i);
					}
				}
				for (int i = 5; i >= 1; --i) {
					insertSet.add(i);
				}
				List<Integer> scannedProperties = testRowTrue(abstraction,
						"R23");
				scannedLocation = testRowTrue(properties, "R34");
				if (!scannedAttributesCheck(scannedProperties, properties)) {
					selectionNotPossible();
					return new ArrayList<TrojanAttribute>();
				}
				if (!scannedAttributesCheck(scannedLocation, locations)) {
					selectionNotPossible();
					return new ArrayList<TrojanAttribute>();
				}
				// Make Connections
				List<MatrixCell> testRow = new ArrayList<MatrixCell>();
				for (int X : insertSet) {
					testRow = scanRowTrue(X, null);
					for (MatrixCell M : testRow) {
						if (insertSet.contains(M.getColumnId())
								|| abstraction.contains(M.getColumnId())) {
							Connections.add(new Connection(X, M.getColumnId(),
									directConnectionForwards(M, X)));
						}
					}
				}
				for (int X : abstraction) {
					testRow = scanRowTrue(X, "R2");
					for (MatrixCell M : testRow) {
						if (abstraction.contains(M.getColumnId())) {
							Connections.add(new Connection(X, M.getColumnId(),
									directConnectionForwards(M, X)));
						}
					}
				}
				if (Connections.size() == 0) {
					selectionNotPossible();
					return new ArrayList<TrojanAttribute>();
				}
				List<Integer> Nodes = new ArrayList<>();
				Nodes.addAll(insertSet);
				Nodes.addAll(abstraction);

				Nodes = nodeFilter(Nodes, Collections.max(abstraction),
						Connections);
				Nodes.addAll(locations);
				Nodes.addAll(properties);

				Connections = connectionFilter(Nodes, Connections);
				Collections.sort(Nodes);
				return getAttributesFromIntegers(Nodes);
			}
		}
	}

	private List<Integer> bellmanBackWards(List<Integer> nodes,
			List<Connection> Connections) {
		Integer source = Collections.min(nodes);
		Collections.sort(nodes);
		Collections.reverse(nodes);
		List<Integer> unreachables = new ArrayList<Integer>();
		HashMap<Integer, Integer> distance = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> predecessor = new HashMap<Integer, Integer>();
		for (Integer X : nodes) {
			if (X == source) {
				// distance[X] = 0;
				distance.put(X, 0);
			} else {
				// distance[X] = 999;
				distance.put(X, 999);
			}
			// predecessor[X] = 999;
			predecessor.put(X, 999);
		}

		for (@SuppressWarnings("unused")
		Integer X : nodes) {
			for (Connection C : Connections) {
				if (nodes.contains(C.getSource())
						&& nodes.contains(C.getTarget())) {
					if (distance.get(C.getTarget()) != 999) {
						if (distance.get(C.getTarget()) + 1 < distance.get(C
								.getSource())) {
							distance.put(C.getSource(),
									distance.get(C.getTarget()) + 1);
							predecessor.put(C.getSource(), C.getTarget());
						}
					}
				}
			}
		}
		for (Integer X : nodes) {
			if (distance.get(X) == 999) {
				unreachables.add(X);
			}
		}
		return unreachables;
	}

	private List<Integer> bellmanForward(List<Integer> nodes,
			List<Connection> Connections) {
		Integer source = Collections.min(nodes);
		Collections.sort(nodes);
		List<Integer> unreachables = new ArrayList<Integer>();
		HashMap<Integer, Integer> distance = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> predecessor = new HashMap<Integer, Integer>();
		for (Integer X : nodes) {
			if (X == source) {
				// distance[X] = 0;
				distance.put(X, 0);
			} else {
				distance.put(X, 999);
				// distance[X] = 999;
			}
			// predecessor[X] = 999;
			predecessor.put(X, 999);
		}

		for (@SuppressWarnings("unused")
		Integer X : nodes) {
			for (Connection C : Connections) {
				if (distance.get(C.getSource()) != 999) {
					if (nodes.contains(C.getSource())
							&& nodes.contains(C.getTarget())) {
						if (distance.get(C.getSource()) + 1 < distance.get(C
								.getTarget())) {
							distance.put(C.getTarget(),
									distance.get(C.getSource()) + 1);
							predecessor.put(C.getTarget(), C.getSource());
						}
					}

				}
			}
		}
		for (Integer X : nodes) {
			if (distance.get(X) == 999) {
				unreachables.add(X);
			}
		}
		return unreachables;
	}

	private List<Connection> connectionFilter(List<Integer> nodes,
			List<Connection> Connections) {
		List<Connection> filteredConnections = new ArrayList<Connection>();
		for (Connection C : Connections) {
			if (nodes.contains(C.getSource()) && nodes.contains(C.getTarget())) {
				filteredConnections.add(C);
			}
		}
		return filteredConnections;
	}

	// Determines if a getRowId() is a direct connection
	// to the current MatrixCell when doing backwards propagation
	private boolean directConnectionBackwards(MatrixCell X, int i) {
		if (Math.abs(i - X.getRowId()) == 1)
			return false;
		else
			return true;
	}

	private boolean directConnectionForwards(int X, int i) {
		if (Math.abs(i - X) == 1)
			return false;
		else
			return true;
	}

	// Determines if a colId is a direct connection
	// to the current MatrixCell when doing backwards propagation
	private boolean directConnectionForwards(MatrixCell X, int i) {
		if (Math.abs(i - X.getColumnId()) == 1)
			return false;
		else
			return true;
	}

	private List<TrojanAttribute> forwardPropagation(
			List<TrojanAttribute> userChosen) {
		List<Integer> locations = new ArrayList<Integer>();
		List<Integer> insert = new ArrayList<Integer>();
		List<Integer> abstraction = new ArrayList<Integer>();

		for (TrojanAttribute A : userChosen) {
			if (A.getCategory() == "Location") {
				locations.add(A.getId());
			} else if (A.getCategory() == "Abstraction") {
				abstraction.add(A.getId());
			} else {
				insert.add(A.getId());
			}
		}
		List<Integer> properties = testRowTrue(abstraction, "R23");
		List<Integer> scannedLocations = testRowTrue(properties, "R34");
		if (locations.size() == 0) {
			locations = scannedLocations;
		}
		if (!scannedAttributesCheck(scannedLocations, locations)) {
			selectionNotPossible();
			return new ArrayList<TrojanAttribute>();
		} else {
			HashSet<Integer> insertSet = new HashSet<Integer>();
			List<Connection> Connections = new ArrayList<Connection>();
			List<MatrixCell> testCol = new ArrayList<MatrixCell>();

			// Find Nodes
			for (int X : abstraction) {
				testCol = scanColumnTrue(X, null);
				for (MatrixCell M : testCol) {
					if (M.getRowId() <= 5) {
						insertSet.add(M.getRowId());
					}
				}
			}
			testCol.clear();
			for (int X : insert) {
				if (!insertSet.contains(X)) {
					selectionNotPossible();
					return new ArrayList<TrojanAttribute>();
				} else {
					insertSet.add(X);
				}
			}
			// Make Connections
			List<MatrixCell> testRow = new ArrayList<MatrixCell>();
			for (int X : insertSet) {
				testRow = scanRowTrue(X, null);
				for (MatrixCell M : testRow) {
					if (insertSet.contains(M.getColumnId())
							|| abstraction.contains(M.getColumnId())) {
						Connections.add(new Connection(X, M.getColumnId(),
								directConnectionForwards(M, X)));
					}
				}
			}
			for (int X : abstraction) {
				testRow = scanRowTrue(X, "R2");
				for (MatrixCell M : testRow) {
					if (abstraction.contains(M.getColumnId())) {
						Connections.add(new Connection(X, M.getColumnId(),
								directConnectionForwards(M, X)));
					}
				}
			}
			if (Connections.size() == 0) {
				selectionNotPossible();
				return new ArrayList<TrojanAttribute>();
			}
			List<Integer> Nodes = new ArrayList<>();
			Nodes.addAll(insertSet);
			Nodes.addAll(locations);
			Nodes.addAll(abstraction);
			Nodes.addAll(properties);
			Nodes = nodeFilter(Nodes, Collections.max(abstraction), Connections);
			Connections = connectionFilter(Nodes, Connections);
			Collections.sort(Nodes);
			return getAttributesFromIntegers(Nodes);
		}
	}

	private List<TrojanAttribute> getAttributesFromIntegers(List<Integer> ints) {
		List<TrojanAttribute> ret = new ArrayList<>();
		AttributeInitializer init = new AttributeInitializer();
		for (Integer i : ints) {
			ret.add(init.getAttributeById(i));
		}
		return ret;
	}

	private HashSet<String> getCategorySet(List<TrojanAttribute> attributes) {
		HashSet<String> ret = new HashSet<String>();
		for (TrojanAttribute t : attributes) {
			ret.add(t.getCategory());
		}
		return ret;
	}

	public HashMap<Integer, MatrixCell> getCellMapByCellId() {
		return this.cellMapByCellId;
	}

	// Return all of the matrix cells in a particulat column that are in a
	// particular submatrix
	private List<MatrixCell> getColumn(int rowNum, String subM) {
		List<MatrixCell> ret = new ArrayList<>();
		// Scan row in specific subMatrix
		if (subM != null) {
			for (MatrixCell m : this.cells) {
				if (m.getColumnId() == rowNum && m.getSubMatrix() == subM) {
					ret.add(m);
				}
			}
		}
		// Scan entire row
		else {
			for (MatrixCell m : this.cells) {
				if (m.getColumnId() == rowNum) {
					ret.add(m);
				}
			}
		}
		return ret;

	}

	// Scan the row and return all column ID's
	private List<MatrixCell> getRow(int rowNum, String subM) {
		List<MatrixCell> ret = new ArrayList<>();
		// Scan row in specific subMatrix
		if (subM != null) {
			for (MatrixCell m : this.cells) {
				if (m.getRowId() == rowNum && m.getSubMatrix() == subM) {
					ret.add(m);
				}
			}
		}
		// Scan entire row
		else {
			for (MatrixCell m : this.cells) {
				if (m.getRowId() == rowNum) {
					ret.add(m);
				}
			}
		}
		return ret;
	}

	private List<TrojanAttribute> insertionOnly(List<TrojanAttribute> userChosen) {
		List<Integer> insertion = attrToInt(userChosen);
		int currentAttr = Collections.min(insertion);
		List<MatrixCell> tempRow = new ArrayList<MatrixCell>();
		HashSet<Integer> nodeSet = new HashSet<Integer>();
		HashSet<Integer> absSet = new HashSet<Integer>();
		List<Connection> Connections = new ArrayList<Connection>();

		// Scan through insertion attributes
		for (int i = currentAttr; i < 6; ++i) {
			tempRow = scanRowTrue(i, null);
			nodeSet.add(i);
			for (MatrixCell M : tempRow) {

				if (M.getSubMatrix() == "R12") {
					absSet.add(M.getColumnId());
				}
				Connections.add(new Connection(i, M.getColumnId(),
						directConnectionForwards(M, i)));
			}
		}
		int highestAbs = Collections.max(absSet);

		// Scan through abstraction attributes
		for (int i = 6; i <= highestAbs; ++i) {
			nodeSet.add(i);
			absSet.add(i);
			tempRow = scanRowTrue(i, "R2");
			for (MatrixCell M : tempRow) {
				Connections.add(new Connection(i, M.getColumnId(),
						directConnectionForwards(M, i)));
			}
		}
		List<Integer> properties = testRowTrue(new ArrayList<>(absSet), "R23");
		List<Integer> locations = testRowTrue(properties, "R34");
		List<Integer> Nodes = new ArrayList<>();
		Nodes.addAll(nodeSet);
		Nodes.addAll(locations);
		Nodes.addAll(properties);

		Nodes = nodeFilter(Nodes, highestAbs, Connections);
		Connections = connectionFilter(Nodes, Connections);
		Collections.sort(Nodes);
		return getAttributesFromIntegers(Nodes);
	}

	private List<TrojanAttribute> locationOnly(List<TrojanAttribute> userChosen) {
		List<Integer> locations = attrToInt(userChosen);
		List<Integer> properties = testColumnTrue(locations, "R34");
		List<Integer> abstraction = testColumnTrue(properties, "R23");
		if ((locations.size() == 0) || (properties.size() == 0)
				|| (abstraction.size() == 0)) {
			selectionNotPossible();
			return new ArrayList<TrojanAttribute>();
		} else {
			List<Connection> Connections = new ArrayList<Connection>();
			List<MatrixCell> tempCol = new ArrayList<MatrixCell>();
			HashSet<Integer> nodeSet = new HashSet<Integer>();
			boolean tempDirect = false;
			int maxAbstraction = Collections.max(abstraction);

			// Adds nodes in Abstraction and Insertion Category
			for (int i = maxAbstraction; i > 0; --i) {
				tempCol = scanColumnTrue(i, null);
				nodeSet.add(i);
				for (MatrixCell X : tempCol) {
					tempDirect = directConnectionBackwards(X, i);
					Connections
							.add(new Connection(X.getRowId(), i, tempDirect));
				}
			}
			List<Integer> Nodes = new ArrayList<>();
			Nodes.addAll(nodeSet);
			Nodes.addAll(locations);
			Nodes.addAll(properties);
			Nodes = nodeFilter(Nodes, Collections.max(abstraction), Connections);
			Connections = connectionFilter(Nodes, Connections);
			Collections.sort(Nodes);
			return getAttributesFromIntegers(Nodes);
		}

	}

	private List<Integer> nodeFilter(List<Integer> nodes, int end,
			List<Connection> Connections) {
		List<Integer> tempNodes = new ArrayList<Integer>();
		for (Integer X : nodes) {
			if (X < 12) {
				tempNodes.add(X);
			}
		}
		List<Integer> removedNodes = bellmanForward(tempNodes, Connections);
		removedNodes.addAll(bellmanBackWards(tempNodes, Connections));
		List<Integer> filtereNodes = new ArrayList<Integer>();
		for (Integer X : nodes) {
			if (!removedNodes.contains(X)) {
				filtereNodes.add(X);
			}
		}
		return filtereNodes;
	}

	private List<TrojanAttribute> propertiesOnly(
			List<TrojanAttribute> PropertiesList) {
		List<Integer> propertyIDs = attrToInt(PropertiesList);
		List<Integer> LocationIDs = testRowTrue(propertyIDs, "R34");
		List<Integer> abstractionResults = testColumnTrue(propertyIDs, "R23");
		if (abstractionResults.size() == 0) {
			selectionNotPossible();
			return new ArrayList<TrojanAttribute>();
		} else {
			List<Connection> Connections = new ArrayList<Connection>();
			List<MatrixCell> tempCol = new ArrayList<MatrixCell>();
			HashSet<Integer> nodeSet = new HashSet<Integer>();
			boolean tempDirect = false;
			int maxAbstraction = Collections.max(abstractionResults);

			// Adds nodes in Abstraction and Insertion Category
			for (int i = maxAbstraction; i > 0; --i) {
				tempCol = scanColumnTrue(i, null);
				nodeSet.add(i);
				for (MatrixCell X : tempCol) {
					tempDirect = directConnectionBackwards(X, i);
					Connections
							.add(new Connection(X.getRowId(), i, tempDirect));
				}
			}
			List<Integer> Nodes = new ArrayList<>();
			Nodes.addAll(nodeSet);
			Nodes.addAll(LocationIDs);
			Nodes.addAll(propertyIDs);
			Nodes = nodeFilter(Nodes, Collections.max(abstractionResults),
					Connections);
			Connections = connectionFilter(Nodes, Connections);
			Collections.sort(Nodes);
			return getAttributesFromIntegers(Nodes);
		}

	}

	// Return all of the matrix cells in a particulat column that are in a
	// particular submatrix
	private List<MatrixCell> scanColumnTrue(int rowNum, String subM) {
		List<MatrixCell> ret = new ArrayList<>();
		if (subM != null) {
			for (MatrixCell m : this.cells) {
				if (m.getColumnId() == rowNum && m.getSubMatrix() == subM
						&& m.getValue()) {
					ret.add(m);
				}
			}
		} else {
			for (MatrixCell m : this.cells) {
				if (m.getColumnId() == rowNum && m.getValue()) {
					ret.add(m);
				}
			}
		}
		return ret;
	}

	// Checks to see if each of the ints in 'properties' is contained in
	// the list 'scannedProperties', if not. Returns false;
	private boolean scannedAttributesCheck(List<Integer> scannedProperties,
			List<Integer> properties) {
		for (int X : properties) {
			if (!scannedProperties.contains(X))
				return false;
		}
		return true;
	}

	// Scan the row and return all column ID's with value 1
	private List<MatrixCell> scanRowTrue(int rowNum, String subM) {
		List<MatrixCell> ret = new ArrayList<>();
		// Scan row in specific subMatrix
		if (subM != null) {
			for (MatrixCell m : this.cells) {
				if (m.getRowId() == rowNum && m.getSubMatrix() == subM
						&& m.getValue()) {
					ret.add(m);
				}
			}
		}
		// Scan entire row
		else {
			for (MatrixCell m : this.cells) {
				if (m.getRowId() == rowNum && m.getValue()) {
					ret.add(m);
				}
			}
		}
		return ret;
	}

	private void selectionNotPossible() {
		Error.printError(
				"Analysis of the Relation Matrix is not possible with this combination",
				new Exception().getStackTrace()[0]);
	}

	public void setCellMapByCellId(
			final HashMap<Integer, MatrixCell> cellMapByCellId) {
		this.cellMapByCellId = cellMapByCellId;
	}

	public void setCells(final List<MatrixCell> cells) {
		this.cells = cells;
	}

	private List<TrojanAttribute> splitPropagation(
			List<TrojanAttribute> userChosen) {
		List<Integer> locations = new ArrayList<Integer>();
		List<Integer> insert = new ArrayList<Integer>();

		for (TrojanAttribute A : userChosen) {
			if (A.getCategory() == "Location") {
				locations.add(A.getId());
			} else {
				insert.add(A.getId());
			}
		}
		List<Integer> properties = testColumnTrue(locations, "R34");
		List<Integer> abstraction = testColumnTrue(properties, "R23");
		if (abstraction.size() == 0 || properties.size() == 0) {
			selectionNotPossible();
			return new ArrayList<TrojanAttribute>();
		}
		HashSet<Integer> insertSet = new HashSet<Integer>();
		List<Connection> Connections = new ArrayList<Connection>();
		List<MatrixCell> testCol = new ArrayList<MatrixCell>();

		// Find Nodes
		for (int X : abstraction) {
			testCol = scanColumnTrue(X, null);
			for (MatrixCell M : testCol) {
				if (M.getRowId() <= 5) {
					insertSet.add(M.getRowId());
				}
			}
		}
		for (int X : insert) {
			if (!insertSet.contains(X)) {
				selectionNotPossible();
				return new ArrayList<TrojanAttribute>();
			} else {
				insertSet.add(X);
			}
		}
		testCol.clear();
		// Make Connections
		List<MatrixCell> testRow = new ArrayList<MatrixCell>();
		for (int X : insertSet) {
			testRow = scanRowTrue(X, null);
			for (MatrixCell M : testRow) {
				if (insertSet.contains(M.getColumnId())
						|| abstraction.contains(M.getColumnId())) {
					Connections.add(new Connection(X, M.getColumnId(),
							directConnectionForwards(M, X)));
				}
			}
		}
		for (int X : abstraction) {
			testRow = scanRowTrue(X, "R2");
			for (MatrixCell M : testRow) {
				if (abstraction.contains(M.getColumnId())) {
					Connections.add(new Connection(X, M.getColumnId(),
							directConnectionForwards(M, X)));
				}
			}
		}
		if (Connections.size() == 0) {
			selectionNotPossible();
			return new ArrayList<TrojanAttribute>();
		}
		List<Integer> Nodes = new ArrayList<>();
		Nodes.addAll(insertSet);
		Nodes.addAll(locations);
		Nodes.addAll(abstraction);
		Nodes.addAll(properties);
		Nodes = nodeFilter(Nodes, Collections.max(abstraction), Connections);
		Connections = connectionFilter(Nodes, Connections);
		Collections.sort(Nodes);
		return getAttributesFromIntegers(Nodes);
	}

	// Receives a list of column numbers and determines which rows
	// The columns have true value in common
	private List<Integer> testColumnTrue(List<Integer> list, String subMatrix) {
		List<Integer> resultsInt = new ArrayList<Integer>();
		List<MatrixCell> colTrue = new ArrayList<MatrixCell>();
		HashSet<Integer> removedSet = new HashSet<Integer>();
		for (Integer X : list) {
			colTrue = getColumn(X, subMatrix);
			for (MatrixCell A : colTrue) {
				if (A.getValue() == false) {
					removedSet.add(A.getRowId());
					if (resultsInt.contains(A.getRowId())) {
						resultsInt.remove(A.getRowId());
					}
				}
				if ((A.getValue() == true)
						&& (!removedSet.contains(A.getRowId()))
						&& (!resultsInt.contains(A.getRowId()))) {
					resultsInt.add(A.getRowId());
				}
			}
		}
		return resultsInt;
	}

	// Receives a list of row numbers and determines which columns
	// The rows have value true in common
	private List<Integer> testRowTrue(List<Integer> list, String subMatrix) {
		List<Integer> resultsInt = new ArrayList<Integer>();
		List<MatrixCell> rowTrue = new ArrayList<MatrixCell>();
		HashSet<Integer> removedSet = new HashSet<Integer>();
		for (Integer X : list) {
			rowTrue = getRow(X, subMatrix);
			for (MatrixCell A : rowTrue) {
				if (A.getValue() == false) {
					removedSet.add(A.getColumnId());
//					if (resultsInt.contains(A.getColumnId())) {
//						resultsInt.remove(A.getColumnId());
//					}
				}
				if ((A.getValue() == true)
						&& (!removedSet.contains(A.getColumnId()))
						&& (!resultsInt.contains(A.getColumnId()))) {
					resultsInt.add(A.getColumnId());
				}
			}
		}
		HashSet<Integer> ret = new HashSet<Integer>();
		for(Integer i : resultsInt){
			if(removedSet.contains(i)){
				//ret.add(i);
			}
			else{
				ret.add(i);
			}
		}
		List<Integer> retList = new ArrayList<Integer>(ret);
		Collections.sort(retList);
		return retList;
	}
}
