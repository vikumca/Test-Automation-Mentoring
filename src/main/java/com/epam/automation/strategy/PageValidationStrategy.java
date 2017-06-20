package com.epam.automation.strategy;

import com.epam.automation.pages.Page;

/**
 * Created by Vikas_Sharma on 6/20/2017.
 */
public abstract class PageValidationStrategy {
    public abstract boolean isCurrentPage(Page page);
}
