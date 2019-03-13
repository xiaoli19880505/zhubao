package com.sys.common;

public class Random {

    public static String ScRandom() {
        String flag="";
        int intFlag = (int) (Math.random() * 1000000);
         flag = String.valueOf(intFlag);
        if (flag.length() == 6 && flag.substring(0, 1).equals("9")) {
            return  flag;
        } else {
            intFlag = intFlag + 100000;
            flag=String.valueOf(intFlag);
            return  flag;
        }
    }

    public static void main(String[] args) throws Exception {
        ScRandom();
    }

}
