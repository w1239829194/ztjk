package com.mmall.service;

public class test {
    public static void main(String[] args) {
        String s = "0.1.2.3.4";
        String x = "0.1.3.5";
        String level = "0.1.2.3.4";
        level = x+level.substring(s.length());
        System.out.println(level);


        if (x.indexOf(s) == 0){
            System.out.println(1);
        }else{
            System.out.println(2);
        }

    }
}

