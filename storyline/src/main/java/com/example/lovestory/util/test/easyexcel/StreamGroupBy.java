package com.example.lovestory.util.test.easyexcel;

import java.util.ArrayList;
import java.util.List;

public class StreamGroupBy {
//
//    public static void main(String[] args) {
//        int i = 0;
//        List<User> users = new ArrayList<>();
//        users.add(new User());
//        users.add(new User());+
//        users.add(new User());
//        users.add(new User());
//        users.forEach(user -> {
//            user.setAge("18");
//            user.setId(i + 1);
//            user.setName("name" + i);
//        });
//
//        Map<String, List<User>> collect = users.stream()
//                .collect(Collectors.groupingBy(
//                        u -> u.getName() + u.getId()
//                ));
//
//        System.out.println(collect);
//    }
    //60万一个sheet页

    //60万一次查询 和 写入分3次  可动态调整
    //查询一次 写入3次
    public static Integer SINGLE_WRITE = 20 * 10000;

    public void excelRead() {
        //传参对象
        ArrayList arrayList = new ArrayList();
        //查询总条数
        int total = 300 * 10000;//根据查询而来
        long longTotal = 300 * 10000;//根据查询而来

        //且针对csv的导出
        if (total <= SINGLE_WRITE * 3) {
            //进行分页导出写入
            int longTotal1 = (int) longTotal;
            int count = total / SINGLE_WRITE; //写入次数
            for (int i = 0; i < count; i++) {
                arrayList.subList(i * SINGLE_WRITE, (i + 1) * SINGLE_WRITE);
                //easyExcel写入
            }
            //最后一次
            List list = arrayList.subList(count * SINGLE_WRITE, arrayList.size());
            //easyExcel写入

            //分sheet页
        } else {
            //1 计算sheet页
            //2 调用上面单sheet页写入方法  封装方法需传入sheet页
        }


        //根据条数做分页

        //持续写入
    }

}
