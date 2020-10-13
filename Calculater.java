package com.company;

import java.util.Objects;
import java.util.Scanner;

public class Calculater
{
    private static Calculater calculater = null;
    private Calculater() { }
    public static Calculater CreateCalculater()
    {
        if (calculater == null)
            calculater = new Calculater();
        return calculater;
    }

    public void Start()
    {
        Help();
        Cycle();
    }
    private void Help()
    {
        System.out.print("Введите выражение в формате: а + b. ");
        System.out.println("Где, а и b простые числа либо римские цифры.");
        System.out.println("После чего нажмите ввод(клавиша - enter). ");
        System.out.println("Для выхода введите \"exit\".");
        System.out.println("Для отображения подсказки введите \"help\".\n");
    }

    private void Cycle()
    {
    String input, answer = null;                // Входные данные, ответ.
    String[] delimiter = {"+","-","*","/"};     // Используем знаки в качестве разделителя.
    String[] romanNumber = {"","I","II","III","IV","V","VI","VII","VIII","IX","X"};  // Массив, римских цифр.
    boolean exit = false;                       // Переменная для выхода из цикла.
    int intArgument1 = 0, intArgument2 = 0, action = 0; // Переменные для хранения значений аргументов и действия.
    int output = 0;                             // Переменная для выходного значения в качестве ответа.
    boolean roman;                              // Если используются римские цифры.


        while(!exit) {
            System.out.print("Введите выражение: ");
            Scanner inputStr = new Scanner(System.in);            // Испоьзуем класс Scanner для получения данных.
            // Читаем введенную строку, исключая пробелы, если они есть.
            input = inputStr.nextLine().replaceAll("\\s+", "").toUpperCase();
            System.out.println(input);

            try {

                if(Objects.equals(input, ""))
                {
                    System.out.println("Пустая строка.");
                    continue;
                }
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

            if (Objects.equals(input, "HELP"))
            {
                Help();
                continue;
            }
            // Формирование условия для выхода из цикла.
            if (Objects.equals(input, "EXIT")) exit = true;




            // Делим строку на два аргумента.
            String[] inputSplit = input.split("[+\\-*/]");

            // Добвить обработку исключения.
//            try
//            {
//                Boolean inputEx = input.contains(romeNumber[1]);
//            }
//            catch (IOError e)
//            {
//                System.out.println("Ошибка. Недопустимый ввод.");
//                System.out.println(e.getMessage());
//            }

            // Определяем какой знак используется в качестве разделителя.
            for (int i = 0; i < delimiter.length; i++) {
                if (input.contains(delimiter[i])) {
                    action = i;
                    break;
                }
            }

            // Находим римскую цифру в массиве.
            if (input.contains(romanNumber[1]) || input.contains(romanNumber[5]) || input.contains(romanNumber[10]) && !exit) {
                // Если римские цифры есть, определяем их значение.
                // Порядковый номер итерации, соответствует введенному символу римской цифры.

                    roman = true;
                try {
                    for (int i = 0; i < romanNumber.length; i++) {

                        if (romanNumber[i].equals(inputSplit[0])) intArgument1 = i;
                        if (romanNumber[i].equals(inputSplit[1])) intArgument2 = i;

                    }
                    for(int i=0;i<10;i++)
                    {
                        if (inputSplit[1].contains(String.valueOf(i)) | inputSplit[0].contains(String.valueOf(i))) {
                            System.out.println("Неверный формат записи.");
                            exit = true;
                            break;
                        }
                    }
                } catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
            } else {
                // Если римских цифр нет, работаем с целыми числами из массива.
                roman = false;  // Сброс признака.
                if (!exit) {
                    try {
                        intArgument1 = Integer.parseInt(inputSplit[0]);
                        intArgument2 = Integer.parseInt(inputSplit[1]);
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    }
            }

            // Выбор действия над аргументами.
            switch (action) {
                case (0): // сложение.
                    output = intArgument1 + intArgument2;
                    answer = String.valueOf(output);
                    break;
                case (1): // вычитание.
                    output = intArgument1 - intArgument2;
                    answer = String.valueOf(output);
                    break;
                case (2): // умножение.
                    output = intArgument1 * intArgument2;
                    answer = String.valueOf(output);
                    break;
                case (3): // деление(используем тип double).
                    output = intArgument1 / intArgument2;
                    answer = String.valueOf((double) intArgument1 / (double) intArgument2);
                    break;
                default:
                    break;
            }
            // Формируем условие образования ответа в римских цифрах.
            if (roman && !exit) {
                if (output >= 10 && output < 20) {
                    answer = "X" + romanNumber[output - 10];
                } else if (output >= 20 && output < 30) {
                    answer = "XX" + romanNumber[output - 20];
                } else if (output >= 30 && output < 40) {
                    answer = "XXX" + romanNumber[output - 30];
                } else if (output >= 40 && output < 50) {
                    answer = "XL" + romanNumber[output - 40];
                } else if (output >= 50 && output < 60) {
                    answer = "L" + romanNumber[output - 50];
                } else if (output >= 60 && output < 70) {
                    answer = "LX" + romanNumber[output - 60];
                } else if (output >= 70 && output < 80) {
                    answer = "LXX" + romanNumber[output - 70];
                } else if (output >= 80 && output < 90) {
                    answer = "LX" + "XX" + romanNumber[output - 80];
                } else if (output >= 90 && output < 100) {
                    answer = "XC" + romanNumber[output - 90];
                } else if (output == 100) {
                    answer = "C";
                } else {
                    answer = romanNumber[output];
                }
            }
            // Искользуем тернарный оператор для получения ответа или выхода.
            String out = !exit ? ("Ответ: " + answer) : "Выход из программы.";
            System.out.println(out);
        }
    }
}
