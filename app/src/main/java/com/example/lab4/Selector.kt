package com.example.lab4;

class Selector {
    private val List = listOf(
        Question("11111111", true),
        Question("22222", true),
        Question("333333333", true),
        Question("4444", false),
        Question("555555", false),
    )

    private var nowQuest:Int = 0
    private var points:Int = 0

    fun getQuest(): String {
        if (nowQuest < List.count()) {
            return List[nowQuest].text
        }
        return ""
    }

    fun doAnswer(answer:Boolean) {
        if (answer == List[nowQuest].answer) points++
        nowQuest++
    }

    fun skip(): String {
        nowQuest++
        return getQuest()
    }

    fun getPoints():Int {
        return points
    }

    fun isFinish():Boolean {
        return nowQuest >= List.count()
    }

    fun restart() {
        nowQuest = 0
        points = 0
    }
}
