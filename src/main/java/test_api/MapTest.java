package test_api;

import java.util.*;

/**
 * @ClassName MapTest
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/21 08:58
 * @Version 1.0
 */
public class MapTest {
    public void testSet() {
        // 1.HashSet
        //  HashSet 最显著的特点是它不保证元素的迭代顺序；它可能与元素添加的顺序不同，因为 HashSet 不是有序的。
        //  此外，HashSet 不允许重复元素，即每个元素只能出现一次
        //无序性: HashSet 不保证元素的顺序。
        //唯一性: 不允许重复元素。
        //null值: 允许有一个 null 元素。
        // 创建HashSet
        /**
         * add(E e): 添加元素到集合中。如果集合已包含该元素，则不会添加。
         * remove(Object o): 移除集合中的指定元素。
         * contains(Object o): 判断集合中是否包含指定元素。
         * size(): 获取集合中的元素数量。
         * isEmpty(): 判断集合是否为空。
         * clear(): 移除集合中的所有元素。
         * iterator(): 返回在此 HashSet 的元素上进行迭代的迭代器。
         */
        Set<String> set = new HashSet<>();

        // 添加元素
        set.add("Java");
        set.add("Python");
        set.add("C++");

        // 尝试添加重复的元素
        boolean isAdded = set.add("Java"); // 返回false，因为"Java"已经存在

        // 打印集合内容
        System.out.println(set); // 输出顺序不定，例如：[Python, Java, C++]

        // 判断元素是否存在
        boolean contains = set.contains("Python"); // 返回true

        // 删除元素
        set.remove("C++");

        // 集合大小
        int size = set.size(); // 返回2，因为已经移除了"C++"

        // 判断集合是否为空
        boolean isEmpty = set.isEmpty(); // 返回false

        // 清空集合
        set.clear();

        // 再次检查集合是否为空
        isEmpty = set.isEmpty(); // 返回true，因为clear()方法已经清空了集合
    }

    /**
     * TreeMap 是Java中的一种基于红黑树的 NavigableMap 实现。它存储的键值对是按照键的自然顺序或者构造 TreeMap
     * 时所指定的 Comparator 进行排序的。由于其基于红黑树，这就使得 TreeMap 中的增删查操作都能保持对数时间复杂度，即 O(log n)。
     * TreeMap 的主要特点：
     * 有序性：TreeMap 中的元素总是按照键的自然顺序排序，或者根据构造时传入的 Comparator 进行排序。
     * 键唯一：与 HashMap 类似，TreeMap 的键是唯一的，如果插入已存在的键，其对应的值将被覆盖。
     * 值可以重复：尽管键必须唯一，但相同的值可以被赋给不同的键。
     * TreeMap 的基本操作：
     * put(K key, V value)：将指定的值与此映射中的指定键关联起来。
     * get(Object key)：返回指定键所映射的值，如果映射不包含该键的映射关系，则返回 null。
     * remove(Object key)：如果存在一个键的映射关系，则将其从映射中移除。
     * firstKey()：返回此映射中当前第一个（最低）键。
     * lastKey()：返回此映射中当前最后一个（最高）键。
     * higherKey(K key)：返回此映射中比给定键大的最小键。
     * lowerKey(K key)：返回此映射中比给定键小的最大键。
     * subMap(K fromKey, K toKey)：返回此映射的部分视图，其键的范围从 fromKey（包括）到 toKey（不包括）。
     * headMap(K toKey)：返回此映射的部分视图，其键的范围小于 toKey。
     * tailMap(K fromKey)：返回此映射的部分视图，其键的范围大于或等于 fromKey。
     */
    public class TreeMapExample {
        public  void testTreeMap(String[] args) {
            // 创建一个 TreeMap 实例
            TreeMap<Integer, String> treeMap = new TreeMap<>();

            // 向 TreeMap 中添加一些键值对
            treeMap.put(3, "三");
            treeMap.put(1, "一");
            treeMap.put(4, "四");
            treeMap.put(2, "二");

            // 打印 TreeMap 中的所有键值对
            System.out.println(treeMap); // 输出: {1=一, 2=二, 3=三, 4=四}

            // 访问第一个和最后一个键
            System.out.println("第一个键: " + treeMap.firstKey()); // 输出: 第一个键: 1
            System.out.println("最后一个键: " + treeMap.lastKey()); // 输出: 最后一个键: 4

            // 根据键来获取值
            String value = treeMap.get(3);
            System.out.println("键为3的值: " + value); // 输出: 键为3的值: 三

            // 移除元素
            treeMap.remove(2);
            System.out.println("移除键为2的元素后: " + treeMap); // 输出: 移除键为2的元素后: {1=一, 3=三, 4=四}

            // 获取比给定键大的最小键
            Integer higherKey = treeMap.higherKey(2);
            System.out.println("比2大的最小键: " + higherKey); // 输出: 比2大的最小键: 3

            // 获取比给定键小的最大键
            Integer lowerKey = treeMap.lowerKey(2);
            System.out.println("比2小的最大键: " + lowerKey); // 输出: 比2小的最大键: 1

            // 测试map的merge方法
            Map<String, Integer> map = new HashMap<>();
            map.put("apple", 10);
            map.put("banana", 20);

            map.merge("apple", 1, Integer::sum); // 将 "apple" 映射的值与 1 相加
            map.merge("cherry", 5, Integer::sum); // "cherry" 之前没有映射，直接添加
            map.merge("banana", 1, (oldValue, newValue) -> null); // "banana" 的映射将会被移除，因为 remappingFunction 返回了 null
            // 结果：{"apple"=11, "cherry"=5}

            // 测试map的entrySet()方法
            for (Map.Entry<String, Integer> entry : map.entrySet()){
                entry.getKey();
                entry.getValue();
            }
        }
    }
}
