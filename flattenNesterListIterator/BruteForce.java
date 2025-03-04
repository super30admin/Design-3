package flattenNesterListIterator;

public class BruteForce {
}

/**
 * TC: O(n)
 * SC:  O(n)
 */
class NestedIterator implements Iterator<Integer> {

    // solution 1 not an iterator

    private List<Integer> li;
    private int idx;

    public NestedIterator(List<NestedInteger> nestedList) {

        this.li = new ArrayList();
        helper(nestedList);
    }

    private void helper(List<NestedInteger> nestedList) {

        for(int i = 0; i < nestedList.size(); i++) {

            NestedInteger curr = nestedList.get(i);

            if(curr.isInteger()) {
                li.add(curr.getInteger());
            } else {
                helper(curr.getList());
            }
        }
    }

    @Override
    public Integer next() {
        Integer re = li.get(idx);
        idx++;
        return re;
    }

    @Override
    public boolean hasNext() {
        // check if not gone to the end of the queue
        return idx != li.size();
    }
}