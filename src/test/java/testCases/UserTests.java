package testCases;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endPoints.CRDUOperation;
import io.restassured.response.Response;
import payLoad.User;

public class UserTests {
	
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setUpData() {
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());;
		userPayload.setUsername(faker.name().username());
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone( faker.phoneNumber().cellPhone());
    }
	
	@Test(priority = 1)
	public void createUser() {
		Response response = CRDUOperation.Create(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2)
	public void readUser() {
		Response response = CRDUOperation.read(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority = 3)
	public void updateUser() {
		
		userPayload.setFirstname(faker.name().firstName());
	    userPayload.setLastname(faker.name().lastName());
		Response response = CRDUOperation.update(userPayload,this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//After update
		Response responseAfterupdate = CRDUOperation.read(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void deleteUser() {
		Response response = CRDUOperation.delete(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
