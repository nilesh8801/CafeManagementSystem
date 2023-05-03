package CafeMangementSystem.com.rest;

import CafeMangementSystem.com.POJO.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path="/category")
public interface CategoryRest {


        @PostMapping(path ="/add")
        ResponseEntity<String> addnewCategory(@RequestBody(required=true) Map<String, String > requestmap);

        @GetMapping(path="/get")
        ResponseEntity<List<Category>> getAllCategory(@RequestParam(required = false) String filterValue);

        @PostMapping(path="/update")
        ResponseEntity<String> updateCategory(@RequestBody(required = true) Map<String, String> requestmap);
}
