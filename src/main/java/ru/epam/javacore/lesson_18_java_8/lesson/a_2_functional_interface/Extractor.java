package ru.epam.javacore.lesson_18_java_8.lesson.a_2_functional_interface;

@FunctionalInterface
public interface Extractor {
    void extract();

    default void extract2(){

    }


}