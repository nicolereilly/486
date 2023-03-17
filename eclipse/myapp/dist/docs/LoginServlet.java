package edu.una.cis;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Iterator;

import javax.security.auth.PrivateCredentialPermission;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	public static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) 
		throws ServletException, IOException 
	{
		PrintWriter out = null;
		out = response.getWriter();
		response.setContentType("text/html");
		
        LoginContext lc = null;
        try {
        	//new LoginContext("foo");
            lc = new LoginContext("AD_Realm", 
            		new ServletCallbackHandler(request.getParameter("username"),
            				request.getParameter("password")));
        } catch (LoginException le) {
        	out.println("Cannot create LoginContext. " + le.getMessage());
        	return;
        } catch (SecurityException se) {
        	out.println("Cannot create LoginContext. " + se.getMessage());
            return;
        } 
        
        try {
            lc.login();
        } catch (LoginException le) {
        	out.println("<h2>Authentication failed:</h2><br>");
        	out.println("  " + le.getMessage());
        	return;
        }
		
        Subject subject = lc.getSubject();
        request.getSession().setAttribute("subjectKey", subject);
        
        out.println("<h2>Principals</h2><ul>");
        Iterator iter = subject.getPrincipals().iterator();
        while (iter.hasNext()) {
			Principal p = (Principal) iter.next();
			out.println("<li>" + p.toString() + "</li>");
		}
        
        out.println("</ul><h2>Private Credentials</h2><ul>");
        iter = subject.getPrivateCredentials().iterator();
        while (iter.hasNext()) {
			PrivateCredentialPermission c = (PrivateCredentialPermission) iter.next();
			out.println("<li>" + c.toString() + "</li>");
		}
        out.println("</ul>");
        
        if (request.isUserInRole("manager")) {
        	out.println("<p>you are a manager</p>");
        } else {
        	out.println("<p>you cant do much</p>");
        }
        
        
//   		RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
//		rd.forward(request, response);
	}
}

class ServletCallbackHandler implements CallbackHandler {
	
	private String username;
	private String password;
	
	public ServletCallbackHandler(String username, String password) {
		this.username = username;
		this.password = password;
	}

    public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
      
        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof TextOutputCallback) {
                TextOutputCallback toc = (TextOutputCallback)callbacks[i];
                System.out.println(toc.getMessage());
            } else if (callbacks[i] instanceof NameCallback) {
                NameCallback nc = (NameCallback)callbacks[i];
                nc.setName(username);
            } else if (callbacks[i] instanceof PasswordCallback) {
                PasswordCallback pc = (PasswordCallback)callbacks[i];
                pc.setPassword(password.toCharArray());
            } else {
                throw new UnsupportedCallbackException
                        (callbacks[i], "Unrecognized Callback");
            }
        }
    }
}

