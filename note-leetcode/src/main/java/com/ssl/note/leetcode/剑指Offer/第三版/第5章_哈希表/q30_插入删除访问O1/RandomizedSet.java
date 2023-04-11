package com.ssl.note.leetcode.剑指Offer.第三版.第5章_哈希表.q30_插入删除访问O1;

import java.util.*;

/**
 * @author SongShengLin
 * @date 2021/9/30 9:40 上午
 * @description
 */
public class RandomizedSet {

    /**
     * 设计一个插入、删除、随机访问都是O(1)数据结构
     * 输入: inputs = ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
     * [[], [1], [2], [2], [], [1], [2], []]
     * 输出: [null, true, false, true, 2, true, false, 2]
     * 解释:
     * RandomizedSet randomSet = new RandomizedSet();  // 初始化一个空的集合
     * randomSet.insert(1); // 向集合中插入 1 ， 返回 true 表示 1 被成功地插入
     * randomSet.remove(2); // 返回 false，表示集合中不存在 2
     * randomSet.insert(2); // 向集合中插入 2 返回 true ，集合现在包含 [1,2]
     * randomSet.getRandom(); // getRandom 应随机返回 1 或 2
     * randomSet.remove(1); // 从集合中移除 1 返回 true 。集合现在包含 [2]
     * randomSet.insert(2); // 2 已在集合中，所以返回 false
     * randomSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2
     */
    private Map<Integer, Integer> map;
    private List<Integer> nums;


    public RandomizedSet() {
        map = new HashMap<>();
        nums = new ArrayList<>();
    }

    /**
     * 插入
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        // map存<待插入的数，它插入的数组位置>
        map.put(val, nums.size());
        nums.add(val);
        return true;
    }

    /**
     * 删除
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        // map不要直接删除，最好是把数组末尾元数坐标替换成待删除pos
        int pos = map.get(val);
        map.put(nums.get(nums.size() - 1), pos);
        map.remove(val);

        // nums不要直接删除pos位置上元素，与末尾元数交换后再删除，避免移动其他元素
        nums.set(pos, nums.get(nums.size() - 1));
        nums.remove(nums.size() - 1);
        return true;
    }


    /**
     * 随机获得一个值
     */
    public int getRandom() {
        int pos = (int) (Math.random() * (nums.size()));
        return nums.get(pos);
//        Random random = new Random();
//        return nums.get(random.nextInt(nums.size()));
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
//        randomizedSet.insert(1);
//        randomizedSet.remove(1);
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
//            int pos = (int) (Math.random() * (nums.size()));
            int pos = random.nextInt(nums.size());
            System.out.println("pos" + pos);
        }


    }
}
