import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {
    public static void main(String[] args) {
        // write your code here
    }
    // Число Белла - это количество способов, которыми массив из n элементов может
    // быть разбит на непустые подмножества. Создайте функцию, которая принимает
    // число n и возвращает соответствующее число Белла.
    public static int sterling(int n, int k) {
        if (n == k) return 1;
        if ((n > 0 && k == 0) || (n == 0 && k > 0) || k > n) return 0;
        return k * sterling(n - 1, k) + sterling(n - 1, k - 1);
    }
    public static int bell(int num) {
        int sum = 0;
        for (int k = 0; k <= num; k++)
            sum += sterling(num, k);
        return sum;
    }
    // В «поросячей латыни» (свинский латинский) есть два очень простых правила:
    // – Если слово начинается с согласного, переместите первую букву (буквы) слова до
    // гласного до конца слова и добавьте «ay» в конец.
    //      have ➞ avehay
    //      cram ➞ amcray
    //      take ➞ aketay
    //      cat ➞ atcay
    //      shrimp ➞ impshray
    //      trebuchet ➞ ebuchettray
    // – Если слово начинается с гласной, добавьте "yay" в конце слова.
    //      ate ➞ ateyay
    //      apple ➞ appleyay
    //      oaken ➞ oakenyay
    //      eagle ➞ eagleyay
    // Напишите две функции, чтобы сделать переводчик с английского на свинский латинский.
    // Первая функция translateWord (word) получает слово на английском и возвращает это
    // слово, переведенное на латинский язык. Вторая функция translateSentence (предложение)
    // берет английское предложение и возвращает это предложение, переведенное на латинский язык.
    // Примечание:
    // – Регулярные выражения помогут вам не исказить пунктуацию в предложении.
    // – Если исходное слово или предложение начинается с заглавной буквы, перевод должен сохранить свой регистр
    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
    public static String translateWord(String word) {
        if (word.length() == 0) return "";
        if (isVowel(word.charAt(0)))
            return word + "yay";
        else {
            int firstVowelIndex = 0;
            while (firstVowelIndex < word.length() && !isVowel(word.charAt(firstVowelIndex)))
                firstVowelIndex++;
            String result = word.substring(firstVowelIndex) + word.substring(0, firstVowelIndex) + "ay";
            if (Character.isUpperCase(word.charAt(0)))
                result = result.substring(0, 1).toUpperCase() + result.substring(1).toLowerCase();
            return result;
        }
    }
    public static String translateSentence(String word)
    {
        String marks = word.substring(word.length() - 1);
        String[] newWord = word.substring(0, word.length()-1).split(" ");
        for (int i = 0; i < newWord.length; i++)
            if(isVowel(newWord[i].charAt(0))) newWord[i] = newWord[i] + "yay";
            else
            {
                int firstVowelIndex = 0;
                while (firstVowelIndex < newWord[i].length() && !isVowel(newWord[i].charAt(firstVowelIndex)))
                    firstVowelIndex++;
                String result = newWord[i].substring(firstVowelIndex) + newWord[i].substring(0, firstVowelIndex) + "ay";
                if (Character.isUpperCase(newWord[i].charAt(0)))
                    result = result.substring(0, 1).toUpperCase() + result.substring(1).toLowerCase();
                newWord[i] = result;
            }
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < newWord.length; i++)
            if (i == newWord.length - 1)
                strBuilder.append(newWord[i]);
            else
                strBuilder.append(newWord[i] + " ");
        String newString = strBuilder.toString();
        newString = newString + marks;
        System.out.println(newString);
        return newString;
    }
    // Учитывая параметры RGB (A) CSS, определите, является ли формат принимаемых
    // значений допустимым или нет. Создайте функцию, которая принимает строку
    // (например, " rgb(0, 0, 0)") и возвращает true, если ее формат правильный, в
    // противном случае возвращает false.
    public static boolean validColor(String rgb) {
        String[] numbers = rgb.substring(rgb.indexOf('(')+1, rgb.indexOf(')')).split(",");
        for (int i = 0 ; i < numbers.length; i++) {
            try {
                double num = Double.parseDouble(numbers[i]);
                if (num < 0 || num > 255)
                    return false;
            } catch (Exception e) {
                // Не число
                return false;
            }
        }
        return true;
    }
    // Создайте функцию, которая принимает URL (строку), удаляет дублирующиеся
    // параметры запроса и параметры, указанные во втором аргументе (который будет необязательным массивом).
    public static String stripUrlParams (String url, String ...paramsToStrip) {
        int argsIndex = url.indexOf('?');
        if (argsIndex == -1) return url;
        String result = url.substring(0, argsIndex+1);
        LinkedHashMap<String, String> args = new LinkedHashMap<>();
        for (String keyVal : url.substring(argsIndex+1).split("&")) {
            String[] kv = keyVal.split("=");
            if (args.containsKey(kv[0])) args.replace(kv[0], kv[1]);
            else args.put(kv[0], kv[1]);
        }
        for (String key : paramsToStrip)
            args.remove(key);
        for (String key : args.keySet())
            result += key + "=" + args.get(key) + "&";
        return result.substring(0, result.length() - 1);
    }
    // Напишите функцию, которая извлекает три самых длинных слова из заголовка
    // газеты и преобразует их в хэштеги. Если несколько слов одинаковой длины,
    // найдите слово, которое встречается первым.
    public static String[] getHashTags(String title) {
        HashMap<String, Integer> words = new HashMap<>();
        title = title.toLowerCase();
        Matcher m = Pattern.compile("[a-z]+").matcher(title);
        while (m.find()) {
            String word = m.group();
            words.put(word, word.length());
        }
        return words.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .limit(3)
                .map(s -> "#" + s.getKey())
                .toArray(String[]::new);
    }
    // Последовательность Улама начинается с: ulam = [1, 2]
    // Следующее число в последовательности - это наименьшее положительное число, равное
    // сумме двух разных чисел (которые уже есть в последовательности) ровно одним
    // способом. Тривиально, это 3, так как в стартовой последовательности есть только 2 числа.
    // ulam = [1, 2, 3]
    // Следующее число 4, которое является суммой 3 + 1. 4 также равно 2 + 2, но это уравнение
    // не учитывается, так как 2 добавления должны быть различны.
    // ulam = [1, 2, 3, 4]
    // Следующее число не может быть 5, так как 5 = 1 + 4, но также и 5 = 2 + 3. Должен быть
    // только один способ сделать число Улама из 2 различных добавлений, найденных в
    // последовательности. Следующее число 6 (2 + 4). Есть 2 способа сделать 7 (1 + 6 или 3 + 4),
    // поэтому следующий - 8 (2 + 6). и так далее.
    // ulam = [1, 2, 3, 4, 6, 8, 11, 13, 16, 18, 26, 28, 36, 38, 47, 48, 53, ...]
    // Создайте функцию, которая принимает число n и возвращает n-е число в последовательности Улама.
    public static int ulam(int n) {
        TreeMap<Integer, Boolean> u = new TreeMap<>();
        u.put(1, true);
        u.put(2, true);
        int i = 3;
        while (u.size() < n) {
            int count = 0;
            for (int key : u.keySet()) {
                if (i - key != key && u.containsKey(i - key))
                    count++;
                if (count > 2)
                    break;
            }
            // находит сначала i - key а потом key - i тоесть 2 числа найдет
            if (count == 2) u.put(i, true);
            i++;
        }
        return u.lastKey();
    }
    // Напишите функцию, которая возвращает самую длинную неповторяющуюся подстроку для строкового ввода.
    public static String longestNonrepeatingSubstring(String str) {
        HashMap<Character, Integer> table = new HashMap<>();
        int end = 0;
        int index = 0;
        int max = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (table.containsKey(c))
                index = Math.max(index, table.get(c) + 1);
            table.put(c, i);
            if (max < i - index + 1) {
                max = i - index + 1;
                end = i + 1;
            }
        }
        return str.substring(end - max, end);
    }
    // Создайте функцию, которая принимает арабское число и преобразует его в римское число.
    public static int[] romanRanks = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    public static String[] romanRankStrings = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    public static String convertToRoman(int num) {
        String res = "";
        for (int i = 0; i < romanRanks.length; i++) {
            int count = num / romanRanks[i];
            for (int j = 0; j < count; j++)
                res += romanRankStrings[i];
            num -= romanRanks[i] * count;
        }
        return res;
    }
    // Создайте функцию, которая принимает строку и возвращает true или false в
    // зависимости от того, является ли формула правильной или нет.
    public static double parse(String str) {
        Matcher m = Pattern.compile("[0-9*/+-]+").matcher(str);
        double num = 0;
        boolean numOrOp = false;
        String op = "";
        while (m.find()) {
            String word = m.group();
            if (numOrOp) {
                op = word;
            } else {
                double num2 = Double.parseDouble(word);
                if (op.equals("+")) num += num2;
                else if (op.equals("-")) num -= num2;
                else if (op.equals("*")) num *= num2;
                else if (op.equals("/")) num /= num2;
                else num = num2;
            }
            numOrOp = !numOrOp;
        }
        return num;
    }
    public static boolean formula(String str) {
        String[] parts = str.split("=");
        double num = parse(parts[0]);
        for (int i = 1; i < parts.length; i++)
            if (num != parse(parts[i])) return false;
        return true;
    }
    // Число может не быть палиндромом, но его потомком может быть. Прямой потомок
    // числа создается путем суммирования каждой пары соседних цифр, чтобы создать цифры следующего числа.
    // Например, 123312 – это не палиндром, а его следующий потомок 363, где: 3 = 1 + 2; 6 = 3 + 3; 3 = 1 + 2.
    // Создайте функцию, которая возвращает значение true, если само число являетсяпалиндромом
    // или любой из его потомков вплоть до 2 цифр (однозначное число - тривиально палиндром).
    public static boolean palindromeDescendant(int number) {
        if (number < 10)
            return false;
        String str = String.valueOf(number);
        if (str.equals(new StringBuilder(str).reverse().toString()))
            return true;
        int newNum = 0;
        int count = str.length() / 2;
        for (int i = 0; i < count; i++)
            newNum += (str.charAt(i * 2) + str.charAt(i * 2 + 1) - 48 * 2) * Math.pow(10, count - i - 1);
        return palindromeDescendant(newNum);
    }
}