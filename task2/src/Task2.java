
import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) {
        int[] mas = {2, 4, 6};
        System.out.println(isAvgWhole(mas));
    }
    // Создайте функцию, которая повторяет каждый символ в строке n раз
    public static String repeat(String word, int n) {
        String newWord = " ";
        for (int i = 0; i < word.length(); i++)
            for (int j = 0; j < n; j++)
                newWord += word.charAt(i);
        return newWord;
    }
    // Создайте функцию, которая принимает массив и возвращает разницу между
    // самыми большими и самыми маленькими числами.
    public static int differenceMaxMin(int[] mas){
        int max = mas[0];
        int min = mas[0];
        for (int i : mas) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        return max - min;
    }
    // Создайте функцию, которая принимает массив в качестве аргумента и возвращает
    // true или false в зависимости от того, является ли среднее значение всех элементов
    // массива целым числом или нет.
    public static boolean isAvgWhole(int[] mas) {
        double sum = 0;
        for (int i : mas)
            sum += i;
        if (sum == (int)sum)
            return true;
        return false;
    }
    // Создайте метод, который берет массив целых чисел и возвращает массив, в
    // котором каждое целое число является суммой самого себя + всех предыдущих чисел в массиве.
    public static int[] cumulativeSum(int[] mas) {
        for (int i = 1; i < mas.length; i++)
            mas[i] += mas[i-1];
        return mas;
    }
    // Создайте функцию, которая возвращает число десятичных знаков, которое имеет
    // число (заданное в виде строки). Любые нули после десятичной точки
    // отсчитываются в сторону количества десятичных знаков.
    public static int getDecimalPlaces(String number) {
        if (number.indexOf('.') != 0)
            return number.length() - number.indexOf('.') - 1;
        return 0;
    }
    // Создайте функцию, которая при заданном числе возвращает соответствующее число Фибоначчи.
    public static int Fibonacci (int n){
        if (n == 0 || n == 1) return 1;
        if (n > 1) return Fibonacci(n - 2) + Fibonacci(n - 1);
        return Fibonacci(n + 2) - Fibonacci(n + 1);
    }
    // Почтовые индексы состоят из 5 последовательных цифр. Учитывая строку,
    // напишите функцию, чтобы определить, является ли вход действительным
    // почтовым индексом. Действительный почтовый индекс выглядит следующим образом:
    // – Должно содержать только цифры (не допускается использование нецифровых цифр).
    // – Не должно содержать никаких пробелов.
    // – Длина не должна превышать 5 цифр.
    public static boolean isValid(String index) {
        if (index.length() != 5) return false;
        for (int i = 0; i < index.length(); i++)
            if (!Character.isDigit(index.charAt(i)) || index.charAt(i) == ' ')
                return false;
        return true;
    }
    // Пара строк образует странную пару, если оба из следующих условий истинны:
    // – Первая буква 1-й строки = последняя буква 2-й строки.
    // – Последняя буква 1-й строки = первая буква 2-й строки.
    // – Создайте функцию, которая возвращает true, если пара строк представляет собой
    //странную пару, и false в противном случае.
    public static boolean isStrangePair (String word1, String word2) {
        return word1.charAt(0) == word2.charAt(word2.length() - 1) &&
                word2.charAt(0) == word2.charAt(word1.length() - 1);
    }
    // Создайте две функции: isPrefix(word, prefix-) и isSuffix (word, -suffix).
    // – isPrefix должен возвращать true, если он начинается с префиксного аргумента.
    // – isSuffix должен возвращать true, если он заканчивается аргументом суффикса.
    // – В противном случае верните false.
    public static boolean isPrefix(String word, String pref) {
        pref = pref.substring(0,  pref.length() - 1);
        return word.startsWith(pref);
    }
    public static boolean isSuffix(String word, String suf){
        suf = suf.substring(1);
        return word.endsWith(suf);
    }
    // Создайте функцию, которая принимает число (шаг) в качестве аргумента и
    // возвращает количество полей на этом шаге последовательности.
    // Шаг 0: начните с 0
    // Шаг 1: Добавьте 3
    // Шаг 2: Вычтите 1
    // Повторите Шаги 1 И 2 ...
    public static int boxSeq(int step){
        if (step == 0) return 0;
        int count = 0;
        for (int i = 0; i < step; i++)
            if (i % 2 != 0) count--;
            else count += 3;
        return count;
    }
}