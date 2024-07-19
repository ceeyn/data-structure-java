package segmentTree;

public class Main {

    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
                (a, b) -> a + b);
        System.out.println(segTree);

        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(2, 5));
        System.out.println(segTree.query(0, 5));

        int[][] arr = new int[3][3];
        arr[0] = new int[]{1,1};
        int[] arr1 = arr[0];
        for(int ele : arr1){
            System.out.println(ele);
        }
    }
}
