//Time complexity: O(N) where N is the number of elements in the given input
//SpaceCOmplexity: O(N)
//Attempted on leetcode and was accepted the first time 
public class NestedIterator implements Iterator<Integer> {
    //Initialise a queue
    private Queue<Integer> queue = new LinkedList();
    public NestedIterator(List<NestedInteger> nestedList) {
        //call a helper functipn to flatten the given input
        helper(nestedList);
    }
    
    //Helper function that takes the list as input and flattens it to a single list
    private void helper(List<NestedInteger> List)
    {
        //if the given list is empty then just return
        if(List==null)
            return;
        //Iterate over the whole list 
        for(NestedInteger l:List)
        {
            //check if the selected value is an integer 
            if(l.isInteger())
            {
                //get the value and add it to the queue
                queue.offer(l.getInteger());
            }
            //otherwise call the helper function recursively to get the list  from the given input
            else
            {
                helper(l.getList());
            }
        }
    }
    //next function to get the next element in the queue
    @Override
    public Integer next() {
        //check  if the queue holds anymore elements by calling hasNext function
        if(hasNext())
        {
            //If so pop the element from the queue and return
            return queue.poll();
        }
        //otherwise return -1
        else
            return -1;
    }

    //hasNext function to check if the queue holds anymore values in it
    @Override
    public boolean hasNext() {
        //Chekc if the queue is empty
        if(!queue.isEmpty())
        {
            //If not return true indicating it has next element
            return true;
        }
        //Otherwise return false
        else
        {
            return false;
        }
    }
}
