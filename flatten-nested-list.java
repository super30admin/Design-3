public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> que;
    public NestedIterator(List<NestedInteger> nestedList) {
        que = new LinkedList<>();
        for(NestedInteger nest : nestedList) {
            if(nest.isInteger()) {
                que.offer(nest.getInteger());
            } else {
                help(nest.getList());
            }
        }
    }

    private void help(List<NestedInteger> n) {
        for(NestedInteger nest : n) {
            if(nest.isInteger()) {
                que.offer(nest.getInteger());
            } else {
                help(nest.getList());
            }
        }
    }

    @Override
    public Integer next() {
        if(hasNext()) return que.poll();
        else return null;
    }

    @Override
    public boolean hasNext() {
        return que.isEmpty() ? false : true;
    }
}