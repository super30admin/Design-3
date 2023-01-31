//Time Complexity: O(1) //Average case
//Space Complexity: O(1) average case

/*
 * In this approach we design an iterator. We take a stack and push the nested list as the iterator first. Then we loop till the stack is empty
 * We check the top element's next element is an integer or not, if yes we return true in the has next. If it is a list we push it to the stack and check its
 * elements. If the list is completed and has no more elements we just pop from the stack. we maintain a variable to keep the prev element. At each place we return its integer 
 * in next function.
 * 

*/
//Iterator solution
public class NestedIterator implements Iterator<Integer> {
    private Stack<Iterator<NestedInteger>> st;
    NestedInteger nextel;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }
    
    @Override
    public Integer next() {
        return nextel.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }else if((nextel = st.peek().next()).isInteger()){
                return true;
            }else{
                st.push(nextel.getList().iterator());
            }
        }
        return false;
    }
}

//invalid solution
public class NestedIterator implements Iterator<Integer> {
    private Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.q = new LinkedList<>();
        dfs(nestedList);
    }
    private void dfs(List<NestedInteger> nestedList){
        for(NestedInteger el : nestedList){
            if(el.isInteger()){
                q.add(el.getInteger());
            }
            else{
                dfs(el.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
}