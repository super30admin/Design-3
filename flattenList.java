
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList(); }
 *
 * TC --> O(max(depth)) SC --> O(max(depth))
 */
public class NestedIterator implements Iterator<Integer> {

    List<Integer> res;
    List<NestedInteger> nestedList;
    int idx;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.res = new ArrayList<>();
        this.nestedList = nestedList;
        this.idx = 0;
        Helper(nestedList);
        System.out.print(res);

    }

    public void Helper(List<NestedInteger> nestedList) {
        for (int i = 0; i < nestedList.size(); i++) {
            if (nestedList.get(i).isInteger()) {
                res.add(nestedList.get(i).getInteger());
            }//if
            else {
                Helper(nestedList.get(i).getList());
            }
        }//for
    }//method

    @Override
    public Integer next() {
        Integer val = res.get(idx);
        idx++;
        return val;

    }

    @Override
    public boolean hasNext() {
        if (idx < res.size()) {
            return true;
        }
        return false;

    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
