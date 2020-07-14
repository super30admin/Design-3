//Queue
public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> q=new LinkedList<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        fillQueue(nestedList);
    }
    
    public void fillQueue(List<NestedInteger> nestedList)
    {
        for(NestedInteger n:nestedList)
        {
            if(n.isInteger())
                q.add(n.getInteger());
            else
                fillQueue(n.getList());
                
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
