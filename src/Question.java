public class Question {

    /**
     * 题目：八幢房屋组成的部落。以单元表示，排列成直线，每天每个单元都与他相邻的单元（邻居）竞争。
     * 整数值1表示一个活跃单元，数值0表示一个非活跃单元。
//     * 如果相邻单元都活跃或者都不活跃，那么该单元第二天的状态为不活跃；否则为活跃、部落两端的单元都只有一个邻居单元，
     * 我们假设另一个邻近单元一直处于不活跃状态。即使更新单元状态后，要考虑之前的状态以更新其他单元状态。
     * 应更新所有单元的单元信息。
     * 编写一个算法，求出给定天数后所有单元的状态。
     * 测试用例：
     */

    public static void main(String... args) {

        TwoWayLinkedList linkedList = new TwoWayLinkedList();

        linkedList.add(1,0);
        linkedList.add(3,1);
//        linkedList.add(4);
//        linkedList.add(5);
//        linkedList.add(6);
        linkedList.print();

//        linkedList.remove(2);
//        linkedList.print();
//
//        linkedList.remove(4);
//        linkedList.print();

        System.out.println(">>>>>>>>>change>>>>>>>>>");
        linkedList.change();
        linkedList.print();


    }

}
