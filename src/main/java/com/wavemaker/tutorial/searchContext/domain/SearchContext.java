package com.wavemaker.tutorial.searchContext.domain;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by srujant on 19/7/16.
 */
public class SearchContext {

    private int concurrentNumberOfThreads;
    private boolean recursiveSearch;
    private String searchPattern;
    private String source;

    public SearchContext(HttpServletRequest request) {
        this.concurrentNumberOfThreads = Integer.parseInt(request.getParameter("concurrentNumberOfThreads"));
        this.recursiveSearch = Boolean.parseBoolean(request.getParameter("recursiveSearch"));
        this.searchPattern = request.getParameter("searchPattern");
        this.source = request.getParameter("sourceFile");
        ;
    }


    public int getConcurrentNumberOfThreads() {
        return concurrentNumberOfThreads;
    }

    public boolean isRecursiveSearch() {
        return recursiveSearch;
    }

    public String getSearchPattern() {
        return searchPattern;
    }

    public String getSource() {
        return source;
    }
}
