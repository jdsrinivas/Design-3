/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 * // @return true if this NestedInteger holds a single integer, rather than a
 * nested list.
 * public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a
 * single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 *
 * // @return the nested list that this NestedInteger holds, if it holds a
 * nested list
 * // Return empty list if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> s;
    NestedInteger nextEL;

    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        s.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        System.out.println("next returning " + nextEL.getInteger());
        return nextEL.getInteger();
    }

    @Override
    public boolean hasNext() {
        // [[1,1],2,[1,1]] example
        while (!s.isEmpty()) {
            if (!s.peek().hasNext()) {
                System.out.println("Pop from Stack:" + s.pop());
            } else if ((nextEL = s.peek().next()).isInteger()) {
                System.out.println(nextEL + " is Integer");
                return true;
            } else {
                System.out.println("Push to Stack:");
                s.push(nextEL.getList().iterator());
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */