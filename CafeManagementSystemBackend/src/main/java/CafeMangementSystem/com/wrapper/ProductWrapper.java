package CafeMangementSystem.com.wrapper;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class ProductWrapper {

    // here we specify value the column and key in which return the value


    Integer id;

    String name;

    String description;

    Integer price;

    String status;


    Integer categoryId;

    String categoryName;

    public ProductWrapper(){

    }

       // define constructor
    public ProductWrapper(Integer id, String name, String description, Integer price,  String status,
                          Integer categoryId, String categoryName ){

        this.id=id;
        this.name=name;
        this.description=description;
        this.price=price;
        this.status=status;
        this.categoryId=categoryId;
        this.categoryName=categoryName;

    }

    public ProductWrapper(Integer id, String name){
        this.id=id;
        this.name=name;
    }

    public ProductWrapper(Integer id, String name, String description, Integer price){
        this.id=id;
        this.name=name;
        this.description=description;
        this.price=price;
    }

}
