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

    private Stack<Iterator<NestedInteger>> stack;
    private Integer nextEle;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new Stack<>();
        this.stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return this.nextEle;
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            Iterator<NestedInteger> iterator = stack.peek();
            if(!iterator.hasNext()){
                stack.pop();
            }else{
                NestedInteger ni = iterator.next();
                if(ni.isInteger()){
                    this.nextEle = ni.getInteger();
                    return true;
                }else{
                    this.stack.push(ni.getList().iterator());
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
