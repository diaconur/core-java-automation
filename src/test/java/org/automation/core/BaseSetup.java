package org.automation.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.automation.core.data.BonusResponse;
import org.automation.core.data.BonusValues;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.automation.core.utils.HelperUtils.buildBodyRequest;

/**
 * Microservice can be started by running the docker command :
 * docker run -it -p 8080:8080 razvan1987/microservice_bonus:v1
 * <p>
 * Swagger URL: http://localhost:8080/swagger-ui.html
 * <p>
 * Date format: YYYY-MM-DD
 */
@Slf4j
public class BaseSetup {
    public static final String BASE_URI = "http://localhost:8080/bonus";
    RequestSpecification requestSpecification;
    Response getEntries;
    List<BonusResponse> bonusList;

    @BeforeTest
    public void baseSetup() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI).build();

        IntStream.range(0, BonusValues.values().length).forEach(i -> {
            try {
                RestAssured.given(requestSpecification)
                        .body(buildBodyRequest(i))
                        .headers(Map.of("Content-type", "application/json"))
                        .post("/addBonus");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        getEntries = RestAssured.given(requestSpecification).get("/getAllBonuses");
        bonusList = getEntries.jsonPath().getList("$", BonusResponse.class);
        log.info("Available entries are: {}", bonusList);
    }

    @AfterClass
    public void deleteAllBonuses() {
        Response response = RestAssured.given(requestSpecification)
                .get("/getAllBonuses");
        List<BonusResponse> bonusesList = response.jsonPath().getList("$", BonusResponse.class);

        IntStream.range(bonusesList.get(0).getId(),
                        bonusesList.get(bonusesList.size() - 1).getId() + 1)
                .forEach(nr -> {
                    try {
                        RestAssured.given(requestSpecification)
                                .pathParams(Map.of("id", nr))
                                .delete("/deleteBonus/{id}?id=" + nr);
                    } catch (RuntimeException exception) {
                        throw new RuntimeException("Could not delete entries!");
                    }

                });
        log.info("Deleted entries: {}", bonusesList.size());
    }
}
