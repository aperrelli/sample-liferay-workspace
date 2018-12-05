package sample.consumer.rest.service.impl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import sample.consumer.rest.model.User;
import sample.consumer.rest.service.SampleConsumerRestService;
import sample.consumer.rest.service.rest.impl.SampleConsumerRestExtendedDataImpl;
import sample.consumer.rest.util.SampleConsumerRestPropsUtil;

/**
 * @author andersonperrelli
 */
@Component(
		immediate = true,
		property = {
				// TODO enter required service properties
		},
		service = SampleConsumerRestService.class
		)

public class SampleConsumerRestServiceImpl implements SampleConsumerRestService {

	@Override
	public String getToken() throws Exception {

		SampleConsumerRestPropsUtil sampleConsumerRestPropsUtil = new SampleConsumerRestPropsUtil();
		SampleConsumerRestExtendedDataImpl sampleConsumerRestExtendedDataImpl = 
				new SampleConsumerRestExtendedDataImpl(sampleConsumerRestPropsUtil.BASEURL_PATH, true);

		return sampleConsumerRestExtendedDataImpl.getToken();
	}

	@Override
	public List<User> getUsers() throws Exception {
		
		//SampleConsumerRestPropsUtil sampleConsumerRestPropsUtil = new SampleConsumerRestPropsUtil();
		SampleConsumerRestExtendedDataImpl sampleConsumerRestExtendedDataImpl = 
				new SampleConsumerRestExtendedDataImpl("https://jsonplaceholder.typicode.com/", true);
		
		
		return sampleConsumerRestExtendedDataImpl.getUsers();
	}

}
