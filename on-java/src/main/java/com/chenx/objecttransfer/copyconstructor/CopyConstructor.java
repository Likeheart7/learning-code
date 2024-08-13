package com.chenx.objecttransfer.copyconstructor;

import java.lang.reflect.Constructor;

/**
 * Java在通过构造器进行复制操作的时候，确实会因为继承关系导致复制出的类型有问题，并且部分数据可能丢失。
 */
public class CopyConstructor {
    public static void ripen(Tomato t) {
        t = new Tomato(t);
        System.out.println("in ripen, t is a " + t.getClass().getName());
    }

    public static void slice(Fruit f) {
        f = new Fruit(f);
        System.out.println("In slice, f is a " + f.getClass().getName());
    }

    @SuppressWarnings("unchecked")
    public static void ripen2(Tomato t) {
        try {
            Class c = t.getClass();
            Constructor ct = c.getDeclaredConstructor(new Class[]{c});
            Object obj = ct.newInstance(new Object[]{t});
            System.out.println("In ripen2, t is a " + obj.getClass().getName());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void slice2(Fruit f) {
        try {
            Class c = f.getClass();
            Constructor ct = c.getDeclaredConstructor(c);
            Object obj = ct.newInstance(f);
            System.out.println("In slice2, f is a " + obj.getClass().getName());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        /**
         * in ripen, t is a com.chenx.objecttransfer.copyconstructor.Tomato
         * In slice, f is a com.chenx.objecttransfer.copyconstructor.Fruit
         * java.lang.NoSuchMethodException: com.chenx.objecttransfer.copyconstructor.Tomato.<init>(com.chenx.objecttransfer.copyconstructor.Tomato)
         * java.lang.NoSuchMethodException: com.chenx.objecttransfer.copyconstructor.Tomato.<init>(com.chenx.objecttransfer.copyconstructor.Tomato)
         * in ripen, t is a com.chenx.objecttransfer.copyconstructor.Tomato
         * In slice, f is a com.chenx.objecttransfer.copyconstructor.Fruit
         * java.lang.NoSuchMethodException: com.chenx.objecttransfer.copyconstructor.GreenZebra.<init>(com.chenx.objecttransfer.copyconstructor.GreenZebra)
         * java.lang.NoSuchMethodException: com.chenx.objecttransfer.copyconstructor.GreenZebra.<init>(com.chenx.objecttransfer.copyconstructor.GreenZebra)
         */
        Tomato tomato = new Tomato();
        ripen(tomato);
        slice(tomato);  // 接收的是tomato，复制的是fruit。
        ripen2(tomato);
        slice2(tomato);
        GreenZebra g = new GreenZebra();
        ripen(g);
        slice(g);
        ripen2(g);
        slice2(g);
        g.evaluate();
    }
}

class FruitQualities {
    private int weight;
    private int color;
    private int firmness;
    private int ripeness;
    private int smell;

    FruitQualities() {

    }

    // 复制构造器
    FruitQualities(FruitQualities fq) {
        this.weight = fq.weight;
        this.color = fq.color;
        this.firmness = fq.firmness;
        this.ripeness = fq.ripeness;
        this.smell = fq.smell;
    }
}

class Seed {
    Seed() {
    }

    // 复制构造器
    Seed(Seed s) {
    }
}

class Fruit {
    private FruitQualities fq;
    private int seeds;
    private Seed[] s;

    Fruit(FruitQualities q, int seeds) {
        this.fq = q;
        this.seeds = seeds;
        this.s = new Seed[seeds];
        for (int i = 0; i < seeds; i++) {
            s[i] = new Seed();
        }
    }

    // 复制构造器
    Fruit(Fruit f) {
        this.fq = new FruitQualities(f.fq);
        this.seeds = f.seeds;
        this.s = new Seed[f.seeds];
        // 调用所有Seed的复制构造器
        for (int i = 0; i < seeds; i++) {
            s[i] = new Seed(f.s[i]);
        }
    }

    // 用于使派生的构造器放入不同的qualities
    protected void addQualities(FruitQualities fq) {
        this.fq = fq;
    }

    protected FruitQualities getQualities() {
        return this.fq;
    }
}

class Tomato extends Fruit {
    Tomato() {
        super(new FruitQualities(), 100);
    }

    Tomato(Tomato tomato) {
        // 向上转型为父复制构造器
        super(tomato);
    }
}

class ZebraQualities extends FruitQualities {
    private int stripedness;

    ZebraQualities() {
        super();
    }

    ZebraQualities(ZebraQualities z) {
        super(z);
        this.stripedness = z.stripedness;
    }
}

class GreenZebra extends Tomato {
    GreenZebra() {
        addQualities(new ZebraQualities());
    }

    GreenZebra(GreenZebra g) {
        super(g); // 调用Tomato(Tomato)
        // 恢复正确的qualities
        addQualities(new ZebraQualities());
    }

    public void evaluate() {
        ZebraQualities zq = (ZebraQualities) getQualities();
    }
}
