/**
 * space complexity is total number of intgers in the nested lists.
 * instantiation takes O(n)
 * 
 * next and hasNext take O(1)
 */
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class NestedIterator implements Iterator<Integer> {
    Queue<NestedInteger> queue;

    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<>();
        push(nestedList);
    }

    @Override
    public Integer next() {
        return queue.poll().getInteger();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
    
    private void push(List<NestedInteger> list) {
        for(NestedInteger ni : list) {
            if(ni.isInteger()) {
                queue.add(ni);
            }
            else {
                push(ni.getList());
            }
        }
    }
}