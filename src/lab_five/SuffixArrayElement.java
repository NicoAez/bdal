package lab_five;

public class SuffixArrayElement {
    public int lcp;
    public int position;
    public char delimeter;

    SuffixArrayElement(int position, char delimeter) {
        this.position = position;
        this.delimeter = delimeter;
    }
}
