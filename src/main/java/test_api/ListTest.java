package test_api;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @ClassName test_api.Collection
 * @Description TODO
 * @Author @hzp
 * @Date 2024/4/19 11:13
 * @Version 1.0
 */
public class ListTest {

    // list -> array
    static List<Integer> list = new ArrayList<Integer>();

    static final int MODULO = (int) 1e9+7;

    public static void testArrayList() {
        /**
         * List 接口主要有两个常用的实现类：ArrayList 和 LinkedList。
         * ArrayList 是一个基于数组实现的 List，它允许进行快速的随机访问。但是，插入和删除元素（特别是列表的开始部分）时可能比较慢，因为这涉及到数组内部的数据移动。
         * LinkedList 是一个基于双向链表实现的 List，它提供了更好的插入和删除操作性能，但是随机访问的速度较慢。
         * 以下是 List 接口的一些常用方法：
         * 添加元素
         * add(E e): 将指定的元素添加到列表的末尾。
         * add(int index, E element): 将指定的元素插入列表的指定位置。
         * 访问元素
         * get(int index): 返回列表中指定位置的元素。
         * 修改元素
         * set(int index, E element): 用指定的元素替换列表中指定位置的元素。
         * 删除元素
         * remove(int index): 移除列表中指定位置的元素。
         * remove(Object o): 从列表中移除首次出现的指定元素（如果存在）。
         * 列表大小
         * size(): 返回列表中的元素数量。
         * 检查是否包含
         * contains(Object o): 如果列表包含指定的元素，则返回 true。
         * 查找索引
         * indexOf(Object o): 返回列表中首次出现的指定元素的索引，或如果列表不包含该元素，则返回 -1。
         * lastIndexOf(Object o): 返回列表中最后出现的指定元素的索引，或如果列表不包含该元素，则返回 -1。
         * 遍历列表
         * 使用迭代器（Iterator）或列表迭代器（ListIterator）。
         * 使用 for 循环和 forEach 方法。
         * 转换为数组
         * toArray(): 将列表转换为一个数组。
         * 清空列表
         * clear(): 移除列表中的所有元素。
         */

        /**
         * ArrayList
         * 搜索和排序
         * boolean contains(Object o): 如果列表包含指定的元素，则返回真。
         * void sort(Comparator<? super E> c): 根据指定的比较器对列表进行排序。
         * 转换为数组
         * Object[] toArray(): 返回一个包含列表中所有元素的数组。
         * <T> T[] toArray(T[] a): 返回一个包含列表中所有元素的数组；返回数组的运行时类型是指定数组的类型。
         * 迭代
         * Iterator<E> iterator(): 返回此列表中元素的迭代器
         */
        list.add(1);
        list.add(2);
        // 1.list.toArray(new int[5])不行 使用toArray方法来将List<Integer>转换为Integer[]数组是直接的，
        // 但不能直接转换为int[]，因为toArray无法返回基本类型数组
        // 题目要求int[]，返回Integer[]不行
        Integer[] objects = list.toArray(new Integer[5]);
        for(int i = 0; i< objects.length; i++){
            System.out.println(objects[i]);
        }
        // 2.stream
        IntStream intStream = list.stream().mapToInt(i -> i);
        int[] ints = list.stream().mapToInt(i -> i).toArray();
        // array转list
        // 1.asList(),改变原array也会改变先有list，是将原array包装成list，public static <T> List<T> asList(T... a) {
        //        return new ArrayList<>(a);
        //    }
        Arrays.asList(ints);
        System.out.println(ints);
        for(int i : ints) System.out.println(i);
        Arrays.asList(new int[]{1,2});
        System.out.println(Arrays.asList(new int[]{1,2}));
        // 2.List.of() ，Java 9 中引入的一个静态工厂方法，用于创建一个不可修改的列表（Immutable List）。这个方法接受一系列元素作为参数

        Map<Integer,Integer> map = new TreeMap<>();
        Collections.sort((List)map.values());


        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> subNumbers = numbers.subList(1, 4); // 获取索引1（包含）到4（不包含）的视图
        System.out.println(subNumbers); // 输出: [2, 3, 4]
    }

    public void testLinkedList() {
        /**
         * 动态增长：LinkedList 可以在运行时动态地增加和减少容量。
         * 高效的插入和删除操作：尤其是在列表的开头和中间位置进行插入和删除操作时。
         * 实现了 List 和 Deque 接口：除了 List 接口的方法外，LinkedList 还提供了首部和尾部操作的方法，如 addFirst, addLast, removeFirst, 和 removeLast。
         * 常用方法
         * 添加元素
         * add(E e) 和 addLast(E e)：在链表的末尾添加指定的元素。
         * addFirst(E e)：在链表的开头添加指定的元素。
         * add(int index, E element)：在链表的指定位置插入指定的元素。 // 前提是index本身在数组的长度内，
         * list长度为1，list.add(2,0)是错误的
         * 访问元素
         * get(int index)：返回链表中指定位置的元素。
         * getFirst()：返回链表的第一个元素。
         * getLast()：返回链表的最后一个元素。
         * 修改元素
         * set(int index, E element)：用指定元素替换链表中指定位置的元素。
         * 删除元素
         * remove() 和 removeFirst()：移除并返回链表的第一个元素。
         * removeLast()：移除并返回链表的最后一个元素。
         * remove(int index)：移除链表中指定位置的元素。
         * remove(Object o)：移除链表中的第一个出现的指定元素（如果存在）。
         * 队列操作
         * LinkedList 实现了 Deque 接口，因此提供了队列操作，例如 offer, poll, 和 peek 方法。
         * 清空列表和列表大小
         * clear()：移除链表中的所有元素。
         * size()：返回链表中的元素数。
         * 示例代码
         * 下面是一些使用 LinkedList 的示例代码：
         */
        // 创建 LinkedList
        LinkedList<String> names = new LinkedList<>();

        // 添加元素
        names.add("Alice");
        names.addFirst("Bob"); // 在开头添加
        names.addLast("Charlie"); // 在末尾添加

        // 访问元素
        System.out.println(names.getFirst()); // 输出: Bob
        System.out.println(names.getLast()); // 输出: Charlie

        // 修改元素
        names.set(1, "Alex");

        // 删除元素
        names.removeFirst(); // 删除第一个元素
        names.removeLast(); // 删除最后一个元素

        // 使用增强的 for 循环遍历
        for (String name : names) {
            System.out.println(name);
        }

        // 清空列表
        names.clear();
    }


    public void testArray(){
        /**
         * 在Java中，当你声明一个多维数组时，你只需要为第一维（以及任何你立即要初始化的维）指定大小。对于后续的维度，
         * 你可以稍后初始化每个元素，这些元素本身可以是不同长度的数组。这种灵活性允许Java数组在各个维度上具有不规则的形状。
         * 当你使用memo = new int[n][n][];进行初始化时，你实际上创建了一个二维数组，其中每个元素都是一个指向int[]数组的引用，
         * 但这些引用在初始时都是null。这就意味着第三个维度的数组尚未被分配内存，你有机会根据每个元素的需要赋予它们不同的大小。
         * 这种方式在你需要存储各种长度不一的数组时非常有用。例如，在处理一些算法问题时，如果每个子问题的结果大小不一致，这种方法就非常适合。
         * 但在你的情况下，即寻找字符串中的最长回文子串，并使用记忆化搜索来存储每个子问题的起始和结束索引时，每个子问题的结果都是一对数值（即数组的长度固定为2）。
         * 因此，直接在声明时指定第三维的大小是合理的，并且这样做可以简化代码，因为你不需要为每个子问题的结果单独分配内存。
         * 使用memo = new int[n][n][2];，你立即为每个子问题的结果分配了一个长度为2的数组。这样，你就不需要担心后续的空指针异常，
         * 也不需要在使用之前检查每个元素是否为null然后再分配内存。这种方法更加直接、高效，特别是当你知道第三维的确切大小时。
         */
    }



}
