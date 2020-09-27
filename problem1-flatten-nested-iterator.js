///**
// * // This is the interface that allows for creating nested lists.
//* // You should not implement it, or speculate about its implementation
//* function NestedInteger() {
//*
//*     Return true if this NestedInteger holds a single integer, rather than a nested list.
//*     @return {boolean}
//*     this.isInteger = function() {
//*         ...
//*     };
//*
//*     Return the single integer that this NestedInteger holds, if it holds a single integer
//*     Return null if this NestedInteger holds a nested list
//*     @return {integer}
//*     this.getInteger = function() {
//*         ...
//*     };
//*
//*     Return the nested list that this NestedInteger holds, if it holds a nested list
//*     Return null if this NestedInteger holds a single integer
//*     @return {NestedInteger[]}
//*     this.getList = function() {
//*         ...
//*     };
//* };
//*/
//  /**
// * @constructor
// * @param {NestedInteger[]} nestedList
// */
var NestedIterator;

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
  var i, j, list, ref, results;
  if (this.nestedList.isInteger()) {
    return this.stack.push(this.nestedList.getInteger());
  } else {
    list = this.nestedList.getList();
    results = [];
    for (i = j = 0, ref = list.size; (0 <= ref ? j < ref : j > ref); i = 0 <= ref ? ++j : --j) {
      results.push(this.stack.push(list[i]));
    }
    return results;
  }
};

///**
// * @this NestedIterator
// * @returns {boolean}
// */
NestedIterator.prototype.hasNext = function() {
  return this.stack.length > 1;
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

//# sourceMappingURL=problem1-flatten-nested-iterator.js.map
