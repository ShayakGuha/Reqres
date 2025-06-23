import org.testng.Assert;
import org.testng.annotations.Test;

public class apiTest extends Base {

    @Test
    public void testStatus() {
        Assert.assertEquals(getAPITest().statusCode(),200);
    }

    @Test
    public void testPageNumber() {
        String str = readJson(getAPITest(),"page");
        Assert.assertEquals(str, "2");

    }

    @Test
    public void postTest() {
        Assert.assertEquals(postAPITest().statusCode(), 201);
    }

    @Test
    public void postTestWithData() {
        Assert.assertEquals(postAPIWithPayloadTest().statusCode(), 201);
    }

    @Test
    public void postWithTakingData() {
        String key = "page";
        String value = readJson(getAPITest(),key);
        storeObject(key,value);
        postAPIWithPayloadTest();

    }


}
