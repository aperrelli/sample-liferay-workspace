package unifor.redirecturl.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * @author andersonperrelli
 */

@Component(
		immediate = true,
		property = {
				"servlet-context-name=",
				"servlet-filter-name=Sample Servlet Filter",
				"url-pattern=/web/guest/noticia"
		},
		service = Filter.class
)
public class SampleFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		/*
		* URL example that the filter will track
		* http://localhost:8080/web/guest/noticia?news=31355
		* */

		String currentURL = PortalUtil.getCurrentURL((HttpServletRequest) request);
		if(currentURL.contains("news=")) {
			String articleURL = redirectURLArticle(response, currentURL);

			if(articleURL!="") {
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.sendRedirect(articleURL);
			}else {
				filterChain.doFilter(request, response);
			}
		}

	}

	private String redirectURLArticle(ServletResponse response, String currentURL) throws IOException {

		String articleURL = "";
		String id_ = currentURL.substring(currentURL.indexOf("=")+1);

		try {
			JournalArticle article = JournalArticleLocalServiceUtil.getArticle(GetterUtil.getLong(id_));
			String articleUrlTitle = article.getUrlTitle();

			Group group = GroupLocalServiceUtil.getGroup(article.getGroupId());

			articleURL = "/web"+group.getFriendlyURL()+"/-/"+articleUrlTitle;
		} catch (PortalException e) {
			_log.error("Error while retrieve article", e);
		}
		return articleURL;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	private static final Log _log = LogFactoryUtil.getLog(SampleFilter.class);

}