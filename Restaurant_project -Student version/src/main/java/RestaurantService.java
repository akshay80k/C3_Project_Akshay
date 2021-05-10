import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {

        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        //restaurants.add(new Restaurant("Ayodhya Restaurant","Bangalore",LocalTime.of(9,0),LocalTime.of(23,00)));
        for(Restaurant restaurant : restaurants){
            if(restaurant.getName().equals(restaurantName)){
                return restaurant;
            }

        }

        throw  new restaurantNotFoundException(restaurantName + "not found");

    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }


    public int select_item_from_particular_restaurant_menu_and_display_price(String restaurant_name,List<String> item_name) throws restaurantNotFoundException {
        int price = 0;
        List<String> item_name_list = new ArrayList<String>();
        item_name_list=item_name;
        Restaurant restaurant = findRestaurantByName(restaurant_name);
        System.out.println(restaurant.getMenu());
        for (Item item : restaurant.getMenu()) {
            for (String name : item_name_list) {
                if (item.getName().equals(name)) {
                    price = price + item.getPrice();
                }
            }
        }
        return price;
    }



}

