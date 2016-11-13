package fr.afcepf.atod.business.product.test;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.afcepf.atod.business.product.api.IBuCountry;
import fr.afcepf.atod.business.product.api.IBuProduct;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.Country;
import fr.afcepf.atod.wine.entity.ProductType;

public class TestBu {

    private static Logger log = Logger.getLogger(TestBu.class);

    public static void main(String[] args) throws WineException {
        Locale.setDefault(Locale.FRANCE);
        @SuppressWarnings("resource")
        BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springBuProduct.xml");

//        IBuProduct buProductbis = (IBuProduct) bf.getBean(IBuProduct.class);
        IBuCountry buC = (IBuCountry) bf.getBean(IBuCountry.class);
        
        List<Country> test = buC.listAllCountries();
        for (Country country : test) {
            log.info(country.getName() +"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        }

//        List<ProductType> wineTypes = buProductbis.getWineTypes();
//        for (ProductType productType : wineTypes) {
//            log.info(productType.getType());
//        }
//        Locale.setDefault(Locale.US);
//        for (ProductType productType : wineTypes) {
//           // buProductbis.refreshLocalizedObject(productType);
//            log.info(productType.getType());
//        }
        //List<ProductVintage> vintages = buProductbis.findProductsByVintage(2010);

        /*Map<ProductType, List<String>> appellations= buProductbis.getAppellationsByType(wineTypes);
        Map<ProductType, Map<Integer,Integer>> prices= buProductbis.getPricesRepartitionByType(wineTypes);
        
        log.info( prices.get(wineTypes.get(0)));*/
        /*List<ProductWine> wines = null;
        log.info(wineTypes);
        log.info(appellations.get(wineTypes.get(0)));
        wines = buProductbis.categoryAccordingToObjectType(wineTypes.get(0), appellations.get(wineTypes.get(0)).get(0),0,5);
        for (ProductWine wine : wines) {
            log.info("\t # wine: " + wine.getName());
        }*/
        /*for (ProductVintage vintage : vintages) {
            for (ProductType type : wineTypes) {
                try {
                    wines = buProductbis.categoryAccordingToObjectType(type, vintage,0,5);
                } catch (Exception e) {

                }
                for (ProductWine wine : wines) {
                    log.info("\t # wine: " + wine.getDescription());
                }
            }
        }*/

    }

}
