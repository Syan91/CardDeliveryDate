package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.By.cssSelector;
import static ru.netology.web.DataGenerator.*;
import static ru.netology.web.DataGenerator.getCorrectDate;

class CardDeliveryDateTest {
    private SelenideElement form;
    private final String city = getRandomCity();
    private final String name = getRandomName();
    private final String date = getCorrectDate(3);
    private final String today = getInCorrectDate();
    private final String phone = getRandomPhone();
    private final String differentDate = getCorrectDate(10);


        @BeforeEach
        void setup() {
            open("http://localhost:9999");
            form = $("[action]");
            form.$(cssSelector("[data-test-id=city] input")).sendKeys(city);
            form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        }

        @Test
        void ShouldAssignDelivery() {
            form.$("[data-test-id=date] input").sendKeys(date);
            form.$("[name=name]").sendKeys(name);
            form.$("[name=phone]").sendKeys(phone);
            form.$("[data-test-id=agreement]").click();
            form.$(byText("Запланировать")).click();
            $((".notification__content")).waitUntil(Condition.visible, 15000).shouldHave(text(date));
        }
    @Test
    void ShouldReAssignDelivery() {
        form.$("[data-test-id=date] input").sendKeys(date);
        form.$("[name=name]").sendKeys(name);
        form.$("[name=phone]").sendKeys(phone);
        form.$("[data-test-id=agreement]").click();
        form.$(byText("Запланировать")).click();
        $((".notification__content")).waitUntil(Condition.visible, 15000).shouldHave(text(date));
        open("http://localhost:9999");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys(city);
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$("[data-test-id=date] input").sendKeys(differentDate);
        form.$("[name=name]").sendKeys(name);
        form.$("[name=phone]").sendKeys(phone);
        form.$("[data-test-id=agreement]").click();
        form.$(byText("Запланировать")).click();
        $(".notification_status_error .button").click();
        $((".notification__content")).waitUntil(Condition.visible, 15000).shouldHave(text(differentDate));
    }
    @Test
    void ShouldNotAssignDelivery() {
        form.$("[data-test-id=date] input").sendKeys(date);
        form.$("[name=name]").sendKeys(name);
        form.$("[name=phone]").sendKeys(phone);
        form.$("[data-test-id=agreement]").click();
        form.$(byText("Запланировать")).click();
        $((".notification__content")).waitUntil(Condition.visible, 15000).shouldHave(text(date));
        open("http://localhost:9999");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys(city);
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$("[data-test-id=date] input").sendKeys(today);
        form.$("[name=name]").sendKeys(name);
        form.$("[name=phone]").sendKeys(phone);
        form.$("[data-test-id=agreement]").click();
        form.$(byText("Запланировать")).click();
        form.$(byText("Заказ на выбранную дату невозможен")).waitUntil(Condition.visible, 15000);
    }
    }

