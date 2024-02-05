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

 // TC: O(1)
 // SC: O(n)
public class NestedIterator implements Iterator<Integer> {
    List<Integer> li;
    int idx;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.li = new ArrayList<>();
        helper(nestedList);
    }
    private void helper(List<NestedInteger> nestedList){
        for(NestedInteger el : nestedList){
            if(el.isInteger()){
                li.add(el.getInteger());
            }else{
                helper(el.getList());
            }
        }
    }
    @Override
    public Integer next() {
        Integer re = li.get(idx);
        idx++;
        return re;
    }

    @Override
    public boolean hasNext() {
        return idx != li.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */