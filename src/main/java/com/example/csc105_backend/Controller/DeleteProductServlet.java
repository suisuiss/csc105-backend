package com.example.csc105_backend.Controller;

import com.example.csc105_backend.Model.Constants;
import com.example.csc105_backend.Model.DeleteProduct;
import com.example.csc105_backend.Model.DeleteProductImage;
import com.google.gson.Gson;
import org.json.JSONObject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import com.example.csc105_backend.Model.Error;

@WebServlet(name = "DeleteProductServlet", value = "/product/delete")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter();
             BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()))) {

            String json = "";
            String line = "";
            while ((line = br.readLine()) != null) {
                json += line;
            }

            JSONObject jsonObject = new JSONObject(json);
            Integer productID = (Integer) jsonObject.get("productID");

            DeleteProductImage deleteProductImage = new DeleteProductImage();
            DeleteProduct deleteProduct = new DeleteProduct();
            boolean result = deleteProductImage.deleteProductImage(productID);

                File directory = new File(Constants.PATH_FILE +"\\"+productID+"\\");
                if(directory.exists()) {
                    File[] files = directory.listFiles();
                    for (File file : files) {
                        result = file.delete();
                    }
                    result = directory.delete();
                }

                    result = deleteProduct.deleteProduct(productID);
                    if(result) {
                        json = new Gson().toJson(result);
                    }else{
                        Error bean = new Error();
                        bean.setErrorCode("400");
                        bean.setErrorMsg("Fail to Delete Product");
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
