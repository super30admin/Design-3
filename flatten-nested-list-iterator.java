// Time Complexity : O(n) n : size of input 
// Space Complexity : O(n) 
public class NestedIterator implements Iterator<Integer> {

    List<Integer> list;
    int s = 0;
    public NestedIterator(List<NestedInteger> nestedList) {
        list = new ArrayList<>();
        helper(nestedList);
    }
    public void helper(List<NestedInteger> nestedList) {
        for(int i = 0; i< nestedList.size();i++){
            if(nestedList.get(i).isInteger()){
                list.add(nestedList.get(i).getInteger());
            } else {
                helper(nestedList.get(i).getList());
            }
        }
    }
    @Override
    public Integer next() {
        return list.get(s++);
    }

    @Override
    public boolean hasNext() {
        return s < list.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */