package sample.consumer.rest.service.rest.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sample.consumer.rest.model.Token;
import sample.consumer.rest.model.User;
import sample.consumer.rest.service.rest.SampleConsumerRestExtendedDataService;

public class SampleConsumerRestExtendedDataImpl {

	protected SampleConsumerRestExtendedDataService sampleConsumerRestExtendedDataService;

	public SampleConsumerRestExtendedDataImpl(String baseUrl, boolean debug) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

		if (debug) {
			HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

			interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

			httpClientBuilder.addInterceptor(interceptor);
		}

		try {

			httpClientBuilder.hostnameVerifier(new HostnameVerifier() {

				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});

		} catch (Exception e) {
			_log.error("Hostname verifier problem", e);
		}

		Retrofit.Builder builder = new Retrofit.Builder();

		Retrofit retrofit = builder.baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson))
				.client(httpClientBuilder.build()).build();

		sampleConsumerRestExtendedDataService = retrofit.create(SampleConsumerRestExtendedDataService.class);
	}

	public String getToken() throws Exception {
		Call<Token> call = sampleConsumerRestExtendedDataService.getToken("client_credentials");

		Response<Token> response = call.execute();

		String tokenComplete = response.body().getTokenType() + " " + response.body().getAccessToken();

		return tokenComplete;
	}
	
	public List<User> getUsers() throws IOException{
		
		List<User> users = new ArrayList();
		
		Call<User> call = sampleConsumerRestExtendedDataService.getUsers();

		Response<User> response = call.execute();

		return users;
	}

	private static final Log _log = LogFactoryUtil.getLog(SampleConsumerRestExtendedDataImpl.class);

}
