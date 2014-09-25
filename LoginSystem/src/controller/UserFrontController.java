package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.UserAuthentication;

/**
 * Servlet implementation class UserFrontController
 */
@WebServlet("*.user")
public class UserFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		
		System.out.println(RequestURI);
		System.out.println(contextPath);
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = new ActionForward();
		
		if (command.equals("/authentication.user")){
			System.out.println("Authentication User");
			try{
				action = new UserAuthentication();
				forward = action.execute(request, response);
				System.out.println(forward.getPath());
				System.out.println(forward.isRedirect());
			}catch(Exception ex){
				System.out.println(ex);
			}
		}
		
		if(forward != null){			 	
			if(forward.isRedirect()){ // true
				System.out.println("true");
				response.sendRedirect(forward.getPath());
			}else{                    // false
				System.out.println("false");
				RequestDispatcher dispatcher =	request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
