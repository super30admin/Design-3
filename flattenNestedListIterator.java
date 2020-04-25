import java.util.*;

public class NestedIterator implements Iterator<Integer> {
    
    ArrayList<Integer> list;
    int curr;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = new ArrayList();
        fill(nestedList);
        curr = -1;
    }

    @Override
    public Integer next() {
        curr += 1;
        return list.get(curr);
    }

    @Override
    public boolean hasNext() {
        return curr + 1 < list.size() ? true : false;
    }
    
    public void fill(List<NestedInteger> nestedList)   
    {
        for(int i = 0; i < nestedList.size(); i++)
        {
            NestedInteger entry = nestedList.get(i);
            if(entry.isInteger())
            {
                list.add(entry.getInteger());
            }
            else
            {
                fill(entry.getList());
            }
        }
    }
}