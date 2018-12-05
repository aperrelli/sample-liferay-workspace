package sample.consumer.rest.service;

import java.util.List;

import sample.consumer.rest.model.User;

/**
 * @author andersonperrelli
 */

public interface SampleConsumerRestService {
	public String getToken() throws Exception;
	public List<User> getUsers() throws Exception;
}