package CafeMangementSystem.com.serviceImpl;

import CafeMangementSystem.com.JWT.JwtFilter;
import CafeMangementSystem.com.POJO.Category;
import CafeMangementSystem.com.constents.CafeConstants;
import CafeMangementSystem.com.dao.CategoryDao;
import CafeMangementSystem.com.service.CategoryService;
import CafeMangementSystem.com.utils.CafeUtils;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    JwtFilter jwtFilter;


    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        try{
            if(jwtFilter.isAdmin()){
                if(validateCategoryMap(requestMap,false)){
                    categoryDao.save(getCategoryFromMap(requestMap, false));
                    return CafeUtils.getResponseEntity("Category Added sucessfully", HttpStatus.OK);
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //validateId =false it will check one condition  "name"
    //validateId =true it will check two condition  "name & id"
    private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId){
        if(requestMap.containsKey("name")){
            if(requestMap.containsKey("id") && validateId){
                return true;
            }else if(!validateId){
                return true;
            }
        }
        return false;

        //both case will fail "id & name" that time it will return false
    }

    private Category getCategoryFromMap(Map<String, String> requestMap, Boolean isAdd){
        Category category= new Category();
        if(isAdd){
            category.setId(Integer.parseInt(requestMap.get("id")));
        }
        category.setName(requestMap.get("name"));
        return category;
    }

    //______________________________________________________________________________________________________


    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        try {
            if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
                return new ResponseEntity<List<Category>>(categoryDao.getAllCategory(), HttpStatus.OK);

            }
            return new ResponseEntity<>(categoryDao.findAll(), HttpStatus.OK);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return new  ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updatecategory(Map<String, String> requestmap) {
        try{
            if(jwtFilter.isAdmin()){
                Optional optional= categoryDao.findById(Integer.parseInt(requestmap.get("id")));
                if(!optional.isEmpty()){
                    categoryDao.save(getCategoryFromMap(requestmap, true));
                    return CafeUtils.getResponseEntity("category Upadted Successfully....!!!", HttpStatus.OK);

                }
                 }else{

              return  CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        }
        catch(Exception ex){
                ex.printStackTrace();
            }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
