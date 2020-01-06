# Bruteforce using array and hashmap

class LRUCache:
    def __init__(self, capacity: int):
        self.d = {}
        self.size = 0
        self.queue = []
        self.capacity = capacity
        
    def get(self, key: int) -> int:
        if key not in self.d:
            return -1
        else:
            self.queue.pop(0)
            self.queue.append(key)
            return self.d[key]

    def put(self, key: int, value: int) -> None:
        if key not in self.d:
            if self.size == self.capacity:
                popped = self.queue.pop(0)
                if popped:
                    del self.d[popped]
                self.d[key] = value
                self.queue.append(key)
            else:
                self.d[key] = value
                self.size += 1
                self.queue.append(key)
        else:
            self.d[key] = value
            self.queue.pop(0)
            self.queue.append(key)


# Optmized using doubly linked list and hashmap
