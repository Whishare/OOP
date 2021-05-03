package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("AsdsdsdsdaSD"));
    }
    //Главный метод - возвращает размер наименьшей сбалансированной строки
    public int solution(String s) {
        List<String> substrings = new ArrayList<String>();
        String finalStr = "";
        int minLength = s.length();
        subString(s, s.length(), substrings);
        for(String str : substrings) {
            if (isBalanced(str)) {
                if (str.length() < s.length()) {
                    minLength = str.length();
                    finalStr = str;
                }
            }
        }
        System.out.println(finalStr);
        if (minLength == s.length() && !isBalanced(s)) return -1;
        else return minLength;
    }
    //Метод который возвращает истину если строка сбалансирована
    private boolean isBalanced(String s) {
        String upper = "";
        String lower = "";
        for(int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                upper += s.charAt(i);
            } else {
                lower += s.charAt(i);
            }
        }
        boolean finded = false;
        for(int i = 0; i < upper.length(); i++) {
            finded = false;
            for(int j = 0; j < lower.length(); j++) {
                if (Character.toLowerCase(upper.charAt(i)) == lower.charAt(j)) {
                    finded = true;
                }
            }
        }
        for(int i = 0; i < lower.length(); i++) {
            finded = false;
            for(int j = 0; j < upper.length(); j++) {
                if (Character.toUpperCase(lower.charAt(i)) == upper.charAt(j)) {
                    finded = true;
                }
            }
        }
        return finded;
    }
    //Метод которые разбивает строку на все возможные подстроки из нее
    private void subString(String str, int n, List<String> list)
    {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                list.add(str.substring(i, j));
            }
        }
    }
}
