package com.example.csc105_backend.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.example.csc105_backend.Model.*;
import com.example.csc105_backend.Model.Error;
import org.json.JSONObject;

import com.google.gson.Gson;

@WebServlet(name = "UpdateProductServlet", value = "/product/update")
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {

    private static final long serialVersionUID = 8790991566726275278L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> fileNames = new ArrayList<String>();
        try (PrintWriter out = response.getWriter()) {

            String json = request.getParameter("data");
            List<Part> parts = (List<Part>) request.getParts();

            JSONObject jsonObject = new JSONObject(json);

            String productName = (String) jsonObject.get("productName");
            String productPrice = (String) jsonObject.get("productPrice");
            Integer productAmount = (Integer) jsonObject.get("productAmount");
            String productDetails = (String) jsonObject.get("productDetails");
            String productDescription = (String) jsonObject.get("productDescription");
            String productCategory = (String) jsonObject.get("productCategory");
            Integer productID = (Integer) jsonObject.get("productID");

            Product product = new Product();
            product.setProductName(productName);
            product.setProductPrice(new BigDecimal(productPrice));
            product.setProductAmount(productAmount);
            product.setProductDetails(productDetails);
            product.setProductDesc (productDescription);
            product.setProductCategory(productCategory);
            product.setProductId(productID);

            UpdateProduct updateProduct = new UpdateProduct();
            boolean prdresult = updateProduct.updateProduct(product);
            DeleteProductImage deleteProductImage = new DeleteProductImage();
            CreateProductImage createProductImage = new CreateProductImage();
            if (prdresult) {
                boolean flag = true;
                ProductImage prdImage = null;

                boolean delRs = deleteProductImage.deleteProductImage(product.getProductId());
                if(delRs) {
                    File directory = new File(Constants.PATH_FILE+productID+"\\");
                    if(directory.exists()) {
                        File[] files = directory.listFiles();
                        for (File file : files) {
                            delRs = file.delete();
                        }
                    }

                    if(delRs) {
                        for (Part part : parts) {
                            if (!part.getName().equalsIgnoreCase("data")) {
                                File file = new File(Constants.PATH_FILE+productID+"\\");
                                if(!file.exists()) {
                                    file.mkdir();
                                }
                                String fileName = getFileName(part);
                                fileNames.add(fileName);
                                String basePath =  Constants.PATH_FILE+productID+"\\";
                                File outputFilePath = new File(basePath + fileName);
                                try (InputStream inputStream = part.getInputStream();OutputStream outputStream = new FileOutputStream(outputFilePath)){
                                    int read = 0;
                                    final byte[] bytes = new byte[1024];
                                    while ((read = inputStream.read(bytes)) != -1) {
                                        outputStream.write(bytes, 0, read);
                                    }
                                } catch (Exception ex) {
                                    fileName = null;
                                    flag = false;
                                }
                            }
                        }
                        for(String fileName : fileNames) {
                            prdImage = new ProductImage();
                            prdImage.setProductId(product.getProductId());
                            prdImage.setProductPicUrl(fileName);
                            int resultPrdImg = createProductImage.createProductImage(prdImage);
                            if(resultPrdImg == 0) {
                                flag = false;
                            }
                        }
                    }
                }

                if(!flag) {
                    Error bean = new Error();
                    bean.setErrorCode("400");
                    bean.setErrorMsg("Fail to Update Product Image");
                    json = new Gson().toJson(bean);
                }else {
                    json = new Gson().toJson(true);
                }
            } else {
                Error bean = new Error();
                bean.setErrorCode("400");
                bean.setErrorMsg("Fail to Update Product");
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

    private static String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
