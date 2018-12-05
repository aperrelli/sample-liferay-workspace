package sample.consumer.rest.service.rest;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import sample.consumer.rest.model.User;
import sample.consumer.rest.model.Token;

public interface SampleConsumerRestExtendedDataService {
	/*
	@GET("/v1/personas/datos/extendidos/run")
	@Headers({ "Accept: application/json"})
	Call<MinsalPersonaResponse> getPersonaExtendedData(@Header("Authorization") String authorization, @Query("runPersona") String runPersona,
			@Query("dvPersona") String dvPersona, @Query("rolPersona") String rolPersona);

	@PUT("/v1/personas/contactos/run")
	Call<PersonaContactUpdate> updatePersonaContact(@Header("Authorization") String authorization, @Body ContactRequest contactRequest);
	 */

	@FormUrlEncoded
	@POST("/oauth/token")
	@Headers({"Accept: application/json","Content-Type: application/x-www-form-urlencoded",
	"Authorization: Basic aXNkQUs3ZGJFMWZGcGg5b3RlbWRJZXZib3hkQUU3Skc6Sk40UUx6TFVXN2duQWhsOQ=="})
	Call<Token> getToken(@Field("grant_type") String grantType);
	
	@GET("/users")
	@Headers({ "Accept: application/json"})
	Call<User> getUsers();
}
