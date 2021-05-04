package com.example.csc105_backend.Controller;

import com.example.csc105_backend.Model.SignupOperation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try (PrintWriter out = response.getWriter()){
        response.setContentType("text/html;charset=UTF-8");
        SignupOperation signupOperation = new SignupOperation();
        HttpSession session = request.getSession(false);
        String signup = signupOperation.register(request.getParameter("name"),request.getParameter("surname"),request.getParameter("address"),request.getParameter("phoneNumber"),request.getParameter("email"),request.getParameter("username"),request.getParameter("password"),(Integer.parseInt(session.getAttribute("userid").toString())));

        if(signup.equals("FAILED REGISTER")){
            getServletContext().getRequestDispatcher("/").forward(request,response);
            out.println("FAILED REGISTER");
        }
        else{
            request.getRequestDispatcher("").forward(request,response);
        }
    }
    }
}
