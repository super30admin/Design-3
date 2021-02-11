
//Time Complexity: Constructor O(1), makeStackTopAnInteger(), next(), hasNext() = O(1)
//Space Complexity: O(n), where n is the maximum neseting depth (max num of lists inside each other)

import java.util.NoSuchElementException;

public class NestedIterator implements Iterator<Integer> {
    
    private Deque<ListIterator<NestedInteger>> stackOfIterators = new ArrayDeque();
    private Integer peeked = null;

    public NestedIterator(List<NestedInteger> nestedList) {
        stackOfIterators.addFirst(nestedList.listIterator());
    }

    private void setPeeked() {
        
        if (peeked != null) return;
        
        while (!stackOfIterators.isEmpty()) {
    
            if (!stackOfIterators.peekFirst().hasNext()) {
                stackOfIterators.removeFirst();
                continue;
            }

            NestedInteger next = stackOfIterators.peekFirst().next();
            
            if (next.isInteger()) {
                peeked = next.getInteger();
                return;
            }
            
            stackOfIterators.addFirst(next.getList().listIterator());
        }        
    }
    

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        Integer result = peeked;
        peeked = null;
        return result;
    }

    @Override
    public boolean hasNext() {
        setPeeked();
        return peeked != null;
    }
}