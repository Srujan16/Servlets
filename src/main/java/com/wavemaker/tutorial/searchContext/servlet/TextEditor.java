package com.wavemaker.tutorial.searchContext.servlet; /**
 * Created by srujant on 21/6/16.
 */


import com.wavemaker.tutorial.SearchResult;
import com.wavemaker.tutorial.WordSearch;
import com.wavemaker.tutorial.searchContext.domain.SearchContext;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;


public class TextEditor extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        response.setContentType("text/html");
        PrintWriter printWriter = null;
        Map<File, List<SearchResult>> result = null;
        SearchContext searchContext = new SearchContext(request);
        WordSearch wordSearch = new WordSearch();
        File file = new File(searchContext.getSource());
        try {
            printWriter = response.getWriter();
            printWriter.print("<html><body>");
            printWriter.print("<p>Result</p>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("./header.html");
            requestDispatcher.include(request, response);
            result = wordSearch.searchDirectory(file, searchContext.getSearchPattern(), searchContext.isRecursiveSearch(), searchContext.getConcurrentNumberOfThreads());
            Set<File> keySet = result.keySet();
            Iterator keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                File currentFile = (File) keySetIterator.next();
                List<SearchResult> searchResult = result.get(currentFile);
                if (searchResult.size() != 0) {
                    printWriter.print("<p>Found word " + searchContext.getSearchPattern() + " in file " + currentFile.getName() + " at Coordinates</p>");
                    for (SearchResult x : searchResult) {
                        printWriter.print("<p>" + x.getLineNumber() + " : " + x.getColumnNumber() + " " + x.getLine() + "</p>");
                    }
                } else {
                    printWriter.print("<p>" + searchContext.getSearchPattern() + " not  found in file " + currentFile.getName() + "</p>");
                }
            }
            request.getSession(false).invalidate();
            printWriter.print("</body></html>");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

