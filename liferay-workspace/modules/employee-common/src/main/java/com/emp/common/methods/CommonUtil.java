package com.emp.common.methods;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineHelperUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.util.Validator;

public class CommonUtil {

	public static SearchContext getSearchContext(long companyId, int start, int end) {

		SearchContext searchContext = new SearchContext();
		if (Validator.isNotNull(companyId)) {
			searchContext.setCompanyId(companyId);
		}
		if (start != QueryUtil.ALL_POS) {
			searchContext.setStart(start);
		}
		if (end != QueryUtil.ALL_POS) {
			searchContext.setEnd(end);
		}

		return searchContext;
	}

	public static BooleanQuery addParameterInQuery(String parameter, String value, BooleanQuery mainQuery) {

		try {
			BooleanQuery booleanQuery = new BooleanQueryImpl();
			booleanQuery.addRequiredTerm(parameter, value);
			mainQuery.add(booleanQuery, BooleanClauseOccur.MUST);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return mainQuery;
	}

	public static BooleanQuery addParameterInQuery(String parameter, String value, BooleanQuery mainQuery,
			BooleanClauseOccur booleanClauseOccur) {

		try {
			BooleanQuery booleanQuery = new BooleanQueryImpl();
			booleanQuery.addRequiredTerm(parameter, value);
			mainQuery.add(booleanQuery, booleanClauseOccur);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return mainQuery;
	}

	public static BooleanQuery addBooleanQuery(BooleanQuery mainQuery, BooleanQuery booleanQuery) {

		try {
			mainQuery.add(booleanQuery, BooleanClauseOccur.MUST);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return mainQuery;
	}

	public static Hits getHits(SearchContext searchContext, BooleanQuery booleanQuery) throws SearchException {

		IndexSearcher indexSearcher = SearchEngineHelperUtil.getSearchEngine().getIndexSearcher();

		return indexSearcher.search(searchContext, booleanQuery);
	}

	private static final Log log = LogFactoryUtil.getLog(CommonUtil.class);

}
