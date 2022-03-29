
/*
Time complexity: O(1) for both the next and hasNext method, O(N+L) is for the constructor method where N is the
number of elements and L is the number of nested lists
Space Complexity: O(N+D) N is the number of elements, and D is the depth of the nested list
Approach:
After discussed in the class
 */
public class FlattenNestedListIterator implements Iterator<Integer> {
    List<Integer> list = new ArrayList<>();
    int index = 0;

    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        loadList(nestedList);
    }

    private void loadList(List<NestedInteger> nestedList) {
        for(int i=0;i<nestedList.size();i++)
            if(nestedList.get(i).isInteger())
                list.add(nestedList.get(i).getInteger());
            else
                loadList(nestedList.get(i).getList());
    }

    public Integer next() {
        Integer val = list.get(index);
        index++;
        return val;
    }

    public boolean hasNext() {
        return index < list.size();
    }
}