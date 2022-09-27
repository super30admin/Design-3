// Brute force approach: We use dfs to get all integers in our result in order. If in our dfs, we encounter an integer, we simply add it to the result. If it is not an integer and a list instead, we run dfs on that list. The problem with this approach is that it is creating the result all at once. An iterator should just care about the next element and should go one by one. If there are any chnages to the list, this iterator will not be able to handle that which defeats the purpose of having an iterator

// Space complexity: O(N + D). The N is for the result array list and D is for the recursion stack where D is the maximum nesting depth


public class NestedIterator implements Iterator<Integer> {
    List<Integer> result;
    int index = 0;
    public NestedIterator(List<NestedInteger> nestedList) { // Time: O(N + L) due to time taken to build the result array list
        result = new ArrayList<>();
        dfs(nestedList);
    }

    @Override
    public Integer next() { // Time: O(1)
        return result.get(index++);
    }

    @Override
    public boolean hasNext() { // Time: O(1)
        return index != result.size();
    }
    
    private void dfs(List<NestedInteger> nestedList) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                result.add(ni.getInteger());
            }
            else {
                dfs(ni.getList());
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */