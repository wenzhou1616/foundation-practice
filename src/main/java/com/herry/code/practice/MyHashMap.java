package com.herry.code.practice;


import java.util.Objects;

/**
 * @author herry
 */
public class MyHashMap<K, V> {
    /**
     * 哈希表
     */
    private Entry<K, V>[] table;

    /**
     * 元素个数
     */
    private int size;

    /**
     * 负载因子
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * 键值对，链表节点
     */
    private static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    public MyHashMap() {
        this.table = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public MyHashMap(int capacity) {
        this.table = (Entry<K, V>[]) new Entry[capacity];
        this.size = capacity;
    }

    /**
     * 扰动函数，作用是重新计算哈希值，让 key 分布得更均匀一些，目的是为了减少哈希冲突
     */
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 计算元素存放的位置
     */
    private int getIndex(K key) {
        // 在数组长度是2的幂次方的前提下，用数组长度减一与哈希值进行与运算，等价于求模操作，好处是效率更高
        return (table.length - 1) & hash(key);
    }

    /**
     * 检查两个 key 是否相同
     */
    private boolean checkKeySame(K oldKey, K newKey) {
        // 先判断哈希值是否相同，然后用equals判断 key 是否相同，这样效率更高
        return hash(oldKey) == hash(newKey) && (Objects.equals(newKey, oldKey));
    }

    /**
     * 检查是否元素的数量是否超过负载
     */
    private boolean checkSize() {
        return size * 1.0 / table.length >= DEFAULT_LOAD_FACTOR;
    }

    /**
     * 添加元素
     */
    public V put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> cur = table[index];
        Entry<K, V> prev = null;

        // 若当前位置没有元素则直接添加
        if (cur == null) {
            table[index] = new Entry<>(key, value);
        } else {
            // 若当前位置有元素，则遍历链表
            while (cur != null) {
                // 存在key相同的元素，更新值
                if (checkKeySame(cur.key, key)) {
                    cur.value = value;
                    return value;
                }
                // 移动到下一个节点
                prev = cur;
                cur = cur.next;
            }
            // 遍历完链表，发现不存在key相同的元素，则在末尾插入元素
            prev.next = new Entry<>(key, value);
        }
        // 添加完元素，元素数量加一
        size++;
        // 判断是否超过阈值，超过了就要扩容
        if (checkSize()) {
            resize();
        }
        return value;
    }

    /**
     * 扩容，扩大为原来的2倍
     */
    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = (Entry<K, V>[]) new Entry[table.length * 2];
        moveData(oldTable);
    }

    /**
     * 将旧哈希表的数据移动到新的哈希表，由于数组的长度改变了，因此元素需要重新计算位置
     */
    private void moveData(Entry<K, V>[] oldTable) {
        // 遍历旧哈希表
        for (Entry<K, V> kvEntry : oldTable) {
            Entry<K, V> node = kvEntry;
            // 遍历链表
            while (node != null) {
                Entry<K, V> insertNode = new Entry<>(node.key, node.value);
                // 重新计算索引位置
                int index = getIndex(node.key);
                Entry<K, V> cur = table[index];
                if (cur == null) {
                    // 若 table 的当前位置没有元素, 则直接插入该节点
                    table[index] = insertNode;
                } else {
                    // 如果 table 的当前位置已经有元素, 则遍历这条链表，将元素添加到末尾
                    Entry<K, V> prev = null;
                    while (cur != null) {
                        prev = cur;
                        cur = cur.next;
                    }
                    prev.next = insertNode;
                }
                // 移动到链表的下一个节点
                node = node.next;
            }
        }
    }

    /**
     * 获取元素
     */
    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> cur = table[index];
        // 当前元素不为 null，则遍历链表找到 key 相同的元素
        while (cur != null) {
            if (checkKeySame(cur.key, key)) {
                return cur.value;
            }
            cur = cur.next;
        }
        // 不存在这个元素，返回 null
        return null;
    }

    /**
     * 删除元素
     */
    public V remove(K key) {
        int index = getIndex(key);
        Entry<K, V> cur = table[index];
        // 当前位置有元素
        if (cur != null) {
            if (checkKeySame(cur.key, key)) {
                // 当前元素的 key 与要删除的 key 相同
                table[index] = cur.next;
                return cur.value;
            } else {
                // 遍历链表后面的 key 是否有相同的
                Entry<K, V> pre = cur;
                cur = cur.next;
                while (cur != null) {
                    if (cur.key.equals(key)) {
                        // 删除 key 相同的元素
                        pre.next = cur.next;
                        return cur.value;
                    }
                    pre = cur;
                    cur = cur.next;
                }
            }
        }
        // 不存在这个元素，返回 null
        return null;
    }

    @Override
    public String toString() {
        for (int i = 0; i < table.length; i++) {
            System.out.print(i + ": ");
            Entry<K, V> cur = table[i];
            if (cur == null) {
                System.out.println("null");
                continue;
            }
            while (cur != null) {
                System.out.print("[key=" + cur.key + ", " + "value=" + cur.value + "]");
                cur = cur.next;
                if (cur == null) {
                    System.out.println();
                } else {
                    System.out.print(" -> ");
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        map.put(1, 11);
        map.put(2, 22);
        map.put(3, 33);
        map.put(17, 1717);
        map.put(33, 3333);
        map.put(49, 4949);
        map.put(65, 6565);
        System.out.println("扩容前: ");
        System.out.println(map);
        System.out.println(map.get(1));
        System.out.println(map.get(17));
        System.out.println(map.get(3));

        map.remove(33);
        map.remove(65);
        System.out.println("删除后：");
        System.out.println(map);

        map.put(5, 55);
        map.put(6, 55);
        map.put(7, 55);
        map.put(8, 55);
        map.put(9, 55);
        map.put(10, 55);
        System.out.println("扩容后: ");
        System.out.println(map);

    }

}