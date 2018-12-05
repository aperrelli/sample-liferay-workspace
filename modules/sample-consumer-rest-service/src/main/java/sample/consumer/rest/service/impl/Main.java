package sample.consumer.rest.service.impl;

import java.util.List;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import sample.consumer.rest.model.User;
import sample.consumer.rest.service.rest.impl.SampleConsumerRestExtendedDataImpl;

public class Main {
	private static final Log _log = LogFactoryUtil.getLog(SampleConsumerRestExtendedDataImpl.class);

	public static void main(String[] args) {
		SampleConsumerRestExtendedDataImpl sampleConsumerRestExtendedDataImpl = new SampleConsumerRestExtendedDataImpl(
				"https://jsonplaceholder.typicode.com/", true);
		try {
			List<User> users = sampleConsumerRestExtendedDataImpl.getUsers();
			
			System.out.println(users.size());

			
		} catch (Exception e) {
			_log.error("Internal Error", e);
		}
	}
}
