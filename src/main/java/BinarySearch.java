/**
 * @ClassName BinarySearch
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/13 16:52
 * @Version 1.0
 */
public class BinarySearch {
    int[] nums;
    // 染色代码，只要是一个将mid染成蓝色即可，蓝色可代表多重含义，如可代表target左边，或者target右边
    public boolean isBlue(int mid, int target){
        return mid > target;
    }

    public int left_bound(int target){
        // l以外的都是确定的小于target，染成红色，r以外都是确定的大于等于target，染成蓝色，试图给[r,l]区间染色
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(isBlue(nums[mid],target)){
                // mid 染成蓝色, mid = r + 1
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return r+1;
    }
}
