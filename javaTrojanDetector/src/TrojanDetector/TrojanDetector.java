package TrojanDetector;

import java.util.ArrayList;
import java.util.List;

import UtilityClasses.ModifiedFrame;
import edu.byu.ece.rapidSmith.bitstreamTools.examples.BitstreamDiff;

public class TrojanDetector {
	BitstreamDiff diff; 
	
	public void  performDetection(){
		String[] args = {"-i", "C:\\Users\\Nick\\Desktop\\NickTop\\HomeWork\\MASc\\Virtex5\\bitFiles\\itemDefault.bit", "-c", "C:\\Users\\Nick\\Desktop\\NickTop\\HomeWork\\MASc\\Virtex5\\bitFiles\\itemMod.bit"};
		BitstreamDiff diff = new BitstreamDiff();
		ArrayList<ModifiedFrame> differences = diff.findDifferences(args);
		System.out.println("Here we are mother fuckers");
		for(ModifiedFrame t : differences){
			System.out.println(t.printModifiedFrame());
		}
	}
}
