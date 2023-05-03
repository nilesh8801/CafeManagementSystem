package CafeMangementSystem.com.restImpl;

import CafeMangementSystem.com.POJO.Category;
import CafeMangementSystem.com.constents.CafeConstants;
import CafeMangementSystem.com.rest.CategoryRest;
import CafeMangementSystem.com.service.CategoryService;
import CafeMangementSystem.com.utils.CafeUtils;
import CafeMangementSystem.com.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class CategoryRestImpl implements CategoryRest {

    @Autowired
    CategoryService categoryService;



    @Override
    public ResponseEntity<String> addnewCategory(Map<String, String> requestmap) {
        try{
            return categoryService.addNewCategory(requestmap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        try{
            return categoryService.getAllCategory(filterValue);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestmap) {
        try{
            return categoryService.updatecategory(requestmap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
