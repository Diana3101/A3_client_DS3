import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.*;
import entities.dto.CreateOrderDTO;
import entities.dto.ServeDTO;
import entities.dto.ThingsDTO;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Main {
    private static final String URL = "http://localhost:8087";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    public static void main(String[] args) {

        headers.setContentType(MediaType.APPLICATION_JSON);

        Seller seller1 = new Seller("Masha1", "Orl");
        List<Thing> addedThings = createThingList();
        List<Integer> quantities = Arrays.asList(1,2,3,4,5);

        //added things on site
        addThingsOnSite(seller1, addedThings, quantities);

        //get things from DB
        ResponseEntity<ThingsDTO> response2 = restTemplate
                .exchange(URL + "/things", HttpMethod.GET, headersEntity, ThingsDTO.class);
        List<Thing> things = Objects.requireNonNull(response2.getBody()).getThings();
        System.out.println(things);

        //get things from DB
         response2 = restTemplate
                 .exchange(URL + "/things", HttpMethod.GET, headersEntity, ThingsDTO.class);
        List<Thing> thingsForSale = Objects.requireNonNull(response2.getBody()).getThings();
        System.out.println(thingsForSale);


        Customer customer1 = new Customer( "Sasha1", "Diduh", Size.M);
        Customer customer2 = new Customer( "Cris1", "Dktyr", Size.XS);

        //creating orders
        System.out.println("Trying to create new order");
        List<Thing> bucketForCustomer1 = new ArrayList<>(thingsForSale.subList(0, 2));
        makeOrder(customer1, bucketForCustomer1);

        System.out.println("Trying to create new order");
        List<Thing> bucketForCustomer2 = new ArrayList<>(thingsForSale.subList(2, 5));
        makeOrder(customer2, bucketForCustomer2);

        //get things from DB
        response2 = restTemplate
                .exchange(URL + "/things", HttpMethod.GET, headersEntity, ThingsDTO.class);

        //get customers from DB
        ResponseEntity<Customer[]> response4 = restTemplate
                .exchange(URL + "/customers", HttpMethod.GET, headersEntity, Customer[].class);
        System.out.println("___________________________________________" + "\nCustomers: ");
        List<Customer> customers = Arrays.asList(Objects.requireNonNull(response4.getBody()));
        System.out.println(customers);
        System.out.println("\n___________________________________________");

        ResponseEntity<Order[]> response5 = restTemplate
                .exchange(URL + "/orders", HttpMethod.GET, headersEntity, Order[].class);
        System.out.println("___________________________________________" + "\nOrders: ");
        List<Order> orders = Arrays.asList(Objects.requireNonNull(response5.getBody()));
        System.out.println(orders);
        System.out.println("\n___________________________________________");
    }

    private static void makeOrder(Customer customer, List<Thing> bucketForCustomer) {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO();
        createOrderDTO.setCustomer(customer);
        createOrderDTO.setThings(bucketForCustomer);
        HttpEntity<CreateOrderDTO> createOrder = new HttpEntity<>(createOrderDTO);
        ResponseEntity<Void> response4 = restTemplate
                .exchange(URL + "/orders", HttpMethod.POST,
                        createOrder, Void.class);
    }

    public static List<Thing> createThingList() {

        Thing thing1 = new Thing("shirt1", Size.M, ConditionOfThing.ideal, 30);
        Thing thing2 = new Thing("skirt1", Size.S, ConditionOfThing.veryGood, 35);
        Thing thing3 = new Thing("suit1", Size.S, ConditionOfThing.ideal, 100);
        Thing thing4 = new Thing("jeans1", Size.M, ConditionOfThing.norm, 80);
        Thing thing5 = new Thing("dress1", Size.L, ConditionOfThing.New, 57);

        return Arrays.asList(thing1, thing2, thing3, thing4, thing5);
    }


    private static void addThingsOnSite(Seller seller1, List<Thing> addedThings, List<Integer> quantities) {
        ServeDTO serveDTO = new ServeDTO();
        serveDTO.setSeller(seller1);
        serveDTO.setThing(addedThings);
        serveDTO.setThingQuantities(quantities);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String serveJsonStr = gson.toJson(serveDTO);

        HttpEntity<String> serveJson = new HttpEntity<>(serveJsonStr, headers);
        ResponseEntity<Void> response1 = restTemplate
                .exchange(URL + "/sellers", HttpMethod.POST, serveJson, Void.class);

        System.out.println("Seller " + seller1.getLastName() + " has added " + addedThings);
    }
}
