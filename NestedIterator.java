// Logic-
// the dynamicity needs to be maintained.
// only way to do that is to use another iterator(native iterator) to form the basis of this iterator.
// the stack takes the iterator of nested integers- essentially the element where all the nested list element are present.
// you can unwrap this list in a while loop. 
// hasNext of nested iterator does all the work.

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stack = new Stack<>();
    NestedInteger element;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new Stack<>();
        this.stack.push(nestedList.iterator());        
    }

    @Override
    public Integer next() { // O(1)
        return element.getInteger();

    }

    @Override
    public boolean hasNext() { // O(n)
        while(!stack.isEmpty()) {
            if(!stack.peek().hasNext()) {
                stack.pop();
            } else if ((element = stack.peek().next()).isInteger()) {
                return true;                
            } else {
                stack.push(element.getList().iterator());                
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */