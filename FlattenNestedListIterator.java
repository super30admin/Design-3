// Time Complexity : O(M) worst case, O(1) average case for next(), where M is max depth of list, N is number of elements in the list  
// Space Complexity : O(N+M) for stack space   
// Did this code successfully run on Leetcode : yes 
// Any problem you faced while coding this : [[],[3]]
//handling the case where elements could be empty

// Your code here along with comments explaining your approach
// maintain a flattened list, initially call flatten on 1st element of the nestedList
// if empty call on next element till end of nestedList, keep track using iterator_idx
// flattenedList contains next items to pop, we use a linkedlist for O(1) on removal from head
// at then end when flattenedList is empty and iterator_idx is at the end of nestedList we can say it is empty

public class NestedIterator implements Iterator<Integer> {
    
    Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<NestedInteger>();
        
        for(int i=nestedList.size()-1; i>=0; i--){
            stack.push(nestedList.get(i));
        }
        prepareStack();
    }

    @Override
    public Integer next() {
        NestedInteger next = stack.pop();
        prepareStack();
        return next.getInteger();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    private void prepareStack(){
        while(!stack.isEmpty() && !stack.peek().isInteger()){
            List<NestedInteger> list = stack.pop().getList();
            
            for(int i=list.size()-1; i>=0; i--){
                stack.push(list.get(i));
            }
        }
    }
}

public class NestedIterator implements Iterator<Integer> {
    
    List<Integer> flattenedList = new LinkedList<Integer>();
    int iterator_idx = 0;
    List<NestedInteger> nestedList;
    
    
    private void helper(NestedInteger e){
        if(e.isInteger()){
            flattenedList.add(e.getInteger());
        }
        else{
            List<NestedInteger> list = e.getList();
            
            for(int i=0; i<list.size(); i++){
                helper(list.get(i));   
            }
        }
    }
    
    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        addElements();
    }

    private void addElements(){
        while(flattenedList.size()==0 && iterator_idx<nestedList.size()){            
            helper(nestedList.get(iterator_idx));
            iterator_idx++;
        }
    }
    
    @Override
    public Integer next() {
        int next = flattenedList.remove(0);
        if(flattenedList.size()==0) addElements(); 
        return next;
    }

    @Override
    public boolean hasNext() {
        return flattenedList.size()>0;
    }
}
