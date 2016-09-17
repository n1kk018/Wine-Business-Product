package fr.afcepf.atod.business.product.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.afcepf.atod.business.product.api.IBuCity;
import fr.afcepf.atod.business.product.api.IBuProduct;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.City;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;
import fr.afcepf.atod.wine.entity.ProductWine;
import java.util.Map;

public class TestBu {
	private static Logger log = Logger.getLogger(TestBu.class);

	public static void main(String[] args) throws WineException {
		@SuppressWarnings("resource")
		BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springBuProduct.xml");
		
                
                IBuProduct buProductbis  = (IBuProduct) 
                        bf.getBean(IBuProduct.class);
                
                List<ProductType> wineTypes = buProductbis.getWineTypes();
                Map<ProductType, List<ProductVarietal>> varietals 
                        = buProductbis.getVarietalsByType(wineTypes);
                List<ProductWine> wines = null;
                
                for(Map.Entry<ProductType,List<ProductVarietal>> 
                        e : varietals.entrySet()){
                    List<ProductVarietal> listVarietal = e.getValue();
                    for(ProductVarietal prod : listVarietal) {
                        wines = buProductbis.categoryAccordingToObjectType(e.getKey(), 
                                prod);
                    }
                }
                
	}

}
