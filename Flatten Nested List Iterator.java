public class NestedIterator implements Iterator<Integer> {
  Queue<Integer> queue;

  public NestedIterator(List<NestedInteger> nestedList) {
    queue = new ArrayDeque<>();
    flatten(nestedList);
  }

  public void flatten(List<NestedInteger> nestedList) {
    for (NestedInteger i : nestedList) {
      if (i.isInteger()) {
        queue.add(i.getInteger());
      } else {
        flatten(i.getList());
      }
    }
  }

  @Override
  public Integer next() {
    return queue.poll();
  }

@Override
public boolean hasNext() {
    return queue.size()>0;
}