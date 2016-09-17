package fr.afcepf.atod.business.product.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.afcepf.atod.business.product.api.IBuCity;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.City;

public class TestBu {
	private static Logger log = Logger.getLogger(TestBu.class);

	public static void main(String[] args) throws WineException {
		@SuppressWarnings("resource")
		BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springBuProduct.xml");
		IBuCity bu = (IBuCity) bf.getBean(IBuCity.class);

		List<City> list = new ArrayList<City>();
		list = bu.findAllCities();
		for (City city : list) {
			log.info("99999999"+city.getZipcode());
		}
	}

}
