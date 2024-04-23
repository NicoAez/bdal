package lab_two;

import utility.FileToText;

import java.io.File;
import java.io.FileReader;

public class MTFBITMain {
    public static void main(String[] args) {
        String requests = FileToText.readFile("Requests_Toy.txt");
        //String requests = FileToText.readFile("Requests_Exp.txt");
        //String requests = FileToText.readFile("Requests_TM.txt");

        var fcl = FrequencyCounter.getFrequencies(requests);

        var sl = new StaticList(fcl);
    }
}
