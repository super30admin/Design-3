//Space Complexity - O(N+L) - N size of nestedList, L - no of lists inside nestedList - stack size
//Time Complexity - next() - O(1)
//Time Complexity - hasNext() - O(L/N)
public class NestedIterator implements Iterator<Integer> {

  Stack<Iterator<NestedInteger>> stack;
  NestedInteger nextElement;

  public NestedIterator(List<NestedInteger> nestedList) {
    stack = new Stack<>();
    stack.push(nestedList.iterator());
  }

  @Override
  public Integer next() {
    return nextElement.getInteger();
  }

  @Override
  public boolean hasNext() {

    while(!stack.isEmpty()){
      if(!stack.peek().hasNext()){
        stack.pop();
      }
      else if((nextElement = stack.peek().next()).isInteger()){
        return true;
      }
      else{
        stack.push(nextElement.getList().iterator());
      }
    }
    return false;
  }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */



public class NestedIterator implements Iterator<Integer> {

  Queue<Integer> queue;
  public NestedIterator(List<NestedInteger> nestedList) {
    queue = new LinkedList<>();
    flatten(nestedList);
  }

  @Override
  public Integer next() {
    return queue.poll();
  }

  @Override
  public boolean hasNext() {
    return !queue.isEmpty();
  }

  public void flatten(List<NestedInteger> nestedList){
    for(NestedInteger element: nestedList){
      if(element.isInteger()){
        queue.add(element.getInteger());
      }
      else{
        flatten(element.getList());
      }
    }
  }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
