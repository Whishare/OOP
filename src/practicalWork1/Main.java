package practicalWork1;

public class Main {
    //Створіть метод, який приймає будь яку кількість елементів int[] та повертає масив,
    //який складається тільки з елементів, які є в одному екземплярі серед усіх масивів.
    private int[] mergeArrays(int[] ...arrays) {
        int summaryLength = 0;
        for(int[] i : arrays) {
            summaryLength += i.length;
        }
        int[] merged = new int[summaryLength];
        for(int i = 0, k = 0; i < arrays.length; i++) {
            for(int j = 0; j < arrays[i].length; j++, k++) {
                merged[k] = arrays[i][j];
            }
        }
        return merged;
    }
    private int[] findUnique(int[] array) {
        int summaryLength = 0;
        int[] unique = new int[array.length];
        for(int i = 0; i < array.length; i++) {
            int same = 0;
            for(int j = 0; j < array.length; j++) {
                if (array[i] == array[j]) {
                    same++;
                }
            }
            if (same <= 1) {
                unique[summaryLength] = array[i];
                summaryLength++;
            }
        }
        int[] finalResult = new int[summaryLength];
        for(int i = 0; i < finalResult.length; i++) {
            finalResult[i] = unique[i];
        }
        return finalResult;
    }
    public int[] findUniqueIn(int[] ...arrays) {
        return findUnique(mergeArrays(arrays));
    }
    //Створіть метод, який дозволяє поєднати між собою будь яку кількість масивів int[]
    //та повернути результуючий масив, який буде складатися виключно з простих чисел, які були у складі масивів.
    private boolean isSimple(int number) {
        if (number % 2 == 0 && number != 2) return false;
        for(int i = 3; i < number; i+=2) {
            if (number % i == 0) return false;
        }
        return true;
    }
    private int[] findSimple(int[] array) {
        int[] simple = new int[array.length];
        int summaryLength = 0;
        for(int i : array) {
            if(isSimple(i)) {
                simple[summaryLength] = i;
                summaryLength++;
            }
        }
        int[] finalResult = new int[summaryLength];
        for(int i = 0; i < finalResult.length; i++) {
            finalResult[i] = simple[i];
        }
        return finalResult;
    }
    public int[] findSimpleIn(int[] ...nums) {
        return findSimple(mergeArrays(nums));
    }
    //Створіть метод, який приймає два параметри типу String та повертає кількість
    //повних повторів одного слова літерами другого.
    //Приклад: слово =він= можливо набрати три рази літерами , які є у рядку =ннніііввввукег=
    public int countWords(String arg1, String arg2) {
        char[] compared = arg2.toCharArray();
        char[] unique = new char[compared.length];
        int k = 0;
        for(int i = 0; i < compared.length; i++) {
            boolean founded = false;
            for(int j = 0; j < unique.length; j++) {
                if (compared[i] == unique[j]) {
                    founded = true;
                }
            }
            if (!founded) {
                unique[k] = compared[i];
                k++;
            }
        }
        char[] finalUnique = new char[k];
        int[] finalNumbers = new int[k];
        for(int i : finalNumbers) i = 1;
        for(int i = 0; i < finalUnique.length; i++) {
            finalUnique[i] = unique[i];
        }
        for(int i = 0; i < compared.length; i++) {
            for(int j = 0; j < finalUnique.length; j++) {
                if (compared[i] == finalUnique[j]) {
                    finalNumbers[j]++;
                }
            }
        }
        char[] goal = arg1.toCharArray();
        char[] unique2 = new char[compared.length];
        int r = 0;
        for(int i = 0; i < goal.length; i++) {
            boolean founded = false;
            for(int j = 0; j < unique2.length; j++) {
                if (goal[i] == unique2[j]) {
                    founded = true;
                }
            }
            if (!founded) {
                unique2[r] = goal[i];
                r++;
            }
        }
        char[] finalUnique2 = new char[r];
        int[] finalNumbers2 = new int[r];
        for(int i : finalNumbers2) i = 1;
        for(int i = 0; i < finalUnique2.length; i++) {
            finalUnique2[i] = unique2[i];
        }
        for(int i = 0; i < goal.length; i++) {
            for(int j = 0; j < finalUnique2.length; j++) {
                if (goal[i] == finalUnique2[j]) {
                    finalNumbers2[j]++;
                }
            }
        }
        char[] finalUnique3 = finalUnique2.clone();
        int[] finalNumbers3 = new int[finalNumbers2.length];
        for(int c : finalNumbers3) c = 0;
        for(int i = 0; i < finalUnique3.length; i++) {
            for(int j = 0; j < finalUnique.length; j++) {
                if (finalUnique3[i] == finalUnique[j]) {
                    finalNumbers3[i] = finalNumbers[j];
                }
            }
        }
        int[] number = new int[finalNumbers2.length];
        for(int i = 0; i < finalUnique2.length; i++) {
            number[i] = (finalNumbers3[i]/finalNumbers2[i]);
        }
        int finalResult = number[0];
        for(int i : number) {
            if (i < finalResult) finalResult = i;
        }
        return finalResult;
    }
    //Створіть метод, який приймає параметр String у форматі "11.01.21" та повертає кількість діб з початку року до цієї дати.
    //Ви повинні врахувати, що у лютому може бути різна кількість дат.
    public int countDaysFromYear(String arg) {
        String[] result = arg.split("[.]");
        int[] months = {31,28,31,30,31,30,31,31,30,31,30,31};
        int day = Integer.parseInt(result[0]);
        int month = Integer.parseInt(result[1]);
        int year = Integer.parseInt(result[2]);
        if ((year % 4) == 0 && ((year%100)!=0) || ((year%400)==0)) {
            months[1] = 29;
        }
        int summaryDays = day;
        for(int i = 1; i < month; i++) {
            summaryDays+=months[i];
        }
        return summaryDays;
    }
    //Створіть метод, який рахує факторіал числа, використовуючи цикли.
    //Потім створити ще одну реалізацію, яка використовує рекурсію для вирішення цієї задачі.
    public int factorialCycle(int number) {
        int factorial = 1;
        for(int i = 1; i <= number; i++) {
            factorial*=i;
        }
        return factorial;
    }
    public int factorialRecursive(int number) {
        if (number == 1) return number;
        else {
            return factorialRecursive(number-1)*number;
        }
    }
    public static void main(String[] args) {

    }
}