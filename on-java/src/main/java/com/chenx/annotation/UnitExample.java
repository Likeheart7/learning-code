package com.chenx.annotation;

public class UnitExample {
    private String test() {
        return "this is test";
    }

    @Test
    public int test01() {
        System.out.println("method test01");
        return 2;
    }

    @Test
    public boolean test02() {
        System.out.println("method test02");
        return true;
    }

    @Test
    public boolean test03() {
        System.out.println("method test03");
        return false;
    }

    @Test
    public boolean test04() {
        return "this is test".equals(test());
    }

}
