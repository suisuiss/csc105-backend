package com.example.csc105_backend.Controller;


import com.example.csc105_backend.Model.Constants;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "DisplayImageServlet", value = "/displayimg")
public class DisplayImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        String fileName = request.getParameter("fileName");
        String productId = request.getParameter("productId");
        ServletOutputStream servletOutputStream;
        servletOutputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(Constants.PATH_FILE +"\\"+productId+"\\"+fileName);

        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(servletOutputStream);
        int ch = 0;
        while ((ch = bufferedInputStream.read()) != -1) {
            bufferedOutputStream.write(ch);
        }

        bufferedInputStream.close();
        fileInputStream.close();
        bufferedOutputStream.close();
        bufferedOutputStream.close();
    }
}
