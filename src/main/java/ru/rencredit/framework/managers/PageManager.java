package ru.rencredit.framework.managers;

import ru.rencredit.framework.pages.FormPage;
import ru.rencredit.framework.pages.StartPage;

public class PageManager {
    private static PageManager pageManager;
    StartPage startPage;
    FormPage formPage;

    private PageManager() {

    }

    public static PageManager getPageManager() {
        if (pageManager == null)
            pageManager = new PageManager();
        return pageManager;
    }

    public StartPage getStartPage() {
        if (startPage == null)
            startPage = new StartPage();
        return startPage;
    }

    public FormPage getFormPage() {
        if (formPage == null)
            formPage = new FormPage();
        return formPage;
    }
}
