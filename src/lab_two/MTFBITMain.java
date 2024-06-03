package lab_two;

import utility.FileToText;
import java.util.ArrayList;

public class MTFBITMain {
    public static void main(String[] args) {
        String requests = FileToText.readFile("Requests_Toy.txt");
        //String requests = FileToText.readFile("Requests_Exp.txt");
        //String requests = FileToText.readFile("Requests_TM.txt");

        var fcl = FrequencyCounter.getFrequencies(requests);

        // StaticList
        var sl = new StaticList(fcl);

        // MFT
        var mtf = new MoveToFront(fcl, requests);

        // BIT
        var bit = new Bit(fcl, requests);

        // difference
        var diff = 100 * ((bit.observedRandomCosts - mtf.observedCost) / mtf.observedCost);
        System.out.println("diff random mtf/bit: " + diff + "%");
    }
}
