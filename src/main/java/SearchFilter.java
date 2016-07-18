
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by srujant on 18/7/16.
 */
public class SearchFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean flag=false;
        int i;
        Cookie ck[]=null;
        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
        HttpServletResponse httpServletResponse=(HttpServletResponse)response;

        ck=(httpServletRequest).getCookies();
        HashMap<String,Integer> users= (HashMap) Users.getUsers();
        if(ck!=null) {
            for (i = 0; i < ck.length; i++) {
                if (users.containsKey(ck[i].getName() + ck[i].getValue())) {
                    flag = true;
                    break;
                }
            }
        }
        if(flag){
            chain.doFilter(request,response);
        }else{
            httpServletResponse.sendRedirect("./login.html");
        }
    }
    @Override
    public void destroy() {}
}
