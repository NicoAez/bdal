package lab_five;

import utility.FileToText;

public class Suffix {
    public static void main(String[] args) {
        var t1 = FileToText.readFile("Mann_Buddenbrooks.txt");
        var t2 = "mississippi";
        var t3 = FileToText.readFile("TestText.txt");

        var a = new SuffixArray(t3);

        System.out.println(a.averageLcp());
        System.out.println(a.maximumLcp());
        a.printSupermaximalRepeats();
    }
}
