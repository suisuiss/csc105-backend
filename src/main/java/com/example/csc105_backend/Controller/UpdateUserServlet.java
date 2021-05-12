package com.example.csc105_backend.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.csc105_backend.Model.UpdateUser;
import org.json.JSONObject;

import com.example.csc105_backend.Model.Error;
import com.example.csc105_backend.Model.User;

import com.google.gson.Gson;

@WebServlet(name = "UpdateUserServlet", value = "/user/update")
public class UpdateUserServlet extends HttpServlet {

    private static final long serialVersionUID = 5751248993413417829L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter();
             BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()))) {

            String json = "";
            String line = "";
            while ((line = br.readLine()) != null) {
                json += line;
            }

            JSONObject jsonObject = new JSONObject(json);
            String userID = (String) jsonObject.get("userId");
            String firstname = (String) jsonObject.get("firstname");
            String lastname = (String) jsonObject.get("lastname");
            String address = (String) jsonObject.get("address");
            String phoneNumber = (String) jsonObject.get("phoneNumber");
            String email = (String) jsonObject.get("email");

            User user = new User(Integer.valueOf(userID), email, firstname, lastname, "", "", address, phoneNumber);

            UpdateUser updateUser = new UpdateUser();
            boolean result = updateUser.updateUser(user);

            if (result) {
                json = new Gson().toJson(result);
            } else {
                Error bean = new Error();
                bean.setErrorCode("400");
                bean.setErrorMsg("Fail to Update User");
                json = new Gson().toJson(bean);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(json);
            out.flush();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


}

