package ecommerce.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ecommerce.dao.UsersDao;
import ecommerce.exceptions.EcommerceException;

@WebFilter("/*")
public class TokenFilter implements Filter {

	private static final int URL_INDEX = 10;
	@EJB
	private UsersDao udao;

	/*
	 * NON PRENDERE IN CONSIDERAZIONE, PROVA PER INTERCETTARE TOKEN MA POI
	 * SOSTITUITO CON FILTER
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
		Properties newProperties = new Properties();
		String url = null;
		InputStream inputStream = TokenFilter.class.getResourceAsStream("/url.prop");
		if (inputStream != null) {
			try {
				newProperties.load(inputStream);
			} catch (IOException e) {
				throw new IOException();
			}
			url = (String) newProperties.get("url");
		}
		String[] arrayURL = url.split(",");
		HttpServletRequest req = (HttpServletRequest) request;
		String subURI = req.getRequestURI().substring(URL_INDEX);
		for (int i = 0; i < arrayURL.length; i++) {
			if (subURI.contains(arrayURL[i])) {
				String token = req.getHeader("x-token");
				try {
					udao.checkToken(token);
					break;
				} catch (EcommerceException e) {
					((HttpServletResponse) response).sendError(500);
					throw new ServletException();
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
