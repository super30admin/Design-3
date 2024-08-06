//Time = O(n)
//Space = O(1)

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    // A list to store all integers in the flattened nested list
    private List<Integer> flattenedList;
    // An index to keep track of the current position in the flattened list
    private int index;

    public NestedIterator(List<NestedInteger> nestedList) {
        flattenedList = new ArrayList<>();
        // Call the flatten method to flatten the nested list
        flatten(nestedList);
        // Initialize the index to 0
        index = 0;
    }

    // Returns the next integer in the flattened list and increments the index
    @Override
    public Integer next() {
        return flattenedList.get(index++);
    }

    // Returns true if there are more integers in the flattened list, false otherwise
    @Override
    public boolean hasNext() {
        return index < flattenedList.size();
    }

    private void flatten(List<NestedInteger> nestedList) {
        for (NestedInteger ni : nestedList) {
            // If the current element is an integer, add it to the flattened list
            if (ni.isInteger()) {
                flattenedList.add(ni.getInteger());
            } else {
                // Otherwise, it is a nested list, so recursively call the flatten method on it
                flatten(ni.getList());
            }
        }
    }

}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
