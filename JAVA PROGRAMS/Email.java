import java.util.Scanner;

public class Email{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter an email address:");
        String email = scanner.nextLine();

        System.out.println(email +"is" +": " + isEmailValid(email));

     
    }

    public static boolean isEmailValid(String email) {
        String[] s1 = email.split("@");

        if (s1.length != 2)
        {
            return false;
        }

        if (s1[0].length() == 0 || s1[1].length() < 3)
        {
            return false;
        }

        if (!s1[1].contains("."))
        {
            return false;
        }

        if (!Character.isLetter(s1[0].charAt(0)))
        {
            return false;
        }
         if (email.contains("..") || email.contains(".@") || email.contains("@.") || email.contains("._."))
        {
            return false;
        }

        if (email.endsWith("."))
        {
            return false;
        }

        for (char c : email.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_' && c != '.' && c != '@')
            {
                return false;
            }
        }

       

        return true;
    }
}
