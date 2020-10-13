package com.company;

import java.util.Dictionary;
import java.util.Hashtable;

public class Main {

    public static void main(String[] args)
    {
        /*
         * Задача:
         * Калькулятор для проведения 4-х арифметических действий (+,-,*,/)
         * На входе получает строку в формате (число знак число)
         * Число может выражаться римскими или арабскими числами
         * Если в строке присутствует и тот и другой формат чисел выбрасывается исключение
         *
         * */
        Calculater calculate = Calculater.CreateCalculater();
        calculate.Start();



    }
}
