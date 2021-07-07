// Time Complexity - O(n) where n is the number of integers in the iterator
// Space Complexity - O(n) the space used by the queue to flatten the iterator


public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> q = new LinkedList<>();  // used q as data structure so the next elements can be found in first in first out manner
    public NestedIterator(List<NestedInteger> nestedList) {
        fillqueue(nestedList);    
    }
    private void fillqueue(List<NestedInteger> nestedList){
        for(NestedInteger list : nestedList){
            if(list.isInteger()){
                q.add(list.getInteger());
            }
            else{
                 fillqueue(list.getList());    // DFS approach since the list could be a nested list so we call fillqueue recursively
            }
        }    
    }
    @Override
    public Integer next() {
        return q.poll();    
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
}
