
public class NestedIterator implements Iterator<Integer> {
    Queue<Integer>q = new LinkedList();
    
    public NestedIterator(List<NestedInteger> nestedList) {
        fillQueue(nestedList);
        
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty(); 
    }
    
    public void fillQueue(List<NestedInteger> nestedList)
    {
        for(NestedInteger ni : nestedList)
        {
            if(ni.isInteger())  // If the next element is an integer, add it to the queue.
            {
                q.offer(ni.getInteger());
            }
            else
            {
                fillQueue(ni.getList()); // if the next element is a list, get the list.
            }
        }
    }
}

