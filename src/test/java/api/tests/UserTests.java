package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	
	User userPayload;
	
	@BeforeClass
	public void prepareData() {
		
		faker=new Faker();
				
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
	}
	
	@Test(priority=1)
	public void testCreateUser() {
		
		Response response=UserEndPoints.createUserEP(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority=2)
	public void testRetrieveUser() {
		
		Response response=UserEndPoints.retrieveUserEP(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority=3)
	public void testUpdateUser() {
		
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response=UserEndPoints.updateUserEP(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response response1=UserEndPoints.retrieveUserEP(this.userPayload.getUsername());
		response1.then().log().all();
	}
	
	
	@Test(priority=4)
	public void testDeleteUser() {
		
		Response response=UserEndPoints.deleteUserEP(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response response1=UserEndPoints.retrieveUserEP(this.userPayload.getUsername());
		response1.then().log().all();
	}


}
