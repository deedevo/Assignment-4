import java.util.Scanner;
public class week {

    public class T8 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter a word:");
            String s = sc.next();
            if (isNormalDigit(s))
                System.out.println("Yes");
            else
                System.out.println("No");
        }

        public static boolean isNormalDigit(String s) {
            char [] word = s.toCharArray();
            if (s.equals("")) return false;
            return isNormalDigit(word, 0);
        }

        private static boolean isNormalDigit(char [] word, int i) {
            if (i>=word.length) return true;
            if ((word[i]>=65 && word[i]<=90) || (word[i]>=97 && word[i]<=122)) return false;
            return isNormalDigit(word,i+1);
        } //O(n)
    }
}
