package flattenNesterListIterator;

public class IteratorApproach {
}

//Time Complexity (TC): O(1) next, hasNext
//next(): O(1)
//hasNext(): O(n), where n is the total number of integers in all nested lists (since each element is processed once).
//Space Complexity (SC): O(d), where d is the maximum depth of the nested list (due to the stack storing iterators).

public class NestedIterator implements Iterator<Integer> {

    NestedInteger nextEl;
    Stack<Iterator<NestedInteger>> st;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack();
        this.st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()) {

            if(!st.peek().hasNext()) {
                st.pop();
            } else if((nextEl = st.peek().next()).isInteger()) {
                return true;
            } else {
                st.push(nextEl.getList().iterator());
            }
        }

        return false;
    }
}