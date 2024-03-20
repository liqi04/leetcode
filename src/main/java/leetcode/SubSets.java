package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author lq
 * @date : 2021/4/12
 */
public class SubSets {

    public static void main(String[] args) {
        List<List<String>> ans = new ArrayList<>();
        List<List<String>> nums = new ArrayList<>();
        List<List<Integer>> visits;
        nums.add(Arrays.asList("a", "b", "c"));
        nums.add(Arrays.asList("d", "e"));
        visits = nums.stream().map(o -> o.stream().map(o1 -> 0).collect(Collectors.toList())).collect(Collectors.toList());
        backtrace(nums, new ArrayList<>(), nums.stream().mapToInt(List::size).sum(),ans,visits);
        System.out.println(ans);
    }

    /**
     *有多个分组，每个分组由不同的字符构成，请找到这些分组合并后的全部可能结果，要求分组内的字母保持原来的顺序，分组之间不要求顺序。
     *
     * 例如:
     * 分组1：[a,b,c]
     * 分组2：[d,e]
     *
     * 可能的排列有[a, b, c, d, e]或者[a, d, b, e, c]，但不能是[b, a, c, d, e], 因为违反了分组1的有序性
     *
     *
     * 基本思想： 回溯算法
     * @param nums 输入的数组
     * @param tmp 临时数组，保存每次组合结果
     * @param length 结果集最大长度
     * @param ans 输出结果
     * @param visits 记录已经访问过的元素
     */
    public static void backtrace(List<List<String>> nums, List<String> tmp, int length, List<List<String>> ans, List<List<Integer>> visits) {
        if (tmp.size() == length) {
            ans.add(new ArrayList<>(tmp));
        }
        for (int i = 0; i < nums.size(); i++) {
            final List<String> num = nums.get(i);
            final List<Integer> visit = visits.get(i);
            for (int j = 0; j < num.size(); j++) {
                // j > 0 && visit.get(j - 1) == 0 如果当前元素的前一个没有访问过，则跳过该元素，保证组内有序
                if (visit.get(j) == 1) {
                    continue;
                }
                if ((j > 0 && visit.get(j - 1) == 0)){
                    break;
                }
                tmp.add(num.get(j));
                visit.set(j, 1);
                backtrace(nums, tmp, length,ans, visits);
                visit.set(j, 0);
                tmp.remove(num.get(j));
            }
        }
    }

}
