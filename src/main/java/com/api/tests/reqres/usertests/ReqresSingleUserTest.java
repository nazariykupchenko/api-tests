package com.api.tests.reqres.usertests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidatorSettings.settings;

import com.api.endpoints.reqres.ReqresUserEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Slf4j
public class ReqresSingleUserTest {


  private final ReqresUserEndpoint reqresUserEndpoint = new ReqresUserEndpoint();

  @DataProvider(name = "data-provider")
  public Object[][] userDataProvider() {
    return new Object[][]{
        new Object[]{2, "Weaver"},
        new Object[]{7, "Lawson"}
    };
  }

  @Test(dataProvider = "data-provider")
  public void userLastNameTest(int id, String expectedLastName) {
    String actualLastName = reqresUserEndpoint
        .getSingleUser(id)
        .extract()
        .response()
        .jsonPath()
        .get("data.last_name");
    Assert.assertEquals(expectedLastName, actualLastName);
    log.info("test for user id {} , and last name {} finished", id, expectedLastName);
  }

  @Test
  public void userSchemaTest() {
    reqresUserEndpoint
        .getSingleUser(1)
        .assertThat().body(matchesJsonSchemaInClasspath("reqres/single-user-schema.json").using(
            settings().with().checkedValidation(false)));
  }
}