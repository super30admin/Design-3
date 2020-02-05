//Time Complexity: O(n) (Maintaining auxillary data structure Queue)
//Space Complexity: O(n) (Going through all the elements of the given input)

public class NestedIterator implements Iterator<Integer> {
    //since time complexity to pop element is O(1)
    //a better data structure compared to array and stack
    Queue<Integer> que;
    public NestedIterator(List<NestedInteger> nestedList) {
        //initializing queue
        //to add elements in the queue
        this.que = new LinkedList<>();
        dfs(nestedList);
    }
    
    private void dfs(List<NestedInteger> nestedList){
        for(NestedInteger element : nestedList){
            //if is integer and not a list
            if(element.isInteger()){
                //add to the queue
                //to get the integer, we need to implement the interface
                que.add(element.getInteger());
            } else{
                //else since is a list, call dfs function recursively
                //the list is again a nested integer
                dfs(element.getList());
            }
        }
    }
    
    //first eement of the que
    @Override
    public Integer next() {
        return que.poll();
    }

    //queue is not empty
    @Override
    public boolean hasNext() {
        return !que.isEmpty();

    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */