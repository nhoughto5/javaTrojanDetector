package trojanAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class RelationMatrix {
	private HashMap<Integer, MatrixCell> cellMapByCellId;
	private List<MatrixCell> cells;

	public RelationMatrix() {
		RelationMatrixInit rInit = new RelationMatrixInit();
		this.cells = rInit.getCells();
		this.addCellNumbersAndMakeHashMap();
	}

	private HashSet<String> getCategorySet(List<TrojanAttribute> attributes){
		HashSet<String> ret = new HashSet<String>();
		for(TrojanAttribute t : attributes){
			ret.add(t.getCategory());
		}
		return ret;
	}
	
    //Scan the row and return all column ID's with value 1
    private List<MatrixCell> scanRowTrue(int rowNum, String subM)
    {
    	List<MatrixCell> ret = new ArrayList<>();
        //Scan row in specific subMatrix
        if (subM != null)
        {
        	for(MatrixCell m : this.cells){
        		if(m.getRowId() == rowNum && m.getSubMatrix() == subM && m.getValue()){
        			ret.add(m);
        		}
        	}
        }
        //Scan entire row
        else
        {
        	for(MatrixCell m : this.cells){
        		if(m.getRowId() == rowNum && m.getValue()){
        			ret.add(m);
        		}
        	}
        }
        return ret;
    }

    //Return all of the matrix cells in a particulat column that are in a particular submatrix
    private List<MatrixCell> scanColumnTrue(int rowNum, String subM)
    {
    	List<MatrixCell> ret = new ArrayList<>();
        if (subM != null)
        {
        	for(MatrixCell m : this.cells){
        		if(m.getColumnId() == rowNum && m.getSubMatrix() == subM && m.getValue()){
        			ret.add(m);
        		}
        	}
        }
        else
        {
        	for(MatrixCell m : this.cells){
        		if(m.getColumnId() == rowNum && m.getValue()){
        			ret.add(m);
        		}
        	}
        }
        return ret;
    }
	
    //Convert a list of attributes to a list of ints (Attribute ID)
    private List<Integer> attrToInt(List<TrojanAttribute> attributes)
    {
        List<Integer> Ints = new ArrayList<Integer>();
        for(TrojanAttribute A : attributes){
            Ints.add(A.getId());
        }
        return Ints;
    }
    //Return all of the matrix cells in a particulat column that are in a particular submatrix
    private List<MatrixCell> getColumn(int rowNum, String subM)
    {
    	List<MatrixCell> ret = new ArrayList<>();
        //Scan row in specific subMatrix
        if (subM != null)
        {
        	for(MatrixCell m : this.cells){
        		if(m.getColumnId() == rowNum && m.getSubMatrix() == subM){
        			ret.add(m);
        		}
        	}
        }
        //Scan entire row
        else
        {
        	for(MatrixCell m : this.cells){
        		if(m.getColumnId() == rowNum){
        			ret.add(m);
        		}
        	}
        }
        return ret;

    }
    //Scan the row and return all column ID's
    private List<MatrixCell> getRow(int rowNum, String subM)
    {
    	List<MatrixCell> ret = new ArrayList<>();
        //Scan row in specific subMatrix
        if (subM != null)
        {
        	for(MatrixCell m : this.cells){
        		if(m.getRowId() == rowNum && m.getSubMatrix() == subM){
        			ret.add(m);
        		}
        	}
        }
        //Scan entire row
        else
        {
        	for(MatrixCell m : this.cells){
        		if(m.getRowId() == rowNum){
        			ret.add(m);
        		}
        	}
        }
        return ret;
    }
    
    //Receives a list of row numbers and determines which columns
    //The rows have value true in common
    private List<Integer> testRowTrue(List<Integer> list, String subMatrix){
        List<Integer> resultsInt = new ArrayList<Integer>();
        List<MatrixCell> rowTrue = new ArrayList<MatrixCell>();
        HashSet<Integer> removedSet = new HashSet<Integer>();
        for (Integer X : list)
        {
            rowTrue = getRow(X, subMatrix);
            for(MatrixCell A : rowTrue)
            {
                if (A.getValue() == false)
                {
                    removedSet.add(A.getColumnId());
                    if (resultsInt.contains(A.getColumnId()))
                    {
                        resultsInt.remove(A.getColumnId());
                    }
                }
                if ((A.getValue() == true) && (!removedSet.contains(A.getColumnId())) && (!resultsInt.contains(A.getColumnId())))
                {
                    resultsInt.add(A.getColumnId());
                }
            }
        }
        return resultsInt;
    }
    //Receives a list of column numbers and determines which rows
    //The columns have true value in common
    private List<Integer> testColumnTrue(List<Integer> list, String subMatrix)
    {
        List<Integer> resultsInt = new ArrayList<Integer>();
        List<MatrixCell> colTrue = new ArrayList<MatrixCell>();
        HashSet<Integer> removedSet = new HashSet<Integer>();
        for(Integer X : list)
        {
            colTrue = getColumn(X, subMatrix);
            for(MatrixCell A : colTrue)
            {
                if (A.getValue() == false)
                {
                    removedSet.add(A.getRowId());
                    if (resultsInt.contains(A.getRowId()))
                    {
                        resultsInt.remove(A.getRowId());
                    }
                }
                if ((A.getValue() == true) && (!removedSet.contains(A.getRowId())) && (!resultsInt.contains(A.getRowId())))
                {
                    resultsInt.add(A.getRowId());
                }
            }
        }
        return resultsInt;
    }
    //Determines if a rowId is a direct connection
    //to the current Matrix_Cell when doing backwards propagation 
    private boolean directConnectionBackwards(MatrixCell X, int i)
    {
        if (Math.abs(i - X.getRowId()) == 1) return false;
        else return true;
    }
    private void abstractionOnly(List<TrojanAttribute> userChosen)
    {
        List<Integer> abstraction = attrToInt(userChosen);
        List<Integer> properties = testRowTrue(abstraction, "R23");
        List<Integer> locations = testRowTrue(properties, "R34");
        if ((abstraction.size() == 0) || (properties.size() == 0) || (locations.size() == 0))
        {
            selectionNotPossible();
            return;
        }
        else
        {
            List<Connection> Connections = new ArrayList<Connection>();
            List<MatrixCell> tempCol = new ArrayList<MatrixCell>();
            HashSet<Integer> nodeSet = new HashSet<Integer>();
            boolean tempDirect = false;
            int maxAbstraction = Collections.max(abstraction);

            //Adds nodes in Abstraction and Insertion Category
            for (int i = maxAbstraction; i > 0; --i)
            {
                tempCol = scanColumnTrue(i, null);
                nodeSet.add(i);
                for(MatrixCell X : tempCol)
                {
                    tempDirect = directConnectionBackwards(X, i);
                    Connections.add(new Connection(X.getRowId(), i, tempDirect));
                }
            }
            List<Integer> Nodes = new ArrayList<>();
            Nodes.addAll(nodeSet);
            Nodes.addAll(locations);
            Nodes.addAll(properties);
            Nodes = nodeFilter(Nodes, Collections.max(abstraction), Connections);
            Connections = connectionFilter(Nodes, Connections);
            Collections.sort(Nodes);
        }
    }
    private void insertionOnly(List<TrojanAttribute> userChosen)
    {
        List<Integer> insertion = attrToInt(userChosen);
        int currentAttr = Collections.min(insertion);
        List<MatrixCell> tempRow = new ArrayList<MatrixCell>();
        HashSet<Integer> nodeSet = new HashSet<Integer>();
        HashSet<Integer> absSet = new HashSet<Integer>();
        List<Connection> Connections = new ArrayList<Connection>();

        //Scan through insertion attributes
        for (int i = currentAttr; i < 6; ++i)
        {
            tempRow = scanRowTrue(i, null);
            nodeSet.add(i);
            for(MatrixCell M : tempRow)
            {
                
                if (M.getSubMatrix() == "R12")
                {
                    absSet.add(M.getColumnId());
                }
                Connections.add(new Connection(i, M.getColumnId(), directConnectionForwards(M, i)));
            }
        }
        int highestAbs = Collections.max(absSet);

        //Scan through abstraction attributes
        for (int i = 6; i <= highestAbs; ++i)
        {
            nodeSet.add(i); absSet.add(i);
            tempRow = scanRowTrue(i, "R2");
            for(MatrixCell M : tempRow)
            {
                Connections.add(new Connection(i, M.getColumnId(), directConnectionForwards(M, i)));
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
    }
    
    private void locationOnly(List<TrojanAttribute> userChosen)
    {
        List<Integer> locations = attrToInt(userChosen);
        List<Integer> properties = testColumnTrue(locations, "R34");
        List<Integer> abstraction = testColumnTrue(properties, "R23");
        if((locations.size() == 0)||(properties.size() == 0)||(abstraction.size() == 0)){
            selectionNotPossible();
            return;
        }
        else
        {
            List<Connection> Connections = new ArrayList<Connection>();
            List<MatrixCell> tempCol = new ArrayList<MatrixCell>();
            HashSet<Integer> nodeSet = new HashSet<Integer>();
            boolean tempDirect = false;
            int maxAbstraction = Collections.max(abstraction);

            //Adds nodes in Abstraction and Insertion Category
            for (int i = maxAbstraction; i > 0; --i)
            {
                tempCol = scanColumnTrue(i, null);
                nodeSet.add(i);
                for(MatrixCell X : tempCol)
                {
                    tempDirect = directConnectionBackwards(X, i);
                    Connections.add(new Connection(X.getRowId(), i, tempDirect));
                }
            }
            List<Integer> Nodes = new ArrayList<>();
            Nodes.addAll(nodeSet);
            Nodes.addAll(locations);
            Nodes.addAll(properties);
            Nodes = nodeFilter(Nodes, Collections.max(abstraction), Connections);
            Connections = connectionFilter(Nodes, Connections);
            Collections.sort(Nodes);
        }

    }
    private void propertiesOnly(List<TrojanAttribute> PropertiesList)
    {
        List<Integer> propertyIDs = attrToInt(PropertiesList);
        List<Integer> LocationIDs = testRowTrue(propertyIDs, "R34");
        List<Integer> abstractionResults = testColumnTrue(propertyIDs, "R23");
        if (abstractionResults.size() == 0) selectionNotPossible();
        else
        {
        	List<Connection> Connections = new ArrayList<Connection>();
            List<MatrixCell> tempCol = new ArrayList<MatrixCell>();
            HashSet<Integer> nodeSet = new HashSet<Integer>();
            boolean tempDirect = false;
            int maxAbstraction = Collections.max(abstractionResults);

            //Adds nodes in Abstraction and Insertion Category
            for (int i = maxAbstraction; i > 0; --i)
            {
                tempCol = scanColumnTrue(i, null);
                nodeSet.add(i);
                for(MatrixCell X : tempCol)
                {
                    tempDirect = directConnectionBackwards(X, i);
                    Connections.add(new Connection(X.getRowId(), i, tempDirect));
                }
            }
            List<Integer> Nodes = new ArrayList<>();
            Nodes.addAll(nodeSet);
            Nodes.addAll(LocationIDs);
            Nodes.addAll(propertyIDs);
            Nodes = nodeFilter(Nodes, Collections.max(abstractionResults), Connections);
            Connections = connectionFilter(Nodes, Connections);
            Collections.sort(Nodes);
        }
        
    }
    private List<Connection> connectionFilter(List<Integer> nodes, List<Connection> Connections)
    {
        List<Connection> filteredConnections = new ArrayList<Connection>();
        for(Connection C : Connections)
        {
            if (nodes.contains(C.getSource()) && nodes.contains(C.getTarget()))
            {
                filteredConnections.add(C);
            }
        }
        return filteredConnections;
    }
    private List<Integer> nodeFilter(List<Integer> nodes, int end, List<Connection> Connections)
    {
        List<Integer> tempNodes = new ArrayList<Integer>();
        for(Integer X : nodes)
        {
            if (X < 12)
            {
                tempNodes.add(X);
            }
        }
        List<Integer> removedNodes = bellmanForward(tempNodes, Connections);
        removedNodes.addAll(bellmanBackWards(tempNodes, Connections));
        List<Integer> filtereNodes = new ArrayList<Integer>();
        for(Integer X : nodes)
        {
            if (!removedNodes.contains(X))
            {
                filtereNodes.add(X);
            }
        }
        return filtereNodes;
    }
    private List<Integer> bellmanForward(List<Integer> nodes, List<Connection> Connections)
    {
        Integer source = Collections.min(nodes);
        Collections.sort(nodes);
        List<Integer> unreachables = new ArrayList<Integer>();
        HashMap<Integer, Integer> distance = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> predecessor = new HashMap<Integer, Integer>();
        for(Integer X : nodes)
        {
            if (X == source)
            {
                //distance[X] = 0;
            	distance.put(X, 0);
            }
            else
            {
            	distance.put(X, 999);
                //distance[X] = 999;
            }
            //predecessor[X] = 999;
            predecessor.put(X, 999);
        }

        for(@SuppressWarnings("unused") Integer X : nodes)
        {
            for(Connection C : Connections)
            {
                if (distance.get(C.getSource()) != 999)
                {
                    if (nodes.contains(C.getSource()) && nodes.contains(C.getTarget()))
                    {
                        if (distance.get(C.getSource()) + 1 < distance.get(C.getTarget()))
                        {
                            distance.put(C.getTarget(), distance.get(C.getSource()) + 1);
                            predecessor.put(C.getTarget(), C.getSource());
                        }
                    }
                    
                }
            }
        }
        for(Integer X : nodes)
        {
            if (distance.get(X) == 999)
            {
                unreachables.add(X);
            }
        }
        return unreachables;
    }
    private List<Integer> bellmanBackWards(List<Integer> nodes, List<Connection> Connections)
    {
        Integer source = Collections.min(nodes);
        Collections.sort(nodes);
        Collections.reverse(nodes);
        List<Integer> unreachables = new ArrayList<Integer>();
        HashMap<Integer, Integer> distance = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> predecessor = new HashMap<Integer, Integer>();
        for(Integer X : nodes)
        {
            if (X == source)
            {
                //distance[X] = 0;
                distance.put(X, 0);
            }
            else
            {
                //distance[X] = 999;
                distance.put(X, 999);
            }
            //predecessor[X] = 999;
            predecessor.put(X, 999);
        }

        for(@SuppressWarnings("unused") Integer X : nodes)
        {
            for(Connection C : Connections)
            {
                if (nodes.contains(C.getSource()) && nodes.contains(C.getTarget()))
                {
                    if (distance.get(C.getTarget()) != 999)
                    {
                        if (distance.get(C.getTarget()) + 1 < distance.get(C.getSource()))
                        {
                            distance.put(C.getSource(), distance.get(C.getTarget()) + 1);
                            predecessor.put(C.getSource(), C.getTarget());
                        }
                    }
                }
            }
        }
        for(Integer X : nodes)
        {
            if (distance.get(X) == 999)
            {
                unreachables.add(X);
            }
        }
        return unreachables;
    }
    private void selectionNotPossible(){
    	
    }
 
	public void analyzeMatrix(List<TrojanAttribute> attributes){
		HashSet<String> categorySet = getCategorySet(attributes);

        if (!categorySet.contains("Insertion") && !categorySet.contains("Abstraction") && categorySet.contains("Properties") && !categorySet.contains("Location"))
        {
            propertiesOnly(attributes);
        }
        //#2 Used for IAPL: 0100
        else if (!categorySet.contains("Insertion") && categorySet.contains("Abstraction") && !categorySet.contains("Properties") && !categorySet.contains("Location"))
        {
            abstractionOnly(attributes);
        }
        //#3 Used for IAPL: 0001
        else if (!categorySet.contains("Insertion") && !categorySet.contains("Abstraction") && !categorySet.contains("Properties") && categorySet.contains("Location"))
        {
            locationOnly(attributes);
        }
        //#4 Used for IAPL: 1000
        else if (categorySet.contains("Insertion") && !categorySet.contains("Abstraction") && !categorySet.contains("Properties") && !categorySet.contains("Location"))
        {
            insertionOnly(attributes);
        }
        //#5 Used for IAPL: 0101 1100 1101 => ( B . C'. D ) + ( A . B . C')
        else if ((categorySet.contains("Abstraction") && !categorySet.contains("Properties") && categorySet.contains("Location")) || (categorySet.contains("Insertion") && categorySet.contains("Abstraction") && !categorySet.contains("Properties")))
        {
            forwardPropagation(attributes);
        }
        //# 7 Used for IAPL: 0011 1010 1011 => ( B'. C . D ) + ( A . B'. C )
        else if ((!categorySet.contains("Abstraction") && categorySet.contains("Properties") && categorySet.contains("Location"))||(categorySet.contains("Insertion") && !categorySet.contains("Abstraction") && categorySet.contains("Properties")))
        {
            backPropagationNoAbstraction(attributes);
        }
        //#8 Used for IAPL: 0110 0111 1110 1111 => ( B . C )
        else if (categorySet.contains("Abstraction") && categorySet.contains("Properties"))
        {
            backPropagationNoAbstraction(attributes);
        }
        //#9 Used for IAPL: 1001 => ( A . B'. C'. D )
        else if (categorySet.contains("Insertion") && !categorySet.contains("Abstraction") && !categorySet.contains("Properties") && categorySet.contains("Location"))
        {
            splitPropagation(attributes);
        }
        //#X Used for IAPL: 0000
        else
        {
            selectionNotPossible();
        }
	}
	
	private void addCellNumbersAndMakeHashMap() {
		this.cellMapByCellId = new HashMap<>();
		for (int i = 0; i < this.cells.size(); ++i) {
			this.cells.get(i).setCellId(i);
			this.cellMapByCellId.put(i, this.cells.get(i));
		}
	}

	public HashMap<Integer, MatrixCell> getCellMapByCellId() {
		return this.cellMapByCellId;
	}

    //Determines if a colId is a direct connection
    //to the current Matrix_Cell when doing backwards propagation 
    private boolean directConnectionForwards(MatrixCell X, int i)
    {
        if (Math.abs(i - X.getColumnId()) == 1) return false;
        else return true;
    }
    private boolean directConnectionForwards(int X, int i)
    {
        if (Math.abs(i - X) == 1) return false;
        else return true;
    }

	public void setCellMapByCellId(
			final HashMap<Integer, MatrixCell> cellMapByCellId) {
		this.cellMapByCellId = cellMapByCellId;
	}

	public void setCells(final List<MatrixCell> cells) {
		this.cells = cells;
	}
}
