public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        flattenList(nestedList);
    }
    
    private void flattenList(List<NestedInteger> nestedList){
        
        for(NestedInteger el : nestedList)
        {
            if(el.isInteger())
            {
                q.add(el.getInteger());
            }
            else
            {
                flattenList(el.getList());
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