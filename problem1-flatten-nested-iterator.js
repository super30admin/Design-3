//https://leetcode.com/problems/flatten-nested-list-iterator/
//// Time Complexity :
//    next(): O(N) could potentially be a nested array to depth N at first element
//    hasNext(): O(1) just checks if stack is empty
//// Space Complexity : O(N) where N is max recursive depth at any 1 element in input
//// Did this code successfully run on Leetcode : no
//    (I printed output and it was correct in leetcode, not sure why it isn't working)
//// Any problem you faced while coding this :

//   leetcode was annoying, directions for this problem unclear

//// Your code here along with comments explaining your approach

// Have a queue
// Push one element from input into the queue
// if element is an integer just push that
// if it is a list, push each individual element
// everytime you next(), return queue front but also
//    grab another element from input
var NestedIterator, a, i, mn;

NestedIterator = function(nestedList) {
  var ni;
  ni = Object.assign(Object.create(NestedIterator.prototype), {
    stack: [],
    nestedList: nestedList
  });
  ni.pushOne();
  return ni;
};

NestedIterator.prototype.pushOne = function() {
  var element, i, j, ref, results;
  element = this.nestedList.shift();
  if (element == null) {
    return;
  }
  if (!Array.isArray(element)) {
    return this.stack.push(element);
  } else {
    results = [];
    for (i = j = 0, ref = element.length; (0 <= ref ? j < ref : j > ref); i = 0 <= ref ? ++j : --j) {
      results.push(this.stack.push(element[i]));
    }
    return results;
  }
};

///**
// * @this NestedIterator
// * @returns {boolean}
// */
NestedIterator.prototype.hasNext = function() {
  return this.stack.length > 0;
};

///**
// * @this NestedIterator
// * @returns {integer}
// */
NestedIterator.prototype.next = function() {
  var retval;
  retval = this.stack.shift();
  if (this.stack.length === 0) {
    this.pushOne();
  }
  return retval;
};

///**
// * Your NestedIterator will be called like this:
// * var i = new NestedIterator(nestedList), a = [];
// * while (i.hasNext()) a.push(i.next());
//*/
mn = NestedIterator([[1, 1], 2, [1, 1]]);

while (mn.hasNext()) {
  console.log(mn.next());
}

i = new NestedIterator([[1, 1], 2, [1, 1]]);

a = [];

while (i.hasNext()) {
  a.push(i.next());
}

a;

//# sourceMappingURL=problem1-flatten-nested-iterator.js.map
