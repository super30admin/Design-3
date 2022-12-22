//TC: O(n)
//SC: O(n)

public class NestedIterator implements Iterator<Integer> {
    List<Integer> list;
    int pointer = 0;
    public NestedIterator(List<NestedInteger> nestedList) {
        list = new ArrayList<Integer>();
        for(int i=0;i<nestedList.size();i++) {
            NestedInteger curr = nestedList.get(i);
            if (curr.isInteger()) list.add(curr.getInteger());
            else {
                NestedIterator deepList = new NestedIterator(curr.getList());
                while(deepList.hasNext()) list.add(deepList.next());
            }
        }
    }

    @Override
    public Integer next() {
        int send = list.get(pointer);
        pointer++;
        return send;
    }

    @Override
    public boolean hasNext() {
        return pointer < list.size();
    }
}