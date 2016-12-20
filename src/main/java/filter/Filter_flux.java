package filter;

import controller.Stat_Flux;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by caopeihe on 2016-8-17.
 */
public class Filter_flux extends HttpServlet implements Filter{
    private static int flux = 0;
    Stat_Flux stat_flux = new Stat_Flux();
    public void init(FilterConfig filterConfig) throws ServletException{}

    public synchronized void doFilter(ServletRequest request, ServletResponse response,
                                      FilterChain Chain) throws ServletException,IOException{
        HttpSession session = ((HttpServletRequest) request).getSession();
        if(session.getAttribute("flux") != null && flux == (Integer) session.getAttribute("flux")){
            List<String> list = stat_flux.read_Flux();
            request.setAttribute("count",list.get(2));
            request.setAttribute("last_ip",list.get(0));
            request.setAttribute("last_date",list.get(1));
        }else{
            this.flux++;
            session.setAttribute("flux",flux);
            String ip = request.getRemoteAddr();
            Date date = new Date();
            String dateTime = date.toString();
            stat_flux.write_Flux(ip,dateTime);
            List<String> list = stat_flux.read_Flux();
            request.setAttribute("count",list.get(2));
            request.setAttribute("last_ip",list.get(0));
            request.setAttribute("last_date",list.get(1));
        }
        Chain.doFilter(request,response);
    }
    public void destroy(){}
}
