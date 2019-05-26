package com.coffee.controller.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.coffee.common.exception.DeleteFailException;
import com.coffee.common.exception.EditFailException;
import com.coffee.common.exception.RegistFailException;
import com.coffee.common.file.FileManager;
import com.coffee.model.domain.Category;
import com.coffee.model.domain.Product;
import com.coffee.model.service.CategoryService;
import com.coffee.model.service.ProductService;

/* Component-scan �±װ� ��ϵǾ� �־�� �Ʒ��� ������Ʈ�� ���� */
@Controller
@RequestMapping("/admin")
public class AdminProductController {
   @Autowired
   private ProductService productService;
   @Autowired
   private CategoryService categoryService;
   @Autowired
   private FileManager fileManager;

   // DefaultAnnotaionHandlerMapping�� ���� �Ʒ��� ��û ������ ���۰����ϴ�
   @RequestMapping(value = "/product/list", method = RequestMethod.GET)
   public ModelAndView selectAll(HttpServletRequest request) {
      System.out.println("����� ���ϴ±�");
      List<Product> productList = productService.selectAll();
      List<Category> categoryList = categoryService.selectAll();
      ModelAndView mav = new ModelAndView("admin/product/list");
      mav.addObject("productList", productList);
      mav.addObject("categoryList", categoryList);
      return mav;
   }

   @RequestMapping(value = "/product/detail", method = RequestMethod.GET)
   public ModelAndView select(int product_id) {
      System.out.println("�����߱�");
      Product product = productService.select(product_id);
      List<Category> categoryList = categoryService.selectAll();
      ModelAndView mav = new ModelAndView("admin/product/detail");
      mav.addObject("product", product);
      mav.addObject("categoryList", categoryList);

      return mav;
   }

   @RequestMapping(value = "/product/goRegist", method = RequestMethod.GET)
   public ModelAndView goRegist() {
      System.out.println("ī�װ� �ҷ�������...");
      List<Category> categoryList = categoryService.selectAll();
      ModelAndView mav = new ModelAndView("admin/product/regist");
      mav.addObject("categoryList", categoryList);
      return mav;
   }

   @RequestMapping(value = "/product/regist", method = RequestMethod.POST)
   public ModelAndView insert(Product product, Integer category_id, HttpServletRequest request) {
      System.out.println("category_id : " + category_id);
      Category category = new Category();
      category.setCategory_id(category_id);
      product.setCategory(category);

      
      System.out.println("���� : " + product.getMyFile());

      MultipartFile myFile = product.getMyFile();
      String filename = myFile.getOriginalFilename();
      String realPath = request.getServletContext().getRealPath("/data");
      System.out.println(realPath);
      File uploadFile;
      if (product.getCategory() != null) {
         System.out.println("����߱�");
      }
      try {
         uploadFile = new File(realPath + "/" + filename);
         myFile.transferTo(uploadFile);
         filename = fileManager.renameByDate(uploadFile, realPath);
         if (filename != null) {
            product.setFilename(filename);
            productService.insert(product);
         }
      } catch (IllegalStateException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      ModelAndView mav = new ModelAndView("redirect:/admin/product/list");
      return mav;
   }

   @RequestMapping(value = "/product/edit", method = RequestMethod.POST)
   public ModelAndView update(Product product, int category_id, HttpServletRequest request) {
      Category category = new Category();
      category.setCategory_id(category_id);
      System.out.println("ī�װ� ����" + category.getCategory_name());
      product.setCategory(category);

      System.out.println("���� : " + product.getMyFile());

      MultipartFile myFile = product.getMyFile();
      String filename = myFile.getOriginalFilename();
      String realPath = request.getServletContext().getRealPath("/data");
      System.out.println(realPath);
      File uploadFile;

      try {
         uploadFile = new File(realPath + "/" + filename);
         myFile.transferTo(uploadFile);
         filename = fileManager.renameByDate(uploadFile, realPath);
         if (filename != null) {
            product.setFilename(filename);
            productService.update(product);
         }
      } catch (IllegalStateException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      System.out.println("�����߱�");
      productService.update(product);
      ModelAndView mav = new ModelAndView("redirect:/admin/product/list");
      return mav;
   }

   @RequestMapping(value = "/product/delete", method = RequestMethod.POST)
   public ModelAndView delete(int product_id) {
      System.out.println("�����߱�");
      productService.delete(product_id);
      ModelAndView mav = new ModelAndView("redirect:/admin/product/list");
      return mav;
   }
   @RequestMapping(value="/product/search")
   public ModelAndView search(Product product, int category_id) {
	   Category category = new Category();
	   category.setCategory_id(category_id);
	   product.setCategory(category);
	   List<Product> productList = productService.search(product);
	   List<Category> categoryList = categoryService.selectAll();
	   ModelAndView mav = new ModelAndView("admin/product/list");
	   mav.addObject("productList", productList);
	   mav.addObject("categoryList", categoryList);
	   return mav;
   }
  
   @ExceptionHandler(AccountNotFoundException.class)
   public ModelAndView handleException(AccountNotFoundException e) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("admin/error/errorpage");
      mav.addObject("err", e);
      return mav;
   }

   @ExceptionHandler(RegistFailException.class)
   public String registException(RegistFailException e) {
      StringBuffer sb = new StringBuffer();
      sb.append("{");
      sb.append("\"result\":0");
      sb.append("}");
      return sb.toString();
   }

   @ExceptionHandler(EditFailException.class)
   public String editException(EditFailException e) {
      return "{\"resultCode\":0,\"msg\":\"" + e.getMessage() + "\"}";
   }

   @ExceptionHandler(DeleteFailException.class)
   public String deleteException(DeleteFailException e) {
      return "{\"resultCode\":0,\"msg\":\"" + e.getMessage() + "\"}";
   }

}