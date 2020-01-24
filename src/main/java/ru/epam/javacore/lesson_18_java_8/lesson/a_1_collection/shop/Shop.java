package ru.epam.javacore.lesson_18_java_8.lesson.a_1_collection.shop;

import ru.epam.javacore.lesson_18_java_8.lesson.a_1_collection.jdk12.SuperCollection;

public class Shop {
    SuperCollection<String> goods;

    public Shop(SuperCollection<String> goods) {
        this.goods = goods;
    }

    public void addGood(String s) {
        System.out.println();
    }

    public int getTotalNumberOfGoods() {
        return goods.size();
    }
}