package com.dhirajprakash.vm.config;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/test/")
@Transactional("trans")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UseIt {

	public class PlacaDetails {

	}

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@RequestMapping(value = { "getDataWeakely" }, method = RequestMethod.POST)
	public List<String> getDataWeakely(HttpServletResponse response, WebRequest webRequest) {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(
				"select occurencia_day,count(occurencia_day) from report_metadata group by occurencia_day;");
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@RequestMapping(value = { "getpleacedetail" }, method = RequestMethod.POST)
	public List<PlacaDetails> getPleaceDetail(HttpServletResponse response, WebRequest webRequest) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(PlacaDetails.class);
		List<PlacaDetails> pleaceDetail = cr.list();
		return pleaceDetail;
	}

}
