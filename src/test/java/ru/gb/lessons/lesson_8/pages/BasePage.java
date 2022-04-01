package ru.gb.lessons.lesson_8.pages;

import ru.gb.lessons.lesson_8.pages.block.MainHeader;

public class BasePage {

//    public BasePage(WebDriver webDriver) {
//        super(webDriver);
//    }

    public MainHeader getHeader()  {
        return new MainHeader();
    }
}
