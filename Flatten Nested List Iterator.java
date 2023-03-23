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
 import java.util.NoSuchElementException;
public class NestedIterator implements Iterator<Integer> {
    
    List<Integer> flattened = new LinkedList<Integer>();
    List<NestedInteger> input_list = new LinkedList<>();
    int curr_index = 0;
    public NestedIterator(List<NestedInteger> nestedList) {
        //I am going to try the recursive approach.
        
        dfs(nestedList);
    }
    
    public void dfs(List<NestedInteger> input){
        
        
        
       //Use an enhanced for loop, looks strange it is within the class.

       for(NestedInteger number: input){
           if(number.isInteger()){
               flattened.add(number.getInteger());
           }
           else{
               dfs(number.getList());
           }
       }
        
        
    }

    @Override
    public Integer next() {
        if(hasNext()){
            //curr_index ++;
            return flattened.get(curr_index++);
        }
        else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean hasNext() {
        if(curr_index < input_list.size()){
            return true;
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */