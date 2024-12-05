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
// Approach : Brute force (eagerly evaluation) - unpack the nested list within the constructor itself using recursion
// Time : O(1)
// Space : O(1)
public class NestedIterator implements Iterator<Integer> {
    List<Integer> li;
    int idx;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.li = new ArrayList<>();
        this.idx = 0;
        dfs(nestedList);
    }

    @Override
    public Integer next() { // O(1)
        return li.get(idx++);
    }

    @Override
    public boolean hasNext() { // O(1)
        return idx < li.size();
    }

    public void dfs(List<NestedInteger> nestedList){
        // for loop based recursion
        for(NestedInteger ne: nestedList){
            if(ne.isInteger()){
                li.add(ne.getInteger());
            } else{
                dfs(ne.getList());
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

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
// Approach : Using own stack. Utilizing inbuild next and hasNext of the list iterator
// Time : O(1) // Ammortized Time. But worst case would be [[[[[[6]]]]]] then it would be depth of the list
// Space : O(1)  // Ammortized Time. But worst case would be [[[[[6]]]]] then it would be depth of the list.
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stk;
    NestedInteger nextElement;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.stk = new Stack<>();
        stk.push(nestedList.iterator());
    }

    @Override
    public Integer next() { // O(1)
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() { // O(1)
    while(!stk.isEmpty()){
        if(!stk.peek().hasNext()){
            stk.pop();
        } else{
            nextElement = stk.peek().next();
            if(nextElement.isInteger()){
                return true;
            } else{
                stk.push(nextElement.getList().iterator());
            }
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