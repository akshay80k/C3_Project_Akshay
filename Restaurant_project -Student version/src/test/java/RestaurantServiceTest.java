import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest{
    //REFACTOR ALL THE REPEATED LINES OF CODE
    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");

    //REFACTOR ALL THE REPEATED LINES OF CODE


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE

        restaurant = service.findRestaurantByName("Amelie's cafe");
        assertNotNull(restaurant);

    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        //Arrange section


        //Act section
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);

        //Assertion section
        assertThrows(restaurantNotFoundException.class,()->{
            service.findRestaurantByName("Udupi restaurant");
        });


    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        //arrange
        int initialNumberOfRestaurants;

        //act
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");

        //assertion
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        //arrange

        //act
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        //assertion
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        //arrange
        int initialNumberOfRestaurants;

        //act
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        initialNumberOfRestaurants = service.getRestaurants().size();

        //assertion
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>



                                    //Test cases
    //writing test method to display selected item total price
    //assusme that the name of the item we selected will be stored as list of string
    // select restaurant to see menu of that restaurant by giving restaurant name, it uses  findRestaurantByname() in RestaurantService
    //this restaurant should display menu
    //select items in menu using items name (give list of item name as input), here I am using select_item_from_particular_restaurant_menu_and_display_price() method
    //sum of selected item is 388 for "sweet corn soup" and "vegetable lasagne"
    //assert the price
    @Test
    public void selected_item_from_particular_restaurant_name_should_display_total_cost_of_item_selected() throws restaurantNotFoundException {
        //arrange
        //selecting restaurant to see menu
        String restaurant_name= "Amelie's cafe";
        //see menu and select item from menu
        System.out.println("see the menu below and select items: ");
        List<String> item_names = new ArrayList<>();
        String item1= "Sweet corn soup";
        String item2 = "Vegetable lasagne";
        int price;

        //act
        restaurant =service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("paneer manchury",140);
        //adding selected_items to item_name list
        item_names.add(item1);
        item_names.add(item2);
        price = service.select_item_from_particular_restaurant_menu_and_display_price(restaurant_name,item_names);


        //assert
        assertEquals(388,price);


    }
}