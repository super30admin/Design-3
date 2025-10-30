// Problem: Flatten Nested List Iterator
//  Time Complexity : Amortized O(1)
//  Space Complexity : O(d) for max depth of stack size
// Did this code successfully run on Leetcode : Yes
//  Any problem you faced while coding this : No

// Your code here along with comments explaining your approach in three
// sentences only
// 1. We keep two stacks of vector of nestedInteger of type iterator begins,
// ends , and we keep track of nextEl using nextEl
// 2. Once hasNext() is called, we check is begins is not empty, if not then we
// check if the top element has not reached it's end, if  yes then pop from both
// the stack
// 3. Else, we top of the stack is integer we increment our iterator and set our
// nextel to the value and if it is a list, we push the list back to the stack
// 4. this is lazy evaluation by keeping track on where we are in the nested
// list
#include <bits/stdc++.h>
using namespace std;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *   public:
 *     // Return true if this NestedInteger holds a single integer, rather than
 * a nested list. bool isInteger() const;
 *
 *     // Return the single integer that this NestedInteger holds, if it holds a
 * single integer
 *     // The result is undefined if this NestedInteger holds a nested list
 *     int getInteger() const;
 *
 *     // Return the nested list that this NestedInteger holds, if it holds a
 * nested list
 *     // The result is undefined if this NestedInteger holds a single integer
 *     const vector<NestedInteger> &getList() const;
 * };
 */

class NestedIterator {
public:
  NestedInteger nextEl;
  stack<vector<NestedInteger>::iterator> begins, ends;
  NestedIterator(vector<NestedInteger> &nestedList) {
    begins.push(nestedList.begin());
    ends.push(nestedList.end());
  }

  int next() { return nextEl.getInteger(); }

  bool hasNext() {
    while (!begins.empty()) {
      // pop if top iterator has exhausted
      if (begins.top() == ends.top()) {
        begins.pop();
        ends.pop();
      } else {
        auto &it = begins.top();
        if (it->isInteger()) {
          nextEl = *it;
          ++it;
          return true;
        } else {
          auto &lst = it->getList();
          ++it;
          begins.push(lst.begin());
          ends.push(lst.end());
        }
      }
    }
    return false;
  }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */
