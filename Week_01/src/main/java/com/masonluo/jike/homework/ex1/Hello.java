package com.masonluo.jike.homework.ex1;

/**
 * @author Masonluo
 * @date 2020-10-17 09:47
 */
public class Hello {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                sum += 2;
            } else {
                sum++;
            }
        }
        System.out.println(sum);
    }
}
