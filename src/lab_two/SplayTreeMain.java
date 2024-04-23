package lab_two;

public class SplayTreeMain {
    public static void main(String[] args) {
        SplayTree st = new SplayTree();

        st.insert(1);
        st.insert(5);
        st.insert(9);
        st.insert(15);
        st.insert(18);
        st.insert(6);

        st.printTree();

        st.search(1);
        st.printTree();

        st.search(15);
        st.printTree();

        st.search(5);
        st.printTree();
    }
}
