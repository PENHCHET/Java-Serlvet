package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAOUser;
import model.DTOUser;

import controller.Action;
import controller.ActionForward;

public class UserAuthentication implements Action{
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		DTOUser user = new DTOUser();
		DAOUser daoUser = new DAOUser();
		try{
			String email = request.getParameter("txtEmail");
			String password = request.getParameter("txtPassword");
			
			System.out.println("Email: "+email);
			System.out.println("Password: "+password);
			user = daoUser.getAuthenticationUser(email, password);
			System.out.println("USER"+user);
		}catch(NullPointerException ex){
			System.out.println("NULL"+ex);
		}		
		
		if(user!=null){
			if(user.getPosition().equals("Admin")){
				forward.setRedirect(true);
				forward.setPath("./Admin.jsp");
			}else if(user.getPosition().equals("Staff")){
				forward.setRedirect(true);
				forward.setPath("./Staff.jsp");
			}
			System.out.println("Login Successfully!!!");
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
		}else{
			forward.setRedirect(true);
			forward.setPath("./index.jsp");
			System.out.println("Login failed, Please try again!!!!!!!");
		}
		return forward;
	}
}
