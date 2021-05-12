package com.example.csc105_backend.Controller;

import com.example.csc105_backend.Model.*;
import com.example.csc105_backend.Model.Error;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProductInfoServlet", value = "/product/info")
public class ProductInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter();){
            String json = "";
            String productId = request.getParameter("productID");
            GetProductById getProductById = new GetProductById();
            ProductImageList productImageList = new ProductImageList();
            Product product = getProductById.getProductById(Integer.valueOf(productId));

            if(null != product) {
                List<ProductImage> productImages = productImageList.getProductImgList(product.getProductId());
                if(null != productImages && !productImages.isEmpty()) {
                    String[] urlSplit = request.getRequestURL().toString().split("product");
                    product.setProductPics(urlSplit[0]+ Constants.PATH_DISPLAY+productImages.get(0).getProductPicUrl()+Constants.PATH_DISPLAY_2+product.getProductId());
                }

                json = new Gson().toJson(product);
                json = json.replaceAll("\\u003d", "=");
            }else {
                Error error = new Error();
                error.setErrorCode("400");
                error.setErrorMsg("Not found Product");
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