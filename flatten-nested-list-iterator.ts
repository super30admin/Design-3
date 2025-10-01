/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *     If value is provided, then it holds a single integer
 *     Otherwise it holds an empty nested list
 *     constructor(value?: number) {
 *         ...
 *     };
 *
 *     Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     isInteger(): boolean {
 *         ...
 *     };
 *
 *     Return the single integer that this NestedInteger holds, if it holds a single integer
 *     Return null if this NestedInteger holds a nested list
 *     getInteger(): number | null {
 *         ...
 *     };
 *
 *     Set this NestedInteger to hold a single integer equal to value.
 *     setInteger(value: number) {
 *         ...
 *     };
 *
 *     Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
 *     add(elem: NestedInteger) {
 *         ...
 *     };
 *
 *     Return the nested list that this NestedInteger holds,
 *     or an empty list if this NestedInteger holds a single integer
 *     getList(): NestedInteger[] {
 *         ...
 *     };
 * };
 */

// Time complexity- Amortized O(1) for hasNext and next operation
// Space complexity- O(d), where d is the depth of the stack (nested list)
class NestedIterator {
	stack: NestedInteger[][];
	indexStack: number[];
	nextEl?: NestedInteger;
	constructor(nestedList: NestedInteger[]) {
		this.stack = [nestedList];
		this.indexStack = [0];
		this.nextEl = null;
	}

	hasNext(): boolean {
		while (this.stack.length > 0) {
			const currList = this.stack[this.stack.length - 1];
			const currIndex = this.indexStack[this.indexStack.length - 1];

			if (currList.length === currIndex) {
				this.stack.pop();
				this.indexStack.pop();
			} else {
				this.nextEl = currList[currIndex];
				this.indexStack[this.indexStack.length - 1]++;

				if (this.nextEl.isInteger()) return true;
				else {
					this.stack.push(this.nextEl.getList());
					this.indexStack.push(0);
				}
			}
		}

		return false;
	}

	next(): number {
		return this.nextEl.getInteger();
	}
}
