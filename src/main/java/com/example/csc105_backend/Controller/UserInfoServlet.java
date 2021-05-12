package com.example.csc105_backend.Controller;

import com.example.csc105_backend.Model.FindUserById;
import com.example.csc105_backend.Model.User;
import com.google.gson.Gson;
import com.example.csc105_backend.Model.Error;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserInfoServlet", value = "/user/information")
public class UserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter();){
            String json = "";
            String userId = request.getParameter("userID");
            FindUserById findUserById = new FindUserById();

            User user = findUserById.findUserById(Integer.valueOf(userId));

            if(null != user) {
                json = new Gson().toJson(user);
            }else {
                Error error = new Error();
                error.setErrorCode("400");
                error.setErrorMsg("Not found User");
                json = new Gson().toJson(error);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(json);
            out.flush();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
