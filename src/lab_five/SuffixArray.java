package lab_five;

import java.util.Arrays;
import java.util.Collection;
import java.util.TreeMap;

public class SuffixArray {
    private SuffixArrayElement[] suffixArray;
    private String text;

    SuffixArray(String text) {
        // format text
        this.text = text.toLowerCase().replaceAll("\n", " ").concat("$");
        System.out.println(this.text.length());

        // create array
        suffixArray = new SuffixArrayElement[this.text.length()];

        // fill array
        System.out.println("Filling array...");
        for(int i = 0; i < this.text.length(); i++)
            suffixArray[i] = new SuffixArrayElement(i, this.text.charAt(i-1 == -1? 0 : i-1));

        // sort Array
        System.out.println("Sorting array...");
        Arrays.sort(suffixArray, (a, b) -> this.text.substring(a.position).compareTo(this.text.substring(b.position)));

        // create lcp
        System.out.println("Computing lcp...");
        for(int i = 0; i < suffixArray.length; i++) {
            if(i == 0) {
                suffixArray[i].lcp = 0;
                continue;
            }

            var j = 0;
            while(this.text.substring(suffixArray[i].position).charAt(j)
                    == this.text.substring(suffixArray[i-1].position).charAt(j)) {
                j++;
            }

            suffixArray[i].lcp = j;
        }
    }

    double averageLcp() {
        var a = 0;

        for(int i = 0; i < suffixArray.length; i++)
            a += suffixArray[i].lcp;

        return (double) a / suffixArray.length;
    }

    int maximumLcp() {
        var a = 0;

        for(int i = 0; i < suffixArray.length; i++)
            a = Math.max(suffixArray[i].lcp, a);

        return a;
    }

    void printSupermaximalRepeats() {
        for(int i = 0; i < suffixArray.length; i++) {
            var prevLcp = i-1 <= -1? 0 : suffixArray[i-1].lcp;
            var nextLcp = i+1 >= suffixArray.length? 0 : suffixArray[i+1].lcp;
            var lcp = suffixArray[i].lcp;
            var prevDelimeter = i-1 <= -1? '$' : suffixArray[i-1].delimeter;
            var delimeter = suffixArray[i].delimeter;

            if(prevLcp < lcp && lcp > nextLcp && delimeter != prevDelimeter) {
                System.out.println(this.text.substring(
                        suffixArray[i].position, suffixArray[i].position + suffixArray[i].lcp));
            }
        }
    }

    /*
        Implementierung speichert für jeden suffix den gesamten String, was für große Texte sehr viel Speicher
        benötigt und dafür eben für große Texte ungeeignet
     */
    public static int[] buildSuffixArrayUrghs(String text) {
        int[] sa = new int[text.length()];
        TreeMap<String, Integer> sortedSuffixes = new TreeMap<String, Integer>();
        for (int i=0; i<text.length(); i++)
            sortedSuffixes.put(text.substring(i, text.length()-1), i+1);
        Collection<Integer> positions = sortedSuffixes.values();
        int j = 0;
        for (Integer pos: positions)
            sa[j++] = pos;
        return sa;
    }
}
