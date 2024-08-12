package com.chenx.newfeature;


import com.chenx.newfeature.pet.Cat;
import com.chenx.newfeature.pet.Dog;
import com.chenx.newfeature.pet.Pet;

public class NewInSwitch {
    public static void main(String[] args) {
        arrow("name");
        checkNull(null);
        System.out.println(asExpression("j"));
        // 模式匹配
        patternMatching(new Dog());
    }

    /**
     * 通过箭头省略break
     */
    private static void arrow(String str) {
        switch (str) {
            case "name" -> System.out.println("in the name case");
            default -> System.out.println("in the default");
        }
    }

    /**
     * 21 版本中的null支持
     */
    private static void checkNull(String str) {
        switch (str) {
            case "name" -> System.out.println("in the name case");
            case null ->
                    System.out.println("we can support null value"); // 21版本才能正式使用，这里需要在vmoptions和java compiler的对应模块加入--enable-preview参数
            default -> System.out.println("in the default block");
        }
    }

    /**
     * 作为表达式给出返回值
     */
    private static int asExpression(String str) {
        var result = switch (str) {
            case "j" -> 1;
            case "k" -> 2;
            case "l" -> 3;
            default -> 4;
        };
        return result;
    }

    /**
     * 模式匹配
     */
    private static void patternMatching(Pet pet) {
        switch (pet) {
            case Dog dog -> dog.shout();
            case Cat cat -> cat.walk();
            case Pet p -> p.feed();
        }
    }
}
