package com.liferay.sample.render.filter;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author andersonperrelli
 */
@Component(
		immediate = true,
		property = {
				"javax.portlet.name=com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet"
		},
		service = PortletFilter.class
)
public class SampleRenderFilter implements RenderFilter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init(FilterConfig arg0) throws PortletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(RenderRequest renderRequest, RenderResponse renderResponse, FilterChain filterChain)
			throws IOException, PortletException {

		redirectURLArticle(renderRequest, renderResponse, filterChain);

	}

	private void redirectURLArticle(RenderRequest request, RenderResponse response, FilterChain filterChain)
			throws IOException, PortletException {

		HttpServletRequest originalServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil
				.getHttpServletRequest(request));
		String id_ = ParamUtil.getString(originalServletRequest, "news");

		try {

			if(Validator.isNotNull(id_)) {

				JournalArticle article = JournalArticleLocalServiceUtil.getArticle(GetterUtil.getLong(id_));
				String articleUrlTitle = article.getUrlTitle();

				Group group = GroupLocalServiceUtil.getGroup(article.getGroupId());

				String articleURL = "/web"+group.getFriendlyURL()+"/-/"+articleUrlTitle;

				HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(response);
				httpServletResponse.sendRedirect(articleURL);
			}else{
				filterChain.doFilter(request, response);
			}
		} catch (PortalException e) {
			_log.error("Error while retrieve article", e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(SampleRenderFilter.class);
}