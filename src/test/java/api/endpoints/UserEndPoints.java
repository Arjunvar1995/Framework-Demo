package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response createUserEP(User payload) {
		
		Response response=given().
							contentType(ContentType.JSON).
							accept(ContentType.JSON).
							body(payload).
							log().all().
						  when().
						  	post(Routes.create_user_url);
		
		return response;
		
	}
	
	public static Response retrieveUserEP(String userName) {
		
		Response response=given().
							accept(ContentType.JSON).
							pathParam("username", userName).
							log().all().
						  when().
						  	get(Routes.get_user_url);
		
		return response;
		
	}
	
	
	public static Response updateUserEP(String userName, User payload) {
		
		Response response=given().
							contentType(ContentType.JSON).
							accept(ContentType.JSON).
							pathParam("username", userName).
							body(payload).
							log().all().
						  when().
						  	put(Routes.update_user_url);
		
		return response;
		
	}
	
	
	public static Response deleteUserEP(String userName) {
		
		Response response=given().
							accept(ContentType.JSON).
							pathParam("username", userName).
							log().all().
						  when().
						  	delete(Routes.delete_user_url);
		
		return response;
		
	}

}
