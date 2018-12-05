package sample.consumer.rest.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class SampleConsumerRestPropsUtil {
	public final String BASEURL_PATH;

	public SampleConsumerRestPropsUtil() {
		
		//https://jsonplaceholder.typicode.com/
		BASEURL_PATH = getPortalProperty("baseurl.path");
	}

	/**
	 * To get properties from portal properties files
	 * */
	public String getPortalProperty(String propertyKey) {
		return GetterUtil.getString(PropsUtil.get(propertyKey));
	}
}
