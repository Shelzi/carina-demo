package com.qaprosoft.carina.demo.shelzi;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.demo.apihw.DeleteUserMethod;
import com.qaprosoft.carina.demo.apihw.PostUserMethod;
import com.qaprosoft.carina.demo.apihw.GetUserMethods;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.core.registrar.tag.Priority;
import com.zebrunner.carina.core.registrar.tag.TestPriority;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

public class FirstAPITest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    @MethodOwner(owner = "Shelzi")
    public void testGetUsers() {
        LOGGER.info("testGetUsers");
        GetUserMethods getUsersMethods = new GetUserMethods();
        getUsersMethods.callAPIExpectSuccess();
        getUsersMethods.validateResponse(JSONCompareMode.STRICT,
                JsonCompareKeywords.ARRAY_CONTAINS.getKey() + "products");
        getUsersMethods.validateResponseAgainstSchema("api/dummyjson/products/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "Shelzi")
    public void testCreateUserMissingSomeFields() throws Exception {
        LOGGER.info("testCreateUserMissingSomeFields");
        PostUserMethod api = new PostUserMethod();
        api.setProperties("api/dummyjson/products/product.properties");
        api.getProperties().remove("title");
        api.getProperties().remove("price");
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "Shelzi")
    @TestPriority(Priority.P1)
    public void testDeleteUsers() {
        DeleteUserMethod deleteUserMethod = new DeleteUserMethod();
        deleteUserMethod.setProperties("api/dummyjson/products/product.properties");
        deleteUserMethod.callAPIExpectSuccess();
        deleteUserMethod.validateResponse();
    }
}
