package com.chenx.oopadvance

/*
类委托
    当你需要扩展或重写某个类的特定方法时，而不想要使用继承来重写。
    通过组合的方式，需要手动委托所有的方法，kotlin提供类委托的方式。类委托简化了手动委托需要处理非目标函数的要求
 */

interface Controls {
    fun up(velocity: Int): String
    fun down(velocity: Int): String
    fun left(velocity: Int): String
    fun right(velocity: Int): String
    fun forward(velocity: Int): String
    fun back(velocity: Int): String
    fun turboBoost(): String
}

class SpaceShipControls : Controls {
    override fun up(velocity: Int) =
        "up $velocity"
    override fun down(velocity: Int) =
        "down $velocity"
    override fun left(velocity: Int) =
        "left $velocity"
    override fun right(velocity: Int) =
        "right $velocity"
    override fun forward(velocity: Int) =
        "forward $velocity"
    override fun back(velocity: Int) =
        "back $velocity"
    override fun turboBoost() = "turbo boost"
}

// 没有类委托的情况下
class ManualDelegation : Controls{
    private val controls = SpaceShipControls()
    override fun up(velocity: Int): String = controls.up(velocity)

    override fun down(velocity: Int): String = controls.down(velocity)

    override fun left(velocity: Int): String = controls.left(velocity)

    override fun right(velocity: Int): String = controls.right(velocity)

    override fun forward(velocity: Int): String = controls.forward(velocity)

    override fun back(velocity: Int): String = controls.back(velocity)

    /**
     * 要修改的方法。上面所有方法的手动委托都是多余的
     */
    override fun turboBoost(): String = controls.turboBoost() +  " 修改之后"
}

// kotlin的类委托
class ClassDelegation(private val controls: SpaceShipControls) : Controls by controls {
    // 这样只需要重写定义需要修改的方法就可以了
    override fun turboBoost(): String {
        return controls.turboBoost() + " after class delegation modify..."
    }
}

fun operateTurbo(controls: Controls) {
    println(controls.turboBoost())
}

fun main() {
    // 可以实现同样的功能
    operateTurbo(SpaceShipControls())
    operateTurbo(ManualDelegation())
    operateTurbo(ClassDelegation(SpaceShipControls()))
}