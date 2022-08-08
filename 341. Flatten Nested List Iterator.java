// Time Complexity : O(No. of Nested List)
// Space Complexity : O(No. of levels of Nesting)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

//We will add the whole nestedinteger in stack and then iterate over each, if it is nested iterator then will add to to stack and then iterate over the peek of stack and then add elements to flatten the nested list

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> s;
    NestedInteger nextEl;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        s.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!s.isEmpty()){
            if(!s.peek().hasNext()){
                s.pop();
            }
            else if((nextEl = s.peek().next()).isInteger()){  
                return true;
            }
            else{
                s.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}