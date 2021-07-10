// Time Complexity : O(N)
// Space Complexity : O(N), where N is the number of elements.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

/*
1. If we find a bracket then send it to a recursive function
2. Id it's a normal number add to global array
*/

// Your code here along with comments explaining your approach
public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        dfs(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
    
    public void dfs(List<NestedInteger> nestedList) {
        for(NestedInteger ele: nestedList) {
            if(ele.isInteger()) {
                q.add(ele.getInteger());
            } else {
                dfs(ele.getList());
            }
        }
    }
}
