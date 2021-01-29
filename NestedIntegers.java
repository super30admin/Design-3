class NestedIntegers {
  /*
  T(N) : O(N) | time taken to build a list of integers
  S(N) : O(N) | spacet taken to store all Integer numbers
  Leetcode : YES

  */
public class NestedIterator implements Iterator<Integer> {

    List<Integer> integers = new ArrayList<>();
    int position = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        flattern(nestedList);
    }

    // recursively build a integers list
    private void flattern(List<NestedInteger> nestedList){
         for(NestedInteger nestedInt : nestedList){
            if(nestedInt.isInteger()){
                integers.add(nestedInt.getInteger());
            }else{
                flattern(nestedInt.getList());
            }
        }
    }

    // return curr position and increment iterator to next position
    @Override
    public Integer next() {
        if(!hasNext()) return null;

        Integer result =  integers.get(position);
        position++;
        return result;
    }

    // based on current position return if list has more items or not
    @Override
    public boolean hasNext() {
        return position < integers.size();
    }
}
}
