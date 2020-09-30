//Time Complexity: O(n+l) for recursion O(1) for hasnext and next
//Space complexity: O(l+maxNodesinAList) - for recursive stack
//Did run on leetcode: yes

public class NestedIterator implements Iterator<Integer> {
    
    Queue<Integer> queue;
    
    private void recurList(List<NestedInteger> nestedList){
        if(nestedList == null)
            return;
        
        for(NestedInteger i:nestedList){
            if(i.isInteger())
                queue.add(i.getInteger());
            else{
                recurList(i.getList());
            }
                
        }
    }

    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<>();
        recurList(nestedList);
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
