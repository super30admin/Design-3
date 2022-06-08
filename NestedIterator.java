//Time Complexity: O(1)
//Space Complexity: O(n)


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

interface NestedInteger {
    public boolean isInteger();
    public Integer getInteger();
    public List<NestedInteger> getList();
}

public class NestedIterator implements Iterator<Integer> {

    private List<Integer> integers = new ArrayList<Integer>();
    private int position = 0; // Pointer to next integer to return.

    public NestedIterator(List<NestedInteger> nestedList) {
        flattenList(nestedList);
    }

    // Recursively unpacks a nested list in DFS order.
    private void flattenList(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                integers.add(nestedInteger.getInteger());
            } else {
                flattenList(nestedInteger.getList());
            }
        }
    }

    @Override
    public Integer next() {
        // As per Java specs, we should throw an exception if no more ints.
        if (!hasNext()) throw new NoSuchElementException();
        // Return int at current position, and then *after*, increment position.
        return integers.get(position++);
    }

    @Override
    public boolean hasNext() {
        return position < integers.size();
    }
}