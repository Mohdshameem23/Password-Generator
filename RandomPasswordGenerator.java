import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomPasswordGenerator {

    // Available character sets for the password
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_+=<>?";

    // Method to generate a  password
    public static String generatePassword(int length, boolean includeUppercase, boolean includeLowercase,
                                          boolean includeDigits, boolean includeSpecialChars) {
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);
        List<String> charCategories = new ArrayList<>();

        if (includeUppercase) {
            charCategories.add(UPPERCASE);
            password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        }
        if (includeLowercase) {
            charCategories.add(LOWERCASE);
            password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        }
        if (includeDigits) {
            charCategories.add(DIGITS);
            password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        if (includeSpecialChars) {
            charCategories.add(SPECIAL_CHARS);
            password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));
        }

        for (int i = password.length(); i < length; i++) {
            String randomCategory = charCategories.get(random.nextInt(charCategories.size()));
            password.append(randomCategory.charAt(random.nextInt(randomCategory.length())));
        }

        return shuffleString(password.toString(), random);
    }

    public static String shuffleString(String input, Random random) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while (!characters.isEmpty()) {
            int randomIndex = random.nextInt(characters.size());
            output.append(characters.remove(randomIndex));
        }
        return output.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Asking the user for input to customize the password
        System.out.print("Enter the length of the password: ");
        int length = scanner.nextInt();

        System.out.print("Include uppercase letters? (yes/no): ");
        boolean includeUppercase = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include lowercase letters? (yes/no): ");
        boolean includeLowercase = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include digits? (yes/no): ");
        boolean includeDigits = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include special characters? (yes/no): ");
        boolean includeSpecialChars = scanner.next().equalsIgnoreCase("yes");

        if (!includeUppercase && !includeLowercase && !includeDigits && !includeSpecialChars) {
            System.out.println("Error: You must select at least one character set!");
        } else {
            // Generate the password
            String password = generatePassword(length, includeUppercase, includeLowercase, includeDigits, includeSpecialChars);
            System.out.println("Generated Password: " + password);
        }

    }
}
