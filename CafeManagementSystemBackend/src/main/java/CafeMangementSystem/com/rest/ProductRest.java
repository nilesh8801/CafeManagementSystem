package CafeMangementSystem.com.rest;


import CafeMangementSystem.com.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path="/product")
public interface ProductRest {

    // add product
    @PostMapping(path="/add")
    ResponseEntity<String> addNewProduct(@RequestBody Map<String,String> requestMap);


    @GetMapping(path="/get")
    ResponseEntity<List<ProductWrapper>> getAllProduct();

    //update product
    @PutMapping(path="/update")
    ResponseEntity<String> updateProduct(@RequestBody Map<String, String> requestMap);

    @DeleteMapping(path="/delete/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable Integer id);

    //update product status
    @PutMapping(path="/updateStatus")
    ResponseEntity<String>  updateStatus(@RequestBody Map<String, String> requestMap);


    //pass category id. so all product exist in category  will return
    @GetMapping(path ="/getByCategory/{id}")
    ResponseEntity<List<ProductWrapper>> getByCategory(@PathVariable Integer id);

    //get particular product Id
    @GetMapping(path="/getById/{id}")
    ResponseEntity<ProductWrapper> getProductById(@PathVariable Integer id);
}
