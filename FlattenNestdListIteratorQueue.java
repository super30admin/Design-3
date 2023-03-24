public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> q = new LinkedList<Integer>();

    public NestedIterator(List<NestedInteger> nestedList) {
        fillQueue(nestedList);
    }

    private void fillQueue(List<NestedInteger> nestList) {
        if(nestList == null) {
            return;
        }
        for(NestedInteger li : nestList) {
            if(li.isInteger()) {
                q.add(li.getInteger());
            } else {
                fillQueue(li.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        if(q.isEmpty()) {
            return false;
        }
        return true;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */