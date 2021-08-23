// Use recursion to add elements in a queue

public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> q;

    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        dfs(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }

    private void dfs(List<NestedInteger> nestedList) {
        for(NestedInteger i : nestedList) {
            if(i.isInteger()) {
                q.add(i.getInteger());
            } else {
                dfs(i.getList());
            }
        }
    }
}