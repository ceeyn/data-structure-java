package test_api;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName test_api.DataStructure
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/6 14:21
 * @Version 1.0
 */
public class DataStructure {
    /**
     * Deque接口在Java中定义了一系列操作，用以允许在双端队列的两端插入和移除元素。以下是一些主要的Deque接口操作：
     * 插入操作
     * addFirst(E e): 在双端队列的头部插入指定元素。
     * addLast(E e): 在双端队列的尾部插入指定元素。
     * offerFirst(E e): 在双端队列的头部插入指定元素；如果队列已满，则返回false。
     * offerLast(E e): 在双端队列的尾部插入指定元素；如果队列已满，则返回false。
     * push(E e): 将元素推入双端队列表示的栈（相当于addFirst）。
     * 移除操作
     * removeFirst(): 移除并返回双端队列的第一个元素。
     * removeLast(): 移除并返回双端队列的最后一个元素。
     * pollFirst(): 移除并返回双端队列的第一个元素；如果队列为空，则返回null。
     * pollLast(): 移除并返回双端队列的最后一个元素；如果队列为空，则返回null。
     * pop(): 移除并返回双端队列表示的栈的顶部元素（相当于removeFirst）。
     * 检索操作
     * getFirst(): 返回双端队列的第一个元素。
     * getLast(): 返回双端队列的最后一个元素。
     * peekFirst(): 返回双端队列的第一个元素；如果队列为空，则返回null。
     * peekLast(): 返回双端队列的最后一个元素；如果队列为空，则返回null。
     * 其他操作
     * removeFirstOccurrence(Object o): 从双端队列中移除第一次出现的指定元素。
     * removeLastOccurrence(Object o): 从双端队列中移除最后一次出现的指定元素。
     * contains(Object o): 判断双端队列是否包含指定元素。
     * size(): 返回双端队列中的元素数量。
     * isEmpty(): 判断双端队列是否为空。
     * iterator(): 返回双端队列元素的迭代器，以正向顺序遍历。
     * descendingIterator(): 返回双端队列元素的迭代器，以逆向顺序遍历。
     * clear(): 清空双端队列中的所有元素。
     * 这些操作为使用双端队列提供了灵活性，使得它可以被当作栈、队列或者双向队列使用。
     *
     */
    public void testQueue(){
        // 创建一个Deque实例，这里我们使用LinkedList类，因为它实现了Deque接口
        Deque<String> deque = new LinkedList<>();
        // 在队列的尾部添加元素
        deque.addLast("Element 1 (Tail)");
        // 在队列的头部添加元素
        deque.addFirst("Element 2 (Head)");
        // 在队列的头部再添加一个元素
        deque.offerFirst("Element 3 (Head)");
        // 打印Deque
        System.out.println("Deque after adding elements: " + deque);
        // 删除头部元素并返回
        String headElement = deque.removeFirst();
        System.out.println("Removed head element: " + headElement);
        // 删除尾部元素并返回
        String tailElement = deque.removeLast();
        System.out.println("Removed tail element: " + tailElement);
        // 再次打印Deque
        System.out.println("Deque after removing elements: " + deque);


        // 初始化一个 ArrayDeque
        Deque<String> deque1 = new ArrayDeque<>();

        // 在队尾添加元素
        deque1.add("Element 1 (Tail)");

        // 在队头添加元素
        deque1.push("Element 2 (Head)");

        // 在队尾添加元素
        deque1.add("Element 3 (Tail)");

        // 在队头添加元素
        deque1.push("Element 4 (Head)");

        // 打印队列
        System.out.println("Deque: " + deque1);

        // 移除队头元素
        deque1.removeFirst();
        System.out.println("Deque after removing first element: " + deque1);

        // 移除队尾元素
        deque1.removeLast();
        System.out.println("Deque after removing last element: " + deque1);

        // 获取队头元素
        String head = deque1.peek();
        System.out.println("Head of the Deque: " + head);

        // 获取队尾元素
        String tail = deque1.peekLast();
        System.out.println("Tail of the Deque: " + tail);

        // 获取队列的大小
        int size = deque1.size();
    }

}
