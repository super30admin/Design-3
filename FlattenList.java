public class NestedIterator implements Iterator<Integer> {
   NestedInteger nextInt;
   Stack<Iterator<NestedInteger>> stack;
   public NestedIterator(List<NestedInteger> nestedList) {
    stack = new Stack<Iterator<NestedInteger>>();
    stack.push(nestedList.iterator());
   }

   @Override
   public Integer next() {
 return nextInt != null ? nextInt.getInteger() : null; 
   }



   @Override
   public boolean hasNext() {
       while (!stack.isEmpty()) {
           if (!stack.peek().hasNext()) stack.pop();
           else if ((nextInt = stack.peek().next()).isInteger()) return true;
           else stack.push(nextInt.getList().iterator());
       }



       return false;
   }
}
