//tc: next() -> o(1) hasNext() -> depends on nexted lists since we are using stack and pushing based on lists
//sc: depth of nested list

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> s;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        s.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!s.isEmpty()) {
            if(!s.peek().hasNext()) {
                s.pop();
            }
            else if((nextEl = s.peek().next()).isInteger()) { // the current value is assigned to nextEl then pointer moves to next
                return true;
            }
            else { // we reach here upon encountering a list, push that to top of stack
                s.push(nextEl.getList().iterator());
            }
        }
        return false; // reach here when stack is empty
    }
}