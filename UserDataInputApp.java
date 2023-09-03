import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserDataInputApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter data (Last name First name Middle name Date of birth Phone number Gender):");
        String input = scanner.nextLine();
        
        String[] userData = input.split(" ");
        
        if (userData.length != 6) {
            System.err.println("Error: Invalid amount of data entered.");
            return;
        }
        
        String lastName = userData[0];
        String firstName = userData[1];
        String middleName = userData[2];
        String birthDateStr = userData[3];
        String phoneNumberStr = userData[4];
        String genderStr = userData[5];
        
        // Попытка парсинга даты рождения
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date birthDate;
        try {
            birthDate = dateFormat.parse(birthDateStr);
        } catch (ParseException e) {
            System.err.println("Error: Invalid date of birth format.");
            e.printStackTrace();
            return;
        }
        
        // Попытка парсинга номера телефона
        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(phoneNumberStr);
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid phone number format.");
            e.printStackTrace();
            return;
        }
        
        // Проверка пола
        if (!genderStr.equals("f") && !genderStr.equals("m")) {
            System.err.println("Error: Invalid gender format.");
            return;
        }
        
        // Создание строки для записи в файл
        String userDataString = lastName + " " + firstName + " " + middleName + " " +
                                dateFormat.format(birthDate) + " " + phoneNumber + " " + genderStr;
        
        // Создание файла и запись данных
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastName + ".txt"))) {
            writer.write(userDataString);
            writer.newLine();
            System.out.println("The data was successfully written to the file.");
        } catch (IOException e) {
            System.err.println("Error writing data to file.");
            e.printStackTrace();
        }
    }
}