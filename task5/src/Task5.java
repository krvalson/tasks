import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task5 {
    public static void main(String[] args) {
        // write your code here
    }
    // Пришло время отправлять и получать секретные сообщения.
    // Создайте две функции, которые принимают строку и массив и возвращают
    // закодированное или декодированное сообщение.
    // Первая буква строки или первый элемент массива представляет собой символьный код
    // этой буквы. Следующие элементы-это различия между символами: например, A +3 --> C или z -1 --> y
    public static int[] encrypt(String str) {
        int[] arr = new int[str.length()];
        arr[0] = str.charAt(0);
        for (int i = 1; i < str.length(); i++)
            arr[i] = str.charAt(i) - str.charAt(i - 1);
        return arr;
    }
    public static String decrypt(int[] arr) {
        char[] str = new char[arr.length];
        str[0] = (char) arr[0];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i];
            str[i] = (char) arr[i];
        }
        return new String(str);
    }
    // Создайте функцию, которая принимает имя шахматной фигуры, ее положение и
    // целевую позицию. Функция должна возвращать true, если фигура может двигаться
    // к цели, и false, если она не может этого сделать.
    // Возможные входные данные - "пешка", "конь", "слон", "Ладья", "Ферзь"и " король".
    public static boolean canMove(String name, String start, String end) {
        char startX = start.charAt(0);
        int startY = Integer.parseInt(String.valueOf(start.charAt(1)));
        char endX = end.charAt(0);
        int endY = Integer.parseInt(String.valueOf(end.charAt(1)));
        switch (name) {
            case "Pawn":
                return startX == endX && ((startY == 2 && endY == 4) || endY == startY + 1);
            case "Knight":
                return (Math.abs(startX - endX) == 2 && Math.abs(startY - endY) == 1) || (Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 2);
            case "Bishop":
                return Math.abs(startX - endX) == Math.abs(startY - endY);
            case "Rook":
                return (startX == endX && startY != endY) || (startX != endX && startY == endY);
            case "Queen":
                return (startX == endX && startY != endY) || (startX != endX && startY == endY) || Math.abs(startX - endX) == Math.abs(startY - endY);
            case "King":
                return Math.abs(startX - endX) < 2 && Math.abs(startY - endY) < 2;
        }
        return false;
    }
    // Входная строка может быть завершена, если можно добавить дополнительные буквы,
    // и никакие буквы не должны быть удалены, чтобы соответствовать слову.
    // Кроме того, порядок букв во входной строке должен быть таким же, как и порядок букв в последнем слове.
    // Создайте функцию, которая, учитывая входную строку, определяет, может ли слово быть завершено.
    public static boolean canComplete(String str1, String str2) {
        char[] arr = str1.toCharArray();
        int num = 0;
        for (char c : arr) {
            num = str2.indexOf(String.valueOf(c), num) + 1;
            if (num == 0) return false;
        }
        return true;
    }
    // Создайте функцию, которая принимает числа в качестве аргументов, складывает их
    // вместе и возвращает произведение цифр до тех пор, пока ответ не станет длиной всего в 1 цифру.
    public static int sumDigProd(int[] arr) {
        int sum = 0;
        for (int value : arr)
            sum += value;
        while (sum > 9) {
            int mult = 1;
            while (sum > 0) {
                mult *= sum % 10;
                sum /= 10;
            }
            sum = mult;
        }
        return sum;
    }
    // Напишите функцию, которая выбирает все слова, имеющие все те же гласные
    // (в любом порядке и / или количестве), что и первое слово, включая первое слово.
    public static boolean[] vowelSet(String word) {
        String vowels = "aeiouy";
        boolean[] set = new boolean[vowels.length()];
        for (int i = 0; i < word.length(); i++) {
            int index = vowels.indexOf(word.charAt(i));
            if (index != -1) set[index] = true;
        }
        return set;
    }
    public static String[] sameVowelGroup(String[] words) {
        List<String> list = new ArrayList<String>();
        boolean[] authenticSet = vowelSet(words[0]);
        for (String word: words)
            if (Arrays.equals(authenticSet, vowelSet(word)))
                list.add(word);
        return list.toArray(new String[list.size()]);
    }
    // Создайте функцию, которая принимает число в качестве аргумента и возвращает
    // true, если это число является действительным номером кредитной карты, а в
    // противном случае-false. Номера кредитных карт должны быть длиной от 14 до 19 цифр
    // и проходить тест Луна, описанный ниже:
    //     – Удалите последнюю цифру (это"контрольная цифра").
    //     – Переверните число.
    //     – Удвойте значение каждой цифры в нечетных позициях. Если удвоенное значение имеет
    // более 1 цифры, сложите цифры вместе (например, 8 x 2 = 16 ➞ 1 + 6 = 7).
    //     – Добавьте все цифры.
    //     – Вычтите последнюю цифру суммы (из шага 4) из 10. Результат должен быть равен контрольной цифре из Шага 1.
    public static boolean validateCard(long num) {
        int checkNum = (int) num % 10;
        num /= 10;
        String reversed = new StringBuilder(String.valueOf(num)).reverse().toString();
        int sum = 0;
        for (int i = 0; i < reversed.length(); i++) {
            int c = reversed.charAt(i) - 48;
            if (i % 2 == 1) {
                c *= 2;
                if (c > 9)
                    c = c / 10 + c % 10;
            }
            sum += c;
        }
        return 10 - sum % 10 == checkNum;
    }
    // Напишите функцию, которая принимает положительное целое число от 0 до 999
    // включительно и возвращает строковое представление этого целого числа,
    // написанное на английском языке.
    public static String[] units = {
            "", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };
    public static String[] tens = {
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy",
            "eighty", "ninety"
    };
    public static String numToEng(int n) {
        if (n == 0) return "zero";
        if (n < 20) return units[n];
        if (n < 100) return tens[n / 10] + ((n % 10 != 0) ? " " : "") + numToEng(n % 10);
        return units[n / 100] + " hundred" + ((n % 100 != 0) ? " " : "") + numToEng(n % 100);
    }
    // Хеш-алгоритмы легко сделать одним способом, но по существу невозможно
    // сделать наоборот. Например, если вы хешируете что-то простое, например,
    // password123, это даст вам длинный код, уникальный для этого слова или фразы. В
    // идеале, нет способа сделать это в обратном порядке. Вы не можете взять хеш-код и
    // вернуться к слову или фразе, с которых вы начали.
    // Создайте функцию, которая возвращает безопасный хеш SHA-256 для данной строки.
    // Хеш должен быть отформатирован в виде шестнадцатеричной цифры
    public static String getSha256Hash(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] text = digest.digest(str.getBytes());
            StringBuilder strBuilder = new StringBuilder();
            for(byte val : text)
                strBuilder.append(String.format("%02x", val&0xff));
            return strBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }
    // Напишите функцию, которая принимает строку и возвращает строку с правильным
    // регистром для заголовков символов в серии "игра престолов".
    // Слова and, the, of и in должны быть строчными. Все остальные слова должны иметь
    // первый символ в верхнем регистре, а остальные-в Нижнем.
    public static String correctTitle(String title) {
        String[] words = title.toLowerCase().split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!word.equals("and") && !word.equals("the") && !word.equals("of") && !word.equals("in"))
                words[i] = word.substring(0, 1).toUpperCase() + word.substring(1);
        }
        return String.join(" ", words);
    }
    // Как указано в онлайн-энциклопедии целочисленных последовательностей:
    // Гексагональная решетка - это привычная двумерная решетка, в которой каждая точка
    // имеет 6 соседей.
    // Центрированное шестиугольное число - это центрированное фигурное число,
    // представляющее шестиугольник с точкой в центре и всеми другими точками,
    // окружающими центральную точку в шестиугольной решетке.
    // Напишите функцию, которая принимает целое число n и возвращает "недопустимое", если
    // n не является центрированным шестиугольным числом или его иллюстрацией в виде
    // многострочной прямоугольной строки в противном случае.
    public static String hexLattice(int num) {
        if (num == 1) return " o ";
        String result = "";
        int layers = num / 6;
        if (num == 7) layers += 1;
        if (layers * (layers - 1) * 3 + 1 != num) return "Invalid";
        String reversed = "";
        for (int i = 0; i < layers; i++) {
            for (int j = i+1; j < layers; j++)
                result += ' ';
            for (int j = -i; j < layers; j++)
                result += " o";
            for (int j = i; j < layers; j++)
                result += ' ';
            if (i == layers - 2)
                reversed = new StringBuilder(result).reverse().toString();
            result += '\n';
        }
        result += reversed;
        return result;
    }
}