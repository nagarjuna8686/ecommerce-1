package ecommerce.filter;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import ecommerce.dao.UsersDao;
import ecommerce.exceptions.EcommerceException;

@WebFilter("/*")
public class TokenFilter implements Filter {

	
	@EJB
	private UsersDao udao;
	
/*
 * NON PRENDERE IN CONSIDERAZIONE, PROVA PER INTERCETTARE TOKEN MA POI SOSTITUITO CON FILTER
 */
//	@AroundInvoke
//	public Object interceptorMethod(InvocationContext ic) throws Exception {
//		
//		System.out.println("token not ok");
//		return null;
//	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("TokenFilter.doFilter()");
		HttpServletRequest req = (HttpServletRequest) request;
		
		String token = req.getHeader("x-token");
		try {
			udao.checkToken(token);
		} catch (EcommerceException e) {
			e.printStackTrace();
		}
		chain.doFilter(request, response);			
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
