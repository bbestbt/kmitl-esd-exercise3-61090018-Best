package kmitl.esd.exercise3.client;

import kmitl.esd.exercise3.model.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

//import org.h2.util.json.JSONObject;


@SpringBootApplication
/**
 * client
 */
public class Client {

    private static final Logger log = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Client.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8089"));
        app.run(args);

        // SpringApplication.run(Exercise2Client.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * Main entry point for the client
     */
    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            String response = callGetCustomer(restTemplate);
            log.info(String.format("Call GET customer: " + response));

            /**
             * call create customer
             */
            CustomerDTO createCustomer=callCreateCustomer(restTemplate);
            log.info("Call CREATE customer: " + createCustomer.toString());

            /**
             * call update customer
             */
            CustomerDTO updateCustomer=callUpdateCustomer(restTemplate);
            log.info("Call UPDATE customer: " + updateCustomer.toString());

            /**
             * call delete customer
             */
            ResponseEntity responseEntity =callDeleteCustomer(restTemplate,"0");
            String deleteResponse=callGetCustomer(restTemplate);
            log.info("Call DELETE customer: " +deleteResponse);


//            String response = callGetHello(restTemplate, "Tom");
//            log.info(String.format("Hello GET call: " + response));
//
//            List<String> customers = new ArrayList<>();
//            CustomerDTO customerDTO=callGetCustomerSimple(restTemplate,null);
//            log.info(String.format("Simple GET call : "+customerDTO.getRemark()));
//            customerDTO=callGetCustomerSimple(restTemplate,"Mike");
//            log.info(String.format("Simple GET call : "+customerDTO.getRemark()));
//            customerDTO=callGetCustomerParans(restTemplate,"Mike",null);
//            log.info(String.format("GET call with query paran 'name' : "+customerDTO.getRemark()));
//            customerDTO=callGetCustomerParans(restTemplate,"Mike",55L);
//            log.info(String.format("GET call with query all paran : "+customerDTO.getRemark()));

        };
    }

    /**
     * call get customer
     * @param restTemplate
     * @return
     */
    String callGetCustomer(RestTemplate restTemplate){
        StringBuffer url = new StringBuffer("http://localhost:8080/customer");
        String respString = restTemplate.getForObject(
                url.toString(), String.class);
        return respString;
    }

    /**
     * call create customer
     * @param restTemplate
     * @return
     * @throws JSONException
     */
    CustomerDTO callCreateCustomer(RestTemplate restTemplate) throws JSONException {
        JSONObject customersData = new JSONObject();
        customersData.put("id",1);
        customersData.put("name","best");
        customersData.put("age",20);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(customersData.toString(), headers);

        ResponseEntity<CustomerDTO> response = restTemplate.exchange("http://localhost:8080/customer", HttpMethod.POST, request, CustomerDTO.class);

        return response.getBody();

    }

    /**
     * call delete customer
     * @param restTemplate
     * @param customerId
     * @return
     */
    ResponseEntity<String> callDeleteCustomer(RestTemplate restTemplate, String customerId) {
        ResponseEntity<String> response=restTemplate.exchange("http://localhost:8000/customer/" + customerId,HttpMethod.DELETE,null,String.class);
        return response;
    }

    /**
     * call update customer
     * @param restTemplate
     * @return
     * @throws JSONException
     */
    CustomerDTO callUpdateCustomer(RestTemplate restTemplate) throws JSONException {
        JSONObject customersData = new JSONObject();
        customersData.put("id",2);
        customersData.put("name","Aom");
        customersData.put("age",21);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(customersData.toString(), headers);

        ResponseEntity<CustomerDTO> response = restTemplate.exchange("http://localhost:8000/customer", HttpMethod.PUT, request, CustomerDTO.class);
        return response.getBody();

    }

    String callGetHello(RestTemplate restTemplate, String name) {
        StringBuffer url = new StringBuffer("http://localhost:8080/hello1?name=Tom");
        if (name != null) {
            url.append("?name=").append(name);
        }
        String respString = restTemplate.getForObject(
                url.toString(), String.class);
        return respString;
    }

    /**
     * Rest API endpoint for getting a customer (find by name)
     * @param restTemplate spring rest template object
     * @param name of the customer (REST query parameter)
     * @return customer(if exist) or null(if not exist)
     */
    CustomerDTO callGetCustomerSimple(RestTemplate restTemplate, String name){
        StringBuffer url=new StringBuffer("http://localhost:8088/customer");
        if(name!=null){
            url.append("?name=").append(name);
        }
        CustomerDTO customerDTO=restTemplate.getForObject(url.toString(), CustomerDTO.class);
        return customerDTO;
    }

//    CustomerDTO callGetWithQueryParans(RestTemplate restTemplate, String name, Long age){
//        StringBuffer url=new StringBuffer("http://localhost:8088/test");
//        HttpHeaders headers=new HttpHeaders();
//        headers.set("Accept",MediaType.APPLICATION_JSON_VALUE);
//
//        UriComponentsBuilder builder=UriComponentsBuilder.fromHttpUrl(url.toString()).queryParan("name",name);
//        if(age!=null) builder.queryParan("age",age);
//
//        HttpEntity<?> entity=new HttpEntity<>(headers);
//
//        HttpEntity<CustomerDTO> response=restTemplate.exchange(
//                builder.toUriString(),
//                HttpMethod.GET,
//                entity,
//        )
//
//    }


}


