//Time Complexity : O(1) for both next()and hasnext()
//Space Complexity : O(d) 
#include<iostream>
#include<vector>
#include<stack>
using namespace std;

class NestedInteger {
   public:
    bool isInteger() const;
    int getInteger() const;
    const vector<NestedInteger> &getList() const;
};
class NestedIterator {
    stack<vector<NestedInteger>::const_iterator> s1, s2;
    NestedInteger nextele;

public:
    NestedIterator(vector<NestedInteger> &nestedList) {
        s1.push(nestedList.begin());
        s2.push(nestedList.end());
    }

    int next() {
        return nextele.getInteger();
    }

    bool hasNext() {
        while (!s1.empty()) {
            if (s1.top() == s2.top()) {
                s1.pop();
                s2.pop();
            } else {
                const NestedInteger& curr = *s1.top()++;
                if (curr.isInteger()) {
                    nextele = curr;
                    return true;
                } else {
                    const vector<NestedInteger>& temp = curr.getList();
                    s1.push(temp.begin());
                    s2.push(temp.end());
                }
            }
        }
        return false;
    }
};
