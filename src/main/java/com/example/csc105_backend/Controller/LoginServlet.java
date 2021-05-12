package com.example.csc105_backend.Controller;
import com.example.csc105_backend.Model.Login;
import com.example.csc105_backend.Model.LoginOperation;
import com.example.csc105_backend.Model.Error;
import org.json.JSONObject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import com.google.gson.Gson;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out = response.getWriter();
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()))){

            String json = "";
            String line = "";
            while ((line = br.readLine()) != null) {
                json += line;
            }

             JSONObject jsonObject = new JSONObject(json);
            String username = (String)jsonObject.get("username");
            String password = (String)jsonObject.get("password");

            LoginOperation loginOperation = new LoginOperation();
            HttpSession session;
            Login login = loginOperation.checkLogin(username,password);
            if(null != login && login.getUserid() != 0){
                json = new Gson().toJson(login.getUserid());
                session= request.getSession();
                session.setAttribute("userid",login.getUserid());
            }
            else{
                Error error= new Error();
                error.setErrorCode("400");
                error.setErrorMsg("Invalid username : "+username+" or password : "+password);
                json = new Gson().toJson(error);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(json);
            out.flush();
        }
    }
}
