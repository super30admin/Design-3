
//create a new linked list to pass in the list of integers and checking if it is a nested list or it is an integer only.

import java.util.LinkedList;

public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> myQ;

    public NestedIterator(List<NestedInteger> nestedList) {
        myQ = new LinkedList<>();
        addAll(nestedList);
    }
    public void addAll(List<NestedInteger> nestedList) {
        for(NestedInteger i : nestedList) {

            if(i.isInteger()) {
                myQ.offer(i.getInteger());
            }
            else {
                addAll(i.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return !myQ.isEmpty() ? myQ.poll() : -1;
    }

    @Override
    public boolean hasNext() {
        return !myQ.isEmpty();
    }
}
