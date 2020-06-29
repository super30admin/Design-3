//time complexity- O(1)

//space complexity- O(N+S) N is size of list and S the size of  recursive stack
import java.util.*;

public class FlattenNestedListIterator {
    public interface NestedInteger {
        boolean isInteger();
        Integer getInteger();
         List<NestedInteger> getList();
    }
    public static class NestedIntegerImpl implements NestedInteger {
        private Integer value;
        private List<NestedInteger> list;
        public NestedIntegerImpl(Integer value) {
            this.value = value;
            this.list = null;
        }
        public NestedIntegerImpl(List<NestedInteger> list) {
            this.list = list;
            this.value = null;
        }
        @Override
        public boolean isInteger() {
            return value != null;
        }
        @Override
        public Integer getInteger() {
            return value;
        }
        @Override
        public List<NestedInteger> getList() {
            return list != null ? list : new ArrayList<>();
        }
    }

    public class NestedIterator implements Iterator<Integer> {
        private ArrayList<Integer> li;
        int i;
        public NestedIterator(List<NestedInteger> nestedList) {
            this.li = new ArrayList<>();
            i = 0;
            dfs(nestedList);
        }

        private void dfs(List<NestedInteger> nestedList) {
            for (NestedInteger ele : nestedList) {
                if (ele.isInteger()) {
                    li.add(ele.getInteger());
                } else {
                    dfs(ele.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return li.get(i++);
        }

        @Override
        public boolean hasNext() {
            return i < li.size();
        }
    }

    public static void main(String[] args) {
        FlattenNestedListIterator it = new FlattenNestedListIterator();
        List<NestedInteger> nestedList = Arrays.asList( new NestedIntegerImpl(1),
                new NestedIntegerImpl(Arrays.asList( new NestedIntegerImpl(2),
                new NestedIntegerImpl(Arrays.asList(new NestedIntegerImpl(3),
                new NestedIntegerImpl(4))))),
                new NestedIntegerImpl(5));

        NestedIterator i = it.new NestedIterator(nestedList);
        while (i.hasNext()) {
            System.out.print(i.next() + " ");
        }
    }
}
