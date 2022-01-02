package com.example.demo;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.demo.pages.MainPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPageTest {
    private final MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        Configuration.startMaximized = true;
        open("https://www.jetbrains.com/");
    }

    @Test
    public void search() {
        mainPage.searchButton.click();

        $(byId("header-search")).sendKeys("Selenium");
        $(byXpath("//button[@type='submit' and text()='Search']")).click();

        $(byClassName("js-search-input")).shouldHave(attribute("value", "Selenium"));
    }

    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.hover();

        $(byClassName("menu-main__popup-wrapper")).shouldBe(visible);
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeAllToolsButton.click();

        $(byClassName("products-list")).shouldBe(visible);

        assertEquals("All Developer Tools and Products by JetBrains", Selenide.title());
    }
}
