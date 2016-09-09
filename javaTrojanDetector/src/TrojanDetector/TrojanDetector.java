package TrojanDetector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import UtilityClasses.ModifiedFrame;
import edu.byu.ece.rapidSmith.bitstreamTools.examples.BitstreamDiff;

public class TrojanDetector {
	BitstreamDiff diff; 
	
	public void  performDetection(File goldenBitFile, File targetBitFile){
		String[] args = {"-i", goldenBitFile.getAbsolutePath(), "-c", targetBitFile.getAbsolutePath()};
		BitstreamDiff diff = new BitstreamDiff();
		ArrayList<ModifiedFrame> differences = diff.findDifferences(args);
		System.out.println("Here we are mother fuckers");
		for(ModifiedFrame t : differences){
			System.out.println(t.printModifiedFrame());
		}
	}
}
