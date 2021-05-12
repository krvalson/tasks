import java.util.ArrayList;

public class Task4 {
    public static void main(String[] args) {
        // write your code here
    }
    // Бесси работает над сочинением для своего класса писателей. Поскольку ее
    // почерк довольно плох, она решает напечатать эссе с помощью текстового
    // процессора. Эссе содержит N слов (1≤N≤100), разделенных пробелами.
    // Каждое слово имеет длину от 1 до 15 символов включительно и состоит
    // только из прописных или строчных букв. Согласно инструкции к заданию,
    // эссе должно быть отформатировано очень специфическим образом: каждая
    // строка должна содержать не более K (1≤K≤80) символов, не считая пробелов.
    // К счастью, текстовый процессор Бесси может справиться с этим требованием,
    // используя следующую стратегию:
    // – Если Бесси набирает Слово, и это слово может поместиться в текущей
    // строке, поместите его в эту строку. В противном случае поместите слово
    // на следующую строку и продолжайте добавлять к этой строке. Конечно,
    // последовательные слова в одной строке все равно должны быть разделены
    // одним пробелом. В конце любой строки не должно быть места.
    // – К сожалению, текстовый процессор Бесси только что сломался. Пожалуйста,
    // помогите ей правильно оформить свое эссе! Вам будут даны n, k и строка
    public static String Bessy(int n, int k, String str) {
        String result = "";
        int size = 0;
        for (String word: str.split(" "))
            if (size + word.length() > k) {
                result = result.trim() + "\n" + word + " ";
                size = word.length();
            } else {
                result += word + " ";
                size += word.length();
            }
        return result.trim();
    }
    // Напишите функцию, которая группирует строку в кластер скобок.
    // Каждый кластер должен быть сбалансирован.
    public static String[] split(String str) {
        ArrayList<String> list = new ArrayList<String>();
        int level = 0;
        String cluster = "";
        for (int i = 0; i < str.length(); i++)
        {
            cluster += str.charAt(i);
            if (str.charAt(i) == '(') level++;
            else level--;
            if (level == 0)
            {
                list.add(cluster);
                cluster = "";
            }
        }
        return list.toArray(new String[list.size()]);
    }
    // Создайте две функции toCamelCase () и toSnakeCase (), каждая из которых берет
    // одну строку и преобразует ее либо в camelCase, либо в snake_case.
    public static String toCamelCase(String str) {
        for (int i = 1; i < str.length(); i++)
            if (str.charAt(i) == '_')
                str = str.substring(0, i) + str.substring(i + 1, i + 2).toUpperCase() + str.substring(i + 2, str.length());
        return str;
    }
    public static String toSnakeCase(String str) {
        return str.replaceAll("([A-Z])", "_$0").toLowerCase();
    }
    // Напишите функцию, которая вычисляет сверхурочную работу и оплату, связанную с сверхурочной работой.
    // Работа с 9 до 5: обычные часы работы
    // После 5 вечера это сверхурочная работа
    // Ваша функция получает массив с 4 значениями:
    //      – Начало рабочего дня, в десятичном формате, (24-часовая дневная нотация)
    //      – Конец рабочего дня. (Тот же формат)
    //      – Почасовая ставка
    //      – Множитель сверхурочных работ
    //      Ваша функция должна возвращать:
    //      $ + заработанные в тот день (округлены до ближайшей сотой)
    public static String overTime(double[] work) {
        double sum = 0;
        double normal_hours = Math.min(17, work[1]) - work[0];
        if (normal_hours >= 0)
            sum += normal_hours * work[2];
        if (work[1] - 17 >= 0)
            sum += (work[1] - 17) * work[2] * work[3];
        return ("$" + String.valueOf(sum));
    }
    // Индекс массы тела (имт) определяется путем измерения вашего веса
    // в килограммах и деления на квадрат вашего роста в метрах. Категории имт таковы:
    // Недостаточный вес: <18,5
    // Нормальный вес: 18.5-24.9
    // избыточный вес: 25 и более. Создайте функцию, которая будет принимать вес
    // и рост (в килограммах, фунтах, метрах или дюймах) и возвращать имт
    // и связанную с ним категорию. Округлите имт до ближайшей десятой.
    public static String BMI(String weightStr, String heightStr) {
        double weight = Double.parseDouble(weightStr.split(" ")[0]);
        double height = Double.parseDouble(heightStr.split(" ")[0]);
        if (weightStr.contains("pounds"))
            weight *= 0.453592;
        if (heightStr.contains("inches"))
            height *= 0.0254;
        double BMI = Math.round((weight / (height * height)) * 10.0) / 10.0;
        if (BMI < 18.5)
            return BMI + " Underweight";
        if (BMI >= 18.5 && BMI <= 24.9)
            return BMI + " Normal weight";
        return BMI + " Overweight";
    }
    // Создайте функцию, которая принимает число и возвращает его мультипликативное
    // постоянство, которое представляет собой количество раз, которое вы должны
    // умножать цифры в num, пока не достигнете одной цифры.
    public static int bugger(int num) {
        int count = 0;
        while (num > 9) {
            int chnum = 1;
            while (num > 0) {
                chnum *= num % 10;
                num /= 10;
            }
            num = chnum;
            count++;
        }
        return count;
    }
    // Напишите функцию, которая преобразует строку в звездную стенографию. Если
    // символ повторяется n раз, преобразуйте его в символ*n.
    public static String toStarShorthand(String str) {
        String res = "";
        int i = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
            int prevIndex = i;
            res += c;
            while (i < str.length() && str.charAt(i) == c) i++;
            int count = i - prevIndex;
            if (count > 1) res += "*" + count;
        }
        return res;
    }
    // Создайте функцию, которая возвращает true, если две строки рифмуются, и false в
    // противном случае. Для целей этого упражнения две строки рифмуются, если
    // последнее слово из каждого предложения содержит одни и те же гласные.
    public static boolean isVowel(char c) {
        String vowels = "aeiouy";
        for (int i = 0; i < vowels.length(); i++)
            if (vowels.charAt(i) == c) return true;
        return false;
    }
    public static boolean doesRhyme(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        int prevStr1Index = str1.length() - 1;
        int prevStr2Index = str2.length() - 1;
        while (prevStr1Index >= 0 && prevStr2Index >= 0 && str1.charAt(prevStr1Index) != ' ')
        {
            while (prevStr1Index >= 0 && !isVowel(str1.charAt(prevStr1Index)))
                prevStr1Index--;
            while (prevStr2Index >= 0 && !isVowel(str2.charAt(prevStr2Index)))
                prevStr2Index--;
            if (str1.charAt(prevStr1Index) != str2.charAt(prevStr2Index))
                return false;
            prevStr1Index--;
            prevStr2Index--;
        }
        return true;
    }
    // Создайте функцию, которая принимает два целых числа и возвращает true, если
    // число повторяется три раза подряд в любом месте в num1 и то же самое число
    // повторяется два раза подряд в num2.
    public static int[] countDigits(long num) {
        int[] counter = new int[10];
        while (num > 0) {
            counter[(int)(num % 10)]++;
            num /= 10;
        }
        return counter;
    }
    public static boolean trouble(long a, long b){
        int[] a_counter = countDigits(a);
        int[] b_counter = countDigits(b);
        for (int i = 0; i < 10; i++)
            if (a_counter[i] == 3 && b_counter[i] == 2)
                return true;
        return false;
    }
    // Предположим, что пара одинаковых символов служит концами книги для всех
    // символов между ними. Напишите функцию, которая возвращает общее количество
    // уникальных символов (книг, так сказать) между всеми парами концов книги.
    public static int countUniqueBooks(String str, char end) {
        boolean[] bookshelf = new boolean[128];
        boolean open = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == end) open = !open;
            else if (open) bookshelf[str.charAt(i)] = true;
        }
        int count = 0;
        for (int i = 0; i < 128; i++)
            if (bookshelf[i]) count++;
        return count;
    }
}