package com.mashibing.juc.c_020.myLock;

import sun.security.krb5.internal.tools.Klist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @Author:         songyafei
 * @Package:        com.mashibing.juc.c_020.myLock
 * @ClassName:      MyExchanger
 * @Description:     线程交换资源的使用
 * @Param
 * @Exception
 * @CreateDate:     2019/12/17 16:02
 * @UpdateRemark:   The modified content
 * @Version:        1.0
 * @Return :
 **/

public class MyExchanger {



    public  static  void main(String [] args){
         List firearmsList = new ArrayList();
         List equipmentList= new ArrayList();

        List firearmsList1 = new ArrayList();
        List equipmentList1= new ArrayList();
        Exchanger<Map<String,List>> exchanger = new Exchanger<Map<String,List>>();

       new Thread(()->{
          System.out.println("Player Hao Chenghao enters the game");
           Map<String,List> mapList = new HashMap<>();
           firearmsList.add("Fire Unicorn");
           firearmsList.add("Rose Elf");
           firearmsList.add("artillery");

           equipmentList.add("Blonde Wig");
           equipmentList.add("Lace");
           equipmentList.add("High-heeled shoes");


           mapList.put("firearms", firearmsList);
           mapList.put("equipment", equipmentList);



           try {
               System.out.println("He thinks he needs to change clothes with Xiao Ming");
               System.out.println("Initiate a reload request");
               System.out.println("The other party agrees to change");

               System.out.println("HaoChengHao  firearms:");
               for (int i = 0; i < firearmsList.size(); i++) {
                   System.out.println(firearmsList.get(i));

               }
               System.out.println("HaoChengHao  Dress up:");
               for (int f = 0; f < equipmentList.size(); f++) {
                   System.out.println(equipmentList.get(f));

               }

               System.out.println("I wish you a happy game");
               TimeUnit.SECONDS.sleep(500);
               mapList = exchanger.exchange(mapList);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println("Game begins!!");

       },"ChengHao").start();










        new Thread(()->{
            System.out.println("Player Li XiaoMing enters the game");
            Map<String,List> mapList = new HashMap<>();
            firearmsList1.add("artillery");
            firearmsList1.add("Justin Gatlin");
            firearmsList1.add("artillery");

            equipmentList1.add("Suit jacket");
            equipmentList1.add("Western-style trousers");
            equipmentList1.add("leather shoes");


            mapList.put("firearms", firearmsList);
            mapList.put("equipment", equipmentList);



            try {
                System.out.println("XiaoMing  firearms:");
                for (int i = 0; i < firearmsList.size(); i++) {
                    System.out.println(firearmsList.get(i));

                }
                System.out.println("XiaoMing  Dress up:");
                for (int f = 0; f < equipmentList.size(); f++) {
                    System.out.println(equipmentList.get(f));

                }
                TimeUnit.SECONDS.sleep(500);
                mapList = exchanger.exchange(mapList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Game begins!!");

        },"XiaoMing").start();
    }

}
