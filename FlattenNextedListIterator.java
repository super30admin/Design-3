//TC == ?? can you explain time and space complexity for this problem.


//Optimal Solution


public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st; // to iterate the list in stack
    NestedInteger nextElement; // to track the prev element
    
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        this.st.push(nestedList.iterator());
        
    }
 

    @Override
    public Integer next() {
       return nextElement.getInteger(); //O(1)
    }

    @Override
    public boolean hasNext() { //O(d)
       while(!st.isEmpty()){
           if(!st.peek().hasNext()){
               st.pop();
           }
           else if((nextElement = st.peek().next()).isInteger()){
               return true;
           }
           else{
               st.push(nextElement.getList().iterator());
           }
       }
        return false;
    }
}


//Normal Solution
public class NestedIterator implements Iterator<Integer> {

    List<Integer> li;
    int i;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.li = new ArrayList<>();
        dfs(nestedList);
        
    }
    private void dfs(List<NestedInteger> nestedList){
        //base
        
        //logic
        for(NestedInteger element:nestedList){
            if(element.isInteger()){
                li.add(element.getInteger());
            }
            else{
                dfs(element.getList());
            }
        }
    }

    @Override
    public Integer next() {
        int result = li.get(i);
        i++;
        return result;
    }

    @Override
    public boolean hasNext() {
        return i!= li.size();
    }
}

