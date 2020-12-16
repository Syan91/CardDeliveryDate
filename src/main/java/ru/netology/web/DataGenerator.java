package ru.netology.web;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
@Data
@NoArgsConstructor
public class DataGenerator {
    public static String getRandomCity(){
        Faker faker = new Faker(new Locale("ru"));
        return faker.address().city();
    }
    public static String getCorrectDate(int days){
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }
    public static String getInCorrectDate(){
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return today;
    }
    public static String getRandomName(){
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }
    public static String getRandomPhone(){
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }
}
