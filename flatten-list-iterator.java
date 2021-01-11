// Time - O(1)
// Space - O(N)

public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        flatten(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
    
    private void flatten(List<NestedInteger> nestedList) {
        for(NestedInteger i : nestedList) {
            if(i.isInteger()) {
                q.add(i.getInteger());
            }
            else {
                flatten(i.getList());
            }
        }
    }
}
