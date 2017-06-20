package com.epam.automation.strategy;

import com.epam.automation.pages.Page;

/**
 * Created by Vikas_Sharma on 6/20/2017.
 */
public class TitleValidationStrategy extends PageValidationStrategy {

    @Override
    public boolean isCurrentPage(Page page) {
        return page.getPageTitle().contains(page.getTitle());
    }
}
