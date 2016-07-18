/**
 * Created by srujant on 21/6/16.
 */


import com.wavemaker.tutorial.SearchResult;
import com.wavemaker.tutorial.WordSearch;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;



public class TextEditor extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");

        int concurrentNumberOfThreads = Integer.parseInt(request.getParameter("concurrentNumberOfThreads"));
        boolean recursiveSearch = Boolean.parseBoolean(request.getParameter("recursiveSearch"));
        String searchPattern = request.getParameter("searchPattern");
        String source = request.getParameter("sourceFile");
        PrintWriter printWriter = null;
        Map<File, List<SearchResult>> result;

        WordSearch wordSearch = new WordSearch();
        File file = new File(source);

        try {
            printWriter = response.getWriter();
            printWriter.print("<html><body>");
            printWriter.print("<h3>Result</h3>");
            result = wordSearch.searchDirectory(file, searchPattern, recursiveSearch, concurrentNumberOfThreads);
            Set<File> keySet = result.keySet();
            Iterator keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                File currentFile = (File) keySetIterator.next();
                List<SearchResult> searchResult = result.get(currentFile);
                if (searchResult.size() != 0) {
                    printWriter.print("<p>Found word " + searchPattern + " in " + file.getName() + " at Coordinates</p>");
                    for (SearchResult x : searchResult) {
                        printWriter.print("<p>" + x.getLineNumber() + " : " + x.getColumnNumber() + " " + x.getLine() + "</p>");
                    }
                } else {
                    printWriter.print("<p>" + searchPattern + " not  found in file " + file.getName() + "</p>");
                }
            }
            printWriter.print("</body></html>");
            request.getSession(false).invalidate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}

