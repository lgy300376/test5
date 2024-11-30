import java.util.Scanner;

public class PalindromeMaker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("请输入一个字符串: ");
        String input = scanner.nextLine();
        
        if (input == null || input.isEmpty()) {
            System.out.println("输入的字符串为空或无效！");
        } else {
            System.out.println("原始字符串: " + input);
            String result = makePalindrome(input);
            System.out.println("生成的回文串: " + result);
        }
        
        scanner.close();
    }

    private static String makePalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            String prefix = s.substring(0, i);
            String suffix = s.substring(i);
            if (isPalindrome(suffix)) {
                // 如果后缀已经是回文了，那么只需要反转前缀并添加到原字符串末尾
                StringBuilder sb = new StringBuilder(prefix).reverse();
                return s + sb.toString();
            }
        }
        // 如果整个字符串都不是回文（这通常不会发生，因为单个字符总是回文），则直接返回原字符串加上其反转
        return s + new StringBuilder(s).reverse().toString();
    }

    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}