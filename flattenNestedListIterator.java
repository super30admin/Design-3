//using queue. Just iterating through the entire list given. 'wrong' approach. as we are'nt making use of next and hasNext
// 'controlled recursion' --> incase of flatten bst iterator

public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        flattenList(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
    
    private void flattenList(List<NestedInteger> nestedList) {
        for(NestedInteger el : nestedList) {
            if(el.isInteger()) {
                q.add(el.getInteger());
            }
            else {
                flattenList(el.getList());
            }
        }
    }
}