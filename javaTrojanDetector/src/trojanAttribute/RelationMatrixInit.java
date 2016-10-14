package trojanAttribute;

import java.util.ArrayList;
import java.util.List;

public class RelationMatrixInit {
	
	public RelationMatrixInit(){
		
	}
	
	public List<MatrixCell> getCells() {
		final List<MatrixCell> entries = new ArrayList<MatrixCell>();
		entries.add(new MatrixCell(1, 1, false, "R1"));
		entries.add(new MatrixCell(1, 2, true, "R1"));
		entries.add(new MatrixCell(1, 3, false, "R1"));
		entries.add(new MatrixCell(1, 4, false, "R1"));
		entries.add(new MatrixCell(1, 5, false, "R1"));
		entries.add(new MatrixCell(2, 1, false, "R1"));
		entries.add(new MatrixCell(2, 2, false, "R1"));
		entries.add(new MatrixCell(2, 3, true, "R1"));
		entries.add(new MatrixCell(2, 4, false, "R1"));
		entries.add(new MatrixCell(2, 5, false, "R1"));
		entries.add(new MatrixCell(3, 1, false, "R1"));
		entries.add(new MatrixCell(3, 2, false, "R1"));
		entries.add(new MatrixCell(3, 3, false, "R1"));
		entries.add(new MatrixCell(3, 4, true, "R1"));
		entries.add(new MatrixCell(3, 5, false, "R1"));
		entries.add(new MatrixCell(4, 1, false, "R1"));
		entries.add(new MatrixCell(4, 2, false, "R1"));
		entries.add(new MatrixCell(4, 3, false, "R1"));
		entries.add(new MatrixCell(4, 4, false, "R1"));
		entries.add(new MatrixCell(4, 5, true, "R1"));
		entries.add(new MatrixCell(5, 1, false, "R1"));
		entries.add(new MatrixCell(5, 2, false, "R1"));
		entries.add(new MatrixCell(5, 3, false, "R1"));
		entries.add(new MatrixCell(5, 4, false, "R1"));
		entries.add(new MatrixCell(5, 5, false, "R1"));
		entries.add(new MatrixCell(1, 6, true, "R12"));
		entries.add(new MatrixCell(1, 7, false, "R12"));
		entries.add(new MatrixCell(1, 8, false, "R12"));
		entries.add(new MatrixCell(1, 9, false, "R12"));
		entries.add(new MatrixCell(1, 10, false, "R12"));
		entries.add(new MatrixCell(1, 11, false, "R12"));
		entries.add(new MatrixCell(2, 6, false, "R12"));
		entries.add(new MatrixCell(2, 7, true, "R12"));
		entries.add(new MatrixCell(2, 8, false, "R12"));
		entries.add(new MatrixCell(2, 9, false, "R12"));
		entries.add(new MatrixCell(2, 10, false, "R12"));
		entries.add(new MatrixCell(2, 11, false, "R12"));
		entries.add(new MatrixCell(3, 6, false, "R12"));
		entries.add(new MatrixCell(3, 7, false, "R12"));
		entries.add(new MatrixCell(3, 8, false, "R12"));
		entries.add(new MatrixCell(3, 9, false, "R12"));
		entries.add(new MatrixCell(3, 10, false, "R12"));
		entries.add(new MatrixCell(3, 11, true, "R12"));
		entries.add(new MatrixCell(4, 6, true, "R12"));
		entries.add(new MatrixCell(4, 7, false, "R12"));
		entries.add(new MatrixCell(4, 8, false, "R12"));
		entries.add(new MatrixCell(4, 9, true, "R12"));
		entries.add(new MatrixCell(4, 10, false, "R12"));
		entries.add(new MatrixCell(4, 11, false, "R12"));
		entries.add(new MatrixCell(5, 6, true, "R12"));
		entries.add(new MatrixCell(5, 7, false, "R12"));
		entries.add(new MatrixCell(5, 8, false, "R12"));
		entries.add(new MatrixCell(5, 9, false, "R12"));
		entries.add(new MatrixCell(5, 10, false, "R12"));
		entries.add(new MatrixCell(5, 11, false, "R12"));
		entries.add(new MatrixCell(6, 6, false, "R2"));
		entries.add(new MatrixCell(6, 7, true, "R2"));
		entries.add(new MatrixCell(6, 8, false, "R2"));
		entries.add(new MatrixCell(6, 9, false, "R2"));
		entries.add(new MatrixCell(6, 10, false, "R2"));
		entries.add(new MatrixCell(6, 11, false, "R2"));
		entries.add(new MatrixCell(7, 6, false, "R2"));
		entries.add(new MatrixCell(7, 7, false, "R2"));
		entries.add(new MatrixCell(7, 8, true, "R2"));
		entries.add(new MatrixCell(7, 9, false, "R2"));
		entries.add(new MatrixCell(7, 10, false, "R2"));
		entries.add(new MatrixCell(7, 11, false, "R2"));
		entries.add(new MatrixCell(8, 6, false, "R2"));
		entries.add(new MatrixCell(8, 7, false, "R2"));
		entries.add(new MatrixCell(8, 8, false, "R2"));
		entries.add(new MatrixCell(8, 9, true, "R2"));
		entries.add(new MatrixCell(8, 10, false, "R2"));
		entries.add(new MatrixCell(8, 11, false, "R2"));
		entries.add(new MatrixCell(9, 6, false, "R2"));
		entries.add(new MatrixCell(9, 7, false, "R2"));
		entries.add(new MatrixCell(9, 8, false, "R2"));
		entries.add(new MatrixCell(9, 9, false, "R2"));
		entries.add(new MatrixCell(9, 10, true, "R2"));
		entries.add(new MatrixCell(9, 11, false, "R2"));
		entries.add(new MatrixCell(10, 6, false, "R2"));
		entries.add(new MatrixCell(10, 7, false, "R2"));
		entries.add(new MatrixCell(10, 8, false, "R2"));
		entries.add(new MatrixCell(10, 9, false, "R2"));
		entries.add(new MatrixCell(10, 10, false, "R2"));
		entries.add(new MatrixCell(10, 11, true, "R2"));
		entries.add(new MatrixCell(11, 6, false, "R2"));
		entries.add(new MatrixCell(11, 7, false, "R2"));
		entries.add(new MatrixCell(11, 8, false, "R2"));
		entries.add(new MatrixCell(11, 9, false, "R2"));
		entries.add(new MatrixCell(11, 10, false, "R2"));
		entries.add(new MatrixCell(11, 11, false, "R2"));
		// ============== Matrix R23 ===============//
		entries.add(new MatrixCell(6, 12, true, "R23"));
		entries.add(new MatrixCell(6, 13, true, "R23"));
		entries.add(new MatrixCell(6, 14, false, "R23"));
		entries.add(new MatrixCell(6, 15, true, "R23"));
		entries.add(new MatrixCell(6, 16, false, "R23"));
		entries.add(new MatrixCell(6, 17, false, "R23"));
		entries.add(new MatrixCell(6, 18, true, "R23"));
		entries.add(new MatrixCell(6, 19, true, "R23"));
		entries.add(new MatrixCell(6, 20, true, "R23"));
		entries.add(new MatrixCell(6, 21, false, "R23"));
		entries.add(new MatrixCell(6, 22, false, "R23"));
		entries.add(new MatrixCell(6, 23, false, "R23"));
		entries.add(new MatrixCell(6, 24, false, "R23"));
		entries.add(new MatrixCell(6, 25, false, "R23"));
		entries.add(new MatrixCell(6, 26, false, "R23"));
		entries.add(new MatrixCell(6, 27, false, "R23"));
		entries.add(new MatrixCell(6, 28, false, "R23"));
		entries.add(new MatrixCell(7, 12, true, "R23"));
		entries.add(new MatrixCell(7, 13, false, "R23"));
		entries.add(new MatrixCell(7, 14, false, "R23"));
		entries.add(new MatrixCell(7, 15, true, "R23"));
		entries.add(new MatrixCell(7, 16, true, "R23"));
		entries.add(new MatrixCell(7, 17, true, "R23"));
		entries.add(new MatrixCell(7, 18, true, "R23"));
		entries.add(new MatrixCell(7, 19, false, "R23"));
		entries.add(new MatrixCell(7, 20, true, "R23"));
		entries.add(new MatrixCell(7, 21, true, "R23"));
		entries.add(new MatrixCell(7, 22, true, "R23"));
		entries.add(new MatrixCell(7, 23, true, "R23"));
		entries.add(new MatrixCell(7, 24, true, "R23"));
		entries.add(new MatrixCell(7, 25, false, "R23"));
		entries.add(new MatrixCell(7, 26, false, "R23"));
		entries.add(new MatrixCell(7, 27, false, "R23"));
		entries.add(new MatrixCell(7, 28, false, "R23"));
		entries.add(new MatrixCell(8, 12, true, "R23"));
		entries.add(new MatrixCell(8, 13, false, "R23"));
		entries.add(new MatrixCell(8, 14, false, "R23"));
		entries.add(new MatrixCell(8, 15, true, "R23"));
		entries.add(new MatrixCell(8, 16, true, "R23"));
		entries.add(new MatrixCell(8, 17, true, "R23"));
		entries.add(new MatrixCell(8, 18, true, "R23"));
		entries.add(new MatrixCell(8, 19, false, "R23"));
		entries.add(new MatrixCell(8, 20, true, "R23"));
		entries.add(new MatrixCell(8, 21, true, "R23"));
		entries.add(new MatrixCell(8, 22, true, "R23"));
		entries.add(new MatrixCell(8, 23, true, "R23"));
		entries.add(new MatrixCell(8, 24, true, "R23"));
		entries.add(new MatrixCell(8, 25, true, "R23"));
		entries.add(new MatrixCell(8, 26, true, "R23"));
		entries.add(new MatrixCell(8, 27, true, "R23"));
		entries.add(new MatrixCell(8, 28, true, "R23"));
		entries.add(new MatrixCell(9, 12, true, "R23"));
		entries.add(new MatrixCell(9, 13, false, "R23"));
		entries.add(new MatrixCell(9, 14, false, "R23"));
		entries.add(new MatrixCell(9, 15, true, "R23"));
		entries.add(new MatrixCell(9, 16, true, "R23"));
		entries.add(new MatrixCell(9, 17, true, "R23"));
		entries.add(new MatrixCell(9, 18, true, "R23"));
		entries.add(new MatrixCell(9, 19, false, "R23"));
		entries.add(new MatrixCell(9, 20, true, "R23"));
		entries.add(new MatrixCell(9, 21, true, "R23"));
		entries.add(new MatrixCell(9, 22, true, "R23"));
		entries.add(new MatrixCell(9, 23, false, "R23"));
		entries.add(new MatrixCell(9, 24, false, "R23"));
		entries.add(new MatrixCell(9, 25, false, "R23"));
		entries.add(new MatrixCell(9, 26, false, "R23"));
		entries.add(new MatrixCell(9, 27, false, "R23"));
		entries.add(new MatrixCell(9, 28, false, "R23"));
		entries.add(new MatrixCell(10, 12, true, "R23"));
		entries.add(new MatrixCell(10, 13, false, "R23"));
		entries.add(new MatrixCell(10, 14, true, "R23"));
		entries.add(new MatrixCell(10, 15, false, "R23"));
		entries.add(new MatrixCell(10, 16, false, "R23"));
		entries.add(new MatrixCell(10, 17, true, "R23"));
		entries.add(new MatrixCell(10, 18, true, "R23"));
		entries.add(new MatrixCell(10, 19, true, "R23"));
		entries.add(new MatrixCell(10, 20, true, "R23"));
		entries.add(new MatrixCell(10, 21, false, "R23"));
		entries.add(new MatrixCell(10, 22, false, "R23"));
		entries.add(new MatrixCell(10, 23, false, "R23"));
		entries.add(new MatrixCell(10, 24, true, "R23"));
		entries.add(new MatrixCell(10, 25, false, "R23"));
		entries.add(new MatrixCell(10, 26, true, "R23"));
		entries.add(new MatrixCell(10, 27, true, "R23"));
		entries.add(new MatrixCell(10, 28, false, "R23"));
		entries.add(new MatrixCell(11, 12, true, "R23"));
		entries.add(new MatrixCell(11, 13, true, "R23"));
		entries.add(new MatrixCell(11, 14, true, "R23"));
		entries.add(new MatrixCell(11, 15, false, "R23"));
		entries.add(new MatrixCell(11, 16, false, "R23"));
		entries.add(new MatrixCell(11, 17, false, "R23"));
		entries.add(new MatrixCell(11, 18, true, "R23"));
		entries.add(new MatrixCell(11, 19, true, "R23"));
		entries.add(new MatrixCell(11, 20, true, "R23"));
		entries.add(new MatrixCell(11, 21, false, "R23"));
		entries.add(new MatrixCell(11, 22, false, "R23"));
		entries.add(new MatrixCell(11, 23, true, "R23"));
		entries.add(new MatrixCell(11, 24, true, "R23"));
		entries.add(new MatrixCell(11, 25, true, "R23"));
		entries.add(new MatrixCell(11, 26, true, "R23"));
		entries.add(new MatrixCell(11, 27, true, "R23"));
		entries.add(new MatrixCell(11, 28, true, "R23"));
		// ==============Matrix R3 =================//
		entries.add(new MatrixCell(12, 12, false, "R3"));
		entries.add(new MatrixCell(12, 13, false, "R3"));
		entries.add(new MatrixCell(12, 14, false, "R3"));
		entries.add(new MatrixCell(12, 15, false, "R3"));
		entries.add(new MatrixCell(12, 16, true, "R3"));
		entries.add(new MatrixCell(12, 17, true, "R3"));
		entries.add(new MatrixCell(12, 18, true, "R3"));
		entries.add(new MatrixCell(12, 19, false, "R3"));
		entries.add(new MatrixCell(12, 20, true, "R3"));
		entries.add(new MatrixCell(12, 21, true, "R3"));
		entries.add(new MatrixCell(12, 22, true, "R3"));
		entries.add(new MatrixCell(12, 23, true, "R3"));
		entries.add(new MatrixCell(12, 24, true, "R3"));
		entries.add(new MatrixCell(12, 25, true, "R3"));
		entries.add(new MatrixCell(12, 26, true, "R3"));
		entries.add(new MatrixCell(12, 27, true, "R3"));
		entries.add(new MatrixCell(12, 28, true, "R3"));
		entries.add(new MatrixCell(13, 12, false, "R3"));
		entries.add(new MatrixCell(13, 13, false, "R3"));
		entries.add(new MatrixCell(13, 14, false, "R3"));
		entries.add(new MatrixCell(13, 15, false, "R3"));
		entries.add(new MatrixCell(13, 16, false, "R3"));
		entries.add(new MatrixCell(13, 17, true, "R3"));
		entries.add(new MatrixCell(13, 18, false, "R3"));
		entries.add(new MatrixCell(13, 19, true, "R3"));
		entries.add(new MatrixCell(13, 20, true, "R3"));
		entries.add(new MatrixCell(13, 21, false, "R3"));
		entries.add(new MatrixCell(13, 22, true, "R3"));
		entries.add(new MatrixCell(13, 23, false, "R3"));
		entries.add(new MatrixCell(13, 24, true, "R3"));
		entries.add(new MatrixCell(13, 25, false, "R3"));
		entries.add(new MatrixCell(13, 26, true, "R3"));
		entries.add(new MatrixCell(13, 27, true, "R3"));
		entries.add(new MatrixCell(13, 28, true, "R3"));
		entries.add(new MatrixCell(14, 12, false, "R3"));
		entries.add(new MatrixCell(14, 13, false, "R3"));
		entries.add(new MatrixCell(14, 14, false, "R3"));
		entries.add(new MatrixCell(14, 15, false, "R3"));
		entries.add(new MatrixCell(14, 16, false, "R3"));
		entries.add(new MatrixCell(14, 17, false, "R3"));
		entries.add(new MatrixCell(14, 18, false, "R3"));
		entries.add(new MatrixCell(14, 19, true, "R3"));
		entries.add(new MatrixCell(14, 20, true, "R3"));
		entries.add(new MatrixCell(14, 21, false, "R3"));
		entries.add(new MatrixCell(14, 22, false, "R3"));
		entries.add(new MatrixCell(14, 23, false, "R3"));
		entries.add(new MatrixCell(14, 24, true, "R3"));
		entries.add(new MatrixCell(14, 25, false, "R3"));
		entries.add(new MatrixCell(14, 26, true, "R3"));
		entries.add(new MatrixCell(14, 27, true, "R3"));
		entries.add(new MatrixCell(14, 28, true, "R3"));
		entries.add(new MatrixCell(15, 12, false, "R3"));
		entries.add(new MatrixCell(15, 13, false, "R3"));
		entries.add(new MatrixCell(15, 14, false, "R3"));
		entries.add(new MatrixCell(15, 15, false, "R3"));
		entries.add(new MatrixCell(15, 16, true, "R3"));
		entries.add(new MatrixCell(15, 17, true, "R3"));
		entries.add(new MatrixCell(15, 18, true, "R3"));
		entries.add(new MatrixCell(15, 19, false, "R3"));
		entries.add(new MatrixCell(15, 20, true, "R3"));
		entries.add(new MatrixCell(15, 21, true, "R3"));
		entries.add(new MatrixCell(15, 22, true, "R3"));
		entries.add(new MatrixCell(15, 23, true, "R3"));
		entries.add(new MatrixCell(15, 24, true, "R3"));
		entries.add(new MatrixCell(15, 25, true, "R3"));
		entries.add(new MatrixCell(15, 26, true, "R3"));
		entries.add(new MatrixCell(15, 27, true, "R3"));
		entries.add(new MatrixCell(15, 28, true, "R3"));
		entries.add(new MatrixCell(16, 12, true, "R3"));
		entries.add(new MatrixCell(16, 13, false, "R3"));
		entries.add(new MatrixCell(16, 14, false, "R3"));
		entries.add(new MatrixCell(16, 15, true, "R3"));
		entries.add(new MatrixCell(16, 16, false, "R3"));
		entries.add(new MatrixCell(16, 17, false, "R3"));
		entries.add(new MatrixCell(16, 18, true, "R3"));
		entries.add(new MatrixCell(16, 19, false, "R3"));
		entries.add(new MatrixCell(16, 20, false, "R3"));
		entries.add(new MatrixCell(16, 21, true, "R3"));
		entries.add(new MatrixCell(16, 22, true, "R3"));
		entries.add(new MatrixCell(16, 23, true, "R3"));
		entries.add(new MatrixCell(16, 24, false, "R3"));
		entries.add(new MatrixCell(16, 25, true, "R3"));
		entries.add(new MatrixCell(16, 26, true, "R3"));
		entries.add(new MatrixCell(16, 27, true, "R3"));
		entries.add(new MatrixCell(16, 28, true, "R3"));
		entries.add(new MatrixCell(17, 12, true, "R3"));
		entries.add(new MatrixCell(17, 13, true, "R3"));
		entries.add(new MatrixCell(17, 14, false, "R3"));
		entries.add(new MatrixCell(17, 15, true, "R3"));
		entries.add(new MatrixCell(17, 16, false, "R3"));
		entries.add(new MatrixCell(17, 17, false, "R3"));
		entries.add(new MatrixCell(17, 18, true, "R3"));
		entries.add(new MatrixCell(17, 19, false, "R3"));
		entries.add(new MatrixCell(17, 20, true, "R3"));
		entries.add(new MatrixCell(17, 21, true, "R3"));
		entries.add(new MatrixCell(17, 22, true, "R3"));
		entries.add(new MatrixCell(17, 23, true, "R3"));
		entries.add(new MatrixCell(17, 24, true, "R3"));
		entries.add(new MatrixCell(17, 25, true, "R3"));
		entries.add(new MatrixCell(17, 26, true, "R3"));
		entries.add(new MatrixCell(17, 27, true, "R3"));
		entries.add(new MatrixCell(17, 28, true, "R3"));
		entries.add(new MatrixCell(18, 12, true, "R3"));
		entries.add(new MatrixCell(18, 13, false, "R3"));
		entries.add(new MatrixCell(18, 14, false, "R3"));
		entries.add(new MatrixCell(18, 15, true, "R3"));
		entries.add(new MatrixCell(18, 16, true, "R3"));
		entries.add(new MatrixCell(18, 17, true, "R3"));
		entries.add(new MatrixCell(18, 18, false, "R3"));
		entries.add(new MatrixCell(18, 19, false, "R3"));
		entries.add(new MatrixCell(18, 20, true, "R3"));
		entries.add(new MatrixCell(18, 21, true, "R3"));
		entries.add(new MatrixCell(18, 22, true, "R3"));
		entries.add(new MatrixCell(18, 23, true, "R3"));
		entries.add(new MatrixCell(18, 24, true, "R3"));
		entries.add(new MatrixCell(18, 25, true, "R3"));
		entries.add(new MatrixCell(18, 26, true, "R3"));
		entries.add(new MatrixCell(18, 27, true, "R3"));
		entries.add(new MatrixCell(18, 28, true, "R3"));
		entries.add(new MatrixCell(19, 12, false, "R3"));
		entries.add(new MatrixCell(19, 13, true, "R3"));
		entries.add(new MatrixCell(19, 14, true, "R3"));
		entries.add(new MatrixCell(19, 15, false, "R3"));
		entries.add(new MatrixCell(19, 16, false, "R3"));
		entries.add(new MatrixCell(19, 17, false, "R3"));
		entries.add(new MatrixCell(19, 18, false, "R3"));
		entries.add(new MatrixCell(19, 19, false, "R3"));
		entries.add(new MatrixCell(19, 20, true, "R3"));
		entries.add(new MatrixCell(19, 21, false, "R3"));
		entries.add(new MatrixCell(19, 22, false, "R3"));
		entries.add(new MatrixCell(19, 23, false, "R3"));
		entries.add(new MatrixCell(19, 24, true, "R3"));
		entries.add(new MatrixCell(19, 25, false, "R3"));
		entries.add(new MatrixCell(19, 26, true, "R3"));
		entries.add(new MatrixCell(19, 27, false, "R3"));
		entries.add(new MatrixCell(19, 28, true, "R3"));
		entries.add(new MatrixCell(20, 12, true, "R3"));
		entries.add(new MatrixCell(20, 13, true, "R3"));
		entries.add(new MatrixCell(20, 14, true, "R3"));
		entries.add(new MatrixCell(20, 15, true, "R3"));
		entries.add(new MatrixCell(20, 16, false, "R3"));
		entries.add(new MatrixCell(20, 17, true, "R3"));
		entries.add(new MatrixCell(20, 18, true, "R3"));
		entries.add(new MatrixCell(20, 19, true, "R3"));
		entries.add(new MatrixCell(20, 20, false, "R3"));
		entries.add(new MatrixCell(20, 21, false, "R3"));
		entries.add(new MatrixCell(20, 22, false, "R3"));
		entries.add(new MatrixCell(20, 23, true, "R3"));
		entries.add(new MatrixCell(20, 24, true, "R3"));
		entries.add(new MatrixCell(20, 25, true, "R3"));
		entries.add(new MatrixCell(20, 26, true, "R3"));
		entries.add(new MatrixCell(20, 27, true, "R3"));
		entries.add(new MatrixCell(20, 28, true, "R3"));
		entries.add(new MatrixCell(21, 12, true, "R3"));
		entries.add(new MatrixCell(21, 13, false, "R3"));
		entries.add(new MatrixCell(21, 14, false, "R3"));
		entries.add(new MatrixCell(21, 15, true, "R3"));
		entries.add(new MatrixCell(21, 16, true, "R3"));
		entries.add(new MatrixCell(21, 17, true, "R3"));
		entries.add(new MatrixCell(21, 18, true, "R3"));
		entries.add(new MatrixCell(21, 19, false, "R3"));
		entries.add(new MatrixCell(21, 20, false, "R3"));
		entries.add(new MatrixCell(21, 21, false, "R3"));
		entries.add(new MatrixCell(21, 22, false, "R3"));
		entries.add(new MatrixCell(21, 23, true, "R3"));
		entries.add(new MatrixCell(21, 24, true, "R3"));
		entries.add(new MatrixCell(21, 25, false, "R3"));
		entries.add(new MatrixCell(21, 26, true, "R3"));
		entries.add(new MatrixCell(21, 27, true, "R3"));
		entries.add(new MatrixCell(21, 28, true, "R3"));
		entries.add(new MatrixCell(22, 12, true, "R3"));
		entries.add(new MatrixCell(22, 13, true, "R3"));
		entries.add(new MatrixCell(22, 14, false, "R3"));
		entries.add(new MatrixCell(22, 15, true, "R3"));
		entries.add(new MatrixCell(22, 16, true, "R3"));
		entries.add(new MatrixCell(22, 17, true, "R3"));
		entries.add(new MatrixCell(22, 18, true, "R3"));
		entries.add(new MatrixCell(22, 19, false, "R3"));
		entries.add(new MatrixCell(22, 20, false, "R3"));
		entries.add(new MatrixCell(22, 21, false, "R3"));
		entries.add(new MatrixCell(22, 22, false, "R3"));
		entries.add(new MatrixCell(22, 23, false, "R3"));
		entries.add(new MatrixCell(22, 24, true, "R3"));
		entries.add(new MatrixCell(22, 25, false, "R3"));
		entries.add(new MatrixCell(22, 26, true, "R3"));
		entries.add(new MatrixCell(22, 27, true, "R3"));
		entries.add(new MatrixCell(22, 28, false, "R3"));
		entries.add(new MatrixCell(23, 12, true, "R3"));
		entries.add(new MatrixCell(23, 13, false, "R3"));
		entries.add(new MatrixCell(23, 14, false, "R3"));
		entries.add(new MatrixCell(23, 15, true, "R3"));
		entries.add(new MatrixCell(23, 16, true, "R3"));
		entries.add(new MatrixCell(23, 17, true, "R3"));
		entries.add(new MatrixCell(23, 18, true, "R3"));
		entries.add(new MatrixCell(23, 19, false, "R3"));
		entries.add(new MatrixCell(23, 20, true, "R3"));
		entries.add(new MatrixCell(23, 21, true, "R3"));
		entries.add(new MatrixCell(23, 22, false, "R3"));
		entries.add(new MatrixCell(23, 23, false, "R3"));
		entries.add(new MatrixCell(23, 24, false, "R3"));
		entries.add(new MatrixCell(23, 25, true, "R3"));
		entries.add(new MatrixCell(23, 26, true, "R3"));
		entries.add(new MatrixCell(23, 27, true, "R3"));
		entries.add(new MatrixCell(23, 28, true, "R3"));
		entries.add(new MatrixCell(24, 12, true, "R3"));
		entries.add(new MatrixCell(24, 13, true, "R3"));
		entries.add(new MatrixCell(24, 14, true, "R3"));
		entries.add(new MatrixCell(24, 15, true, "R3"));
		entries.add(new MatrixCell(24, 16, false, "R3"));
		entries.add(new MatrixCell(24, 17, true, "R3"));
		entries.add(new MatrixCell(24, 18, true, "R3"));
		entries.add(new MatrixCell(24, 19, true, "R3"));
		entries.add(new MatrixCell(24, 20, true, "R3"));
		entries.add(new MatrixCell(24, 21, true, "R3"));
		entries.add(new MatrixCell(24, 22, true, "R3"));
		entries.add(new MatrixCell(24, 23, false, "R3"));
		entries.add(new MatrixCell(24, 24, false, "R3"));
		entries.add(new MatrixCell(24, 25, false, "R3"));
		entries.add(new MatrixCell(24, 26, true, "R3"));
		entries.add(new MatrixCell(24, 27, true, "R3"));
		entries.add(new MatrixCell(24, 28, false, "R3"));
		entries.add(new MatrixCell(25, 12, true, "R3"));
		entries.add(new MatrixCell(25, 13, false, "R3"));
		entries.add(new MatrixCell(25, 14, false, "R3"));
		entries.add(new MatrixCell(25, 15, true, "R3"));
		entries.add(new MatrixCell(25, 16, true, "R3"));
		entries.add(new MatrixCell(25, 17, true, "R3"));
		entries.add(new MatrixCell(25, 18, true, "R3"));
		entries.add(new MatrixCell(25, 19, false, "R3"));
		entries.add(new MatrixCell(25, 20, true, "R3"));
		entries.add(new MatrixCell(25, 21, false, "R3"));
		entries.add(new MatrixCell(25, 22, false, "R3"));
		entries.add(new MatrixCell(25, 23, true, "R3"));
		entries.add(new MatrixCell(25, 24, false, "R3"));
		entries.add(new MatrixCell(25, 25, false, "R3"));
		entries.add(new MatrixCell(25, 26, true, "R3"));
		entries.add(new MatrixCell(25, 27, true, "R3"));
		entries.add(new MatrixCell(25, 28, false, "R3"));
		entries.add(new MatrixCell(26, 12, true, "R3"));
		entries.add(new MatrixCell(26, 13, true, "R3"));
		entries.add(new MatrixCell(26, 14, true, "R3"));
		entries.add(new MatrixCell(26, 15, true, "R3"));
		entries.add(new MatrixCell(26, 16, true, "R3"));
		entries.add(new MatrixCell(26, 17, true, "R3"));
		entries.add(new MatrixCell(26, 18, true, "R3"));
		entries.add(new MatrixCell(26, 19, true, "R3"));
		entries.add(new MatrixCell(26, 20, true, "R3"));
		entries.add(new MatrixCell(26, 21, true, "R3"));
		entries.add(new MatrixCell(26, 22, true, "R3"));
		entries.add(new MatrixCell(26, 23, true, "R3"));
		entries.add(new MatrixCell(26, 24, true, "R3"));
		entries.add(new MatrixCell(26, 25, true, "R3"));
		entries.add(new MatrixCell(26, 26, false, "R3"));
		entries.add(new MatrixCell(26, 27, true, "R3"));
		entries.add(new MatrixCell(26, 28, true, "R3"));
		entries.add(new MatrixCell(27, 12, true, "R3"));
		entries.add(new MatrixCell(27, 13, true, "R3"));
		entries.add(new MatrixCell(27, 14, true, "R3"));
		entries.add(new MatrixCell(27, 15, true, "R3"));
		entries.add(new MatrixCell(27, 16, true, "R3"));
		entries.add(new MatrixCell(27, 17, true, "R3"));
		entries.add(new MatrixCell(27, 18, true, "R3"));
		entries.add(new MatrixCell(27, 19, false, "R3"));
		entries.add(new MatrixCell(27, 20, true, "R3"));
		entries.add(new MatrixCell(27, 21, true, "R3"));
		entries.add(new MatrixCell(27, 22, true, "R3"));
		entries.add(new MatrixCell(27, 23, true, "R3"));
		entries.add(new MatrixCell(27, 24, true, "R3"));
		entries.add(new MatrixCell(27, 25, true, "R3"));
		entries.add(new MatrixCell(27, 26, true, "R3"));
		entries.add(new MatrixCell(27, 27, false, "R3"));
		entries.add(new MatrixCell(27, 28, false, "R3"));
		entries.add(new MatrixCell(28, 12, true, "R3"));
		entries.add(new MatrixCell(28, 13, true, "R3"));
		entries.add(new MatrixCell(28, 14, true, "R3"));
		entries.add(new MatrixCell(28, 15, true, "R3"));
		entries.add(new MatrixCell(28, 16, true, "R3"));
		entries.add(new MatrixCell(28, 17, true, "R3"));
		entries.add(new MatrixCell(28, 18, true, "R3"));
		entries.add(new MatrixCell(28, 19, true, "R3"));
		entries.add(new MatrixCell(28, 20, true, "R3"));
		entries.add(new MatrixCell(28, 21, true, "R3"));
		entries.add(new MatrixCell(28, 22, false, "R3"));
		entries.add(new MatrixCell(28, 23, true, "R3"));
		entries.add(new MatrixCell(28, 24, false, "R3"));
		entries.add(new MatrixCell(28, 25, false, "R3"));
		entries.add(new MatrixCell(28, 26, true, "R3"));
		entries.add(new MatrixCell(28, 27, false, "R3"));
		entries.add(new MatrixCell(28, 28, false, "R3"));
		// ================= Matrix R34 ==============//
		entries.add(new MatrixCell(12, 29, true, "R34"));
		entries.add(new MatrixCell(12, 30, true, "R34"));
		entries.add(new MatrixCell(12, 31, true, "R34"));
		entries.add(new MatrixCell(12, 32, true, "R34"));
		entries.add(new MatrixCell(12, 33, true, "R34"));
		entries.add(new MatrixCell(13, 29, true, "R34"));
		entries.add(new MatrixCell(13, 30, true, "R34"));
		entries.add(new MatrixCell(13, 31, true, "R34"));
		entries.add(new MatrixCell(13, 32, true, "R34"));
		entries.add(new MatrixCell(13, 33, true, "R34"));
		entries.add(new MatrixCell(14, 29, true, "R34"));
		entries.add(new MatrixCell(14, 30, true, "R34"));
		entries.add(new MatrixCell(14, 31, true, "R34"));
		entries.add(new MatrixCell(14, 32, true, "R34"));
		entries.add(new MatrixCell(14, 33, true, "R34"));
		entries.add(new MatrixCell(15, 29, true, "R34"));
		entries.add(new MatrixCell(15, 30, false, "R34"));
		entries.add(new MatrixCell(15, 31, true, "R34"));
		entries.add(new MatrixCell(15, 32, true, "R34"));
		entries.add(new MatrixCell(15, 33, true, "R34"));
		entries.add(new MatrixCell(16, 29, true, "R34"));
		entries.add(new MatrixCell(16, 30, true, "R34"));
		entries.add(new MatrixCell(16, 31, true, "R34"));
		entries.add(new MatrixCell(16, 32, true, "R34"));
		entries.add(new MatrixCell(16, 33, true, "R34"));
		entries.add(new MatrixCell(17, 29, true, "R34"));
		entries.add(new MatrixCell(17, 30, true, "R34"));
		entries.add(new MatrixCell(17, 31, true, "R34"));
		entries.add(new MatrixCell(17, 32, true, "R34"));
		entries.add(new MatrixCell(17, 33, true, "R34"));
		entries.add(new MatrixCell(18, 29, true, "R34"));
		entries.add(new MatrixCell(18, 30, true, "R34"));
		entries.add(new MatrixCell(18, 31, true, "R34"));
		entries.add(new MatrixCell(18, 32, true, "R34"));
		entries.add(new MatrixCell(18, 33, true, "R34"));
		entries.add(new MatrixCell(19, 29, false, "R34"));
		entries.add(new MatrixCell(19, 30, false, "R34"));
		entries.add(new MatrixCell(19, 31, true, "R34"));
		entries.add(new MatrixCell(19, 32, true, "R34"));
		entries.add(new MatrixCell(19, 33, true, "R34"));
		entries.add(new MatrixCell(20, 29, true, "R34"));
		entries.add(new MatrixCell(20, 30, true, "R34"));
		entries.add(new MatrixCell(20, 31, true, "R34"));
		entries.add(new MatrixCell(20, 32, true, "R34"));
		entries.add(new MatrixCell(20, 33, true, "R34"));
		entries.add(new MatrixCell(21, 29, true, "R34"));
		entries.add(new MatrixCell(21, 30, true, "R34"));
		entries.add(new MatrixCell(21, 31, true, "R34"));
		entries.add(new MatrixCell(21, 32, true, "R34"));
		entries.add(new MatrixCell(21, 33, true, "R34"));
		entries.add(new MatrixCell(22, 29, true, "R34"));
		entries.add(new MatrixCell(22, 30, true, "R34"));
		entries.add(new MatrixCell(22, 31, true, "R34"));
		entries.add(new MatrixCell(22, 32, true, "R34"));
		entries.add(new MatrixCell(22, 33, true, "R34"));
		entries.add(new MatrixCell(23, 29, true, "R34"));
		entries.add(new MatrixCell(23, 30, true, "R34"));
		entries.add(new MatrixCell(23, 31, false, "R34"));
		entries.add(new MatrixCell(23, 32, false, "R34"));
		entries.add(new MatrixCell(23, 33, false, "R34"));
		entries.add(new MatrixCell(24, 29, true, "R34"));
		entries.add(new MatrixCell(24, 30, true, "R34"));
		entries.add(new MatrixCell(24, 31, true, "R34"));
		entries.add(new MatrixCell(24, 32, true, "R34"));
		entries.add(new MatrixCell(24, 33, true, "R34"));
		entries.add(new MatrixCell(25, 29, true, "R34"));
		entries.add(new MatrixCell(25, 30, true, "R34"));
		entries.add(new MatrixCell(25, 31, true, "R34"));
		entries.add(new MatrixCell(25, 32, true, "R34"));
		entries.add(new MatrixCell(25, 33, true, "R34"));
		entries.add(new MatrixCell(26, 29, true, "R34"));
		entries.add(new MatrixCell(26, 30, true, "R34"));
		entries.add(new MatrixCell(26, 31, true, "R34"));
		entries.add(new MatrixCell(26, 32, true, "R34"));
		entries.add(new MatrixCell(26, 33, true, "R34"));
		entries.add(new MatrixCell(27, 29, true, "R34"));
		entries.add(new MatrixCell(27, 30, true, "R34"));
		entries.add(new MatrixCell(27, 31, true, "R34"));
		entries.add(new MatrixCell(27, 32, true, "R34"));
		entries.add(new MatrixCell(27, 33, true, "R34"));
		entries.add(new MatrixCell(28, 29, true, "R34"));
		entries.add(new MatrixCell(28, 30, true, "R34"));
		entries.add(new MatrixCell(28, 31, true, "R34"));
		entries.add(new MatrixCell(28, 32, true, "R34"));
		entries.add(new MatrixCell(28, 33, true, "R34"));
		return entries;
	}
}