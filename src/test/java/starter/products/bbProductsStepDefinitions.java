package starter.products;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import starter.postcodes.LocationResponse;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class bbProductsStepDefinitions {

    @Steps
    bbProductsAPI productsAPI;

    @When("I get all products from the store")
    public void getAllProducts() {
        bbProductsAPI.getAllProducts();
    }

    @When("I get a product by id {}")
    public void getProductById(String id) {
        bbProductsAPI.getProductById(id);
    }

    @Then("the response code should be {}")
    public void theResultIsValid(int responseCode) {
        restAssuredThat(response -> response.statusCode(responseCode));
    }

    @Then("the response body field {} is not empty")
    public void theResultIsNotEmpty(String field) {
        ResponseBody body = SerenityRest.lastResponse();
        JsonPath json = body.jsonPath();

        Integer jsonField = json.get(field);
        Assert.assertTrue(jsonField != null );
    }
}
