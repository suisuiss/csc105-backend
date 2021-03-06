package com.example.csc105_backend.Controller;

import com.example.csc105_backend.Model.Error;
import com.example.csc105_backend.Model.SignupOperation;
import com.google.gson.Gson;
import org.json.JSONObject;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", value = "/signup")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {

            String json = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                json += line;
            }

            JSONObject jsonObject = new JSONObject(json);
            String firstname = (String) jsonObject.get("firstname");
            String lastname = (String) jsonObject.get("lastname");
            String address = (String) jsonObject.get("address");
            String phoneNumber = (String) jsonObject.get("phoneNumber");
            String email = (String) jsonObject.get("email");
            String username = (String) jsonObject.get("username");
            String password = (String) jsonObject.get("password");

            SignupOperation signupOperation = new SignupOperation();
            boolean signup = signupOperation.register(firstname, lastname, address, phoneNumber, email, username, password);

            if (signup) {
                json = new Gson().toJson(signup);
            } else {
                Error error = new Error();
                error.setErrorCode("400");
                error.setErrorMsg("Fail to Register");
                json = new Gson().toJson(error);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(json);
            out.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
