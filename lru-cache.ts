class Node {
	key: number;
	value: number;
	prev: Node | null;
	next: Node | null;

	constructor(key: number, value: number) {
		this.key = key;
		this.value = value;
		this.prev = null;
		this.next = null;
	}
}
// Time complexity- O(1) for all operations
// Space complexity- O(capacity)
class LRUCache {
	private cache: Map<number, Node>;
	private capacity: number;
	private head: Node;
	private tail: Node;
	constructor(capacity: number) {
		this.cache = new Map<number, Node>();
		this.capacity = capacity;

		// initialize with dummy node
		this.head = new Node(-1, -1);
		this.tail = new Node(-1, -1);

		// link head and tail
		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	removeNode(node: Node): void {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	addToHead(node: Node): void {
		node.next = this.head.next;
		node.next.prev = node;
		node.prev = this.head;
		this.head.next = node;
	}

	get(key: number): number {
		if (!this.cache.has(key)) {
			return -1;
		}

		let node = this.cache.get(key);
		// remove the node
		this.removeNode(node);
		// add to head
		this.addToHead(node);

		return node.value;
	}

	put(key: number, value: number): void {
		if (this.cache.has(key)) {
			let node = this.cache.get(key);
			node.value = value;
			this.removeNode(node);
			this.addToHead(node);
		} else {
			if (this.capacity === this.cache.size) {
				let tailPrev = this.tail.prev;
				this.removeNode(tailPrev);
				this.cache.delete(tailPrev.key);
			}
			let node = new Node(key, value);
			this.addToHead(node);
			this.cache.set(key, node);
		}
	}
}
