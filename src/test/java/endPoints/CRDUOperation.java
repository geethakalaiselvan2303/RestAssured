package endPoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payLoad.User;

public class CRDUOperation {
	
	public static Response Create(User payload){
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(EndpointURL.post_url);
 		
		return response;
	}
	
	public static Response read(String userName){
		Response response = RestAssured.given()
				.pathParam("username", userName)
				.when()
				.get(EndpointURL.get_url);
		
		return response;
	}
	public static Response update(User payload,String userName){
		Response response = RestAssured.given().contentType("application/json")
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				.when()
				.put(EndpointURL.put_url);
		
		return response;
	}
	
	public static Response delete(String userName){
		Response response = RestAssured.given()
				.pathParam("username", userName)
				.when()
				.put(EndpointURL.delete_url);
		
		return response;
	}

}
