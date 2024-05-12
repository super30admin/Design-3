package Design-3;
// TC: O(1)
// TC: O(1)
public class NestedIterator {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextElement;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!st.empty()) {
            if (!st.peek().hasNext()) {
                st.pop();
            } else {
                nextElement = st.peek().next();
                if (nextElement.isInteger()) {
                    return true;
                } else {

                    st.push(nextElement.getList().iterator());
                }
            }
        }
        return false;
    }
}
