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
    
    List<NestedInteger> nestedList; //nested list
    Iterator<NestedInteger> itr;    //iterator of type NestedInteger as it is a interface
    Stack<Iterator<NestedInteger>> stack; //using stack to process the nested list 
    
    NestedInteger num;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = new ArrayList<>();
        this.itr = nestedList.iterator(); //calling iterator on nestedList to point index just before the first element
        
        this.stack = new Stack();
        stack.push(itr); //push this iterator in stack
    }

    @Override
    public Integer next() {
        //just return the nextelement if its a integer
        return num.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            
            //if we reach the end of current iterator
            if(!stack.peek().hasNext()){
                stack.pop();
            }
            //if stack has some elements
            else{
                num = stack.peek().next();         
                if(num.isInteger()){
                    return true;
                }
                else{
                     //else initialise new iterator for the list and push it in stack and continue
                    List<NestedInteger> newlist = num.getList();
                    Iterator<NestedInteger> itr = newlist.iterator();
                    stack.push(itr);
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