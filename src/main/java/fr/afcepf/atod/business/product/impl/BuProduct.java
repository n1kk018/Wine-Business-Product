/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afcepf.atod.business.product.api.IBuProduct;
import fr.afcepf.atod.business.product.api.IGetWinesParameters;
import fr.afcepf.atod.business.product.designpattern.visitor.ProductBase;
import fr.afcepf.atod.business.product.designpattern.visitor.IVisitable;
import fr.afcepf.atod.business.product.designpattern.visitor.ProductsVisitor;
import fr.afcepf.atod.vin.data.exception.WineErrorCode;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.data.product.api.IDaoProduct;
import fr.afcepf.atod.wine.data.product.api.IDaoProductType;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;
import fr.afcepf.atod.wine.entity.ProductVintage;
import fr.afcepf.atod.wine.entity.ProductWine;
import java.util.ArrayList;

/**
 *
 * @author ronan
 */
@Service
public class BuProduct implements IBuProduct, IGetWinesParameters {

    @Autowired
    protected IDaoProduct daoProduct;
    @Autowired
    private IDaoProductType daoProductType;
    private static final int MAX_SE = 50;
    private List<ProductWine> wines = null;
    
    //private static final int STEP_INT = 50;

    @Override
    public Product findByName(String name) throws WineException {
        Product product = null;
        WineException wineException = null;
        try {
            if (!name.equalsIgnoreCase("")) {
                product = daoProduct.findByName(name);
                if (!product.getName().equalsIgnoreCase("")) {
                    return product;
                } else {
                    throw new WineException(
                            WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                            name + " not referenced in the db");
                }
            } else {
                throw new WineException(
                        WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                        name + " not referenced in the db");
            }
        } catch (Exception e) {
            wineException = new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    name + " not referenced in the db");
        }
        if (wineException != null) {
            throw wineException;
        }
        return product;
    }

    @Override
    public List<Product> findExpensive(double min) 
    		throws WineException {
        List<Product> expensiveProds = null;
        if (min >= 0) {
            expensiveProds = daoProduct.findExpensiveProducts(min);
            if (expensiveProds.isEmpty()) {
                throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                        "expensive products not found in the database");
            }
        } else {
            throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "criteria has to be defined...");
        }

        return expensiveProds;
    }

    @Override
    public List<Product> getPromotedProductsSelection() 
    		throws WineException {
        List<Product> list = null;
        try {
            list = daoProduct.getPromotedProductsSortedByEndDate(MAX_SE/5);
        } catch (Exception e) {
            throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "no promoted item referenced in db");
        }

        return list;
    }

    @Override
    public List<ProductType> getWineTypes() throws WineException {
        List<ProductType> list = null;
        try {
            list = daoProductType.findAllObj();
        } catch (Exception e) {
            throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "no promoted item referenced in db");
        }
        return list;
    }

    @Override
    public List<Product> findProductsByName(String name) 
    		throws WineException {
        List<Product> list = null;
        try {
            list = daoProduct.findByNotCompleteName(name);
        } catch (Exception e) {
            throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "no promoted item referenced in db");
        }
        return list;
    }

    @Override
    public List<Product> findProductsByAppelation(String appelation) 
    		throws WineException {
        List<Product> list = null;
        try {
            list = daoProduct.findByAppelation(appelation);
        } catch (Exception e) {
            throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "no promoted item referenced in db");
        }
        return list;
    }

    @Override
    public List<ProductVintage> findProductsByVintage(Integer vintage) 
    		throws WineException {
        List<ProductVintage> list = null;
        try {
            list = daoProduct.findByVintage(vintage);
        } catch (Exception e) {
            throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "no promoted item referenced in db");
        }
        return list;
    }

    @Override
    public List<ProductVarietal> findProductsBy(String variatal) 
    		throws WineException {
        List<ProductVarietal> list = null;
        try {
            list = daoProduct.findByVarietal(variatal);
        } catch (Exception e) {
            throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "no promoted item referenced in db");
        }
        return list;
    }

    @Override
    public List<ProductType> findProductsType(String wineType) 
    		throws WineException {
        List<ProductType> list = null;
        try {
            list = daoProduct.findByType(wineType);
        } catch (Exception e) {
            throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "no promoted item referenced in db");
        }
        return list;
    }

    @Override
    public Map<ProductType, List<String>> getAppellationsByType(List<ProductType> wineTypes) 
    		throws WineException {
        Map<ProductType, List<String>> map = new HashMap<>();
        try {
            for (ProductType productType : wineTypes) {
                map.put(productType, daoProduct.getAppellationsByWineType(productType));
            }
        } catch (Exception e) {
            throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "no promoted item referenced in db");
        }
        return map;
    }

    @Override
    public Map<ProductType, List<ProductVarietal>> getVarietalsByType(List<ProductType> wineTypes)
            throws WineException {
        Map<ProductType, List<ProductVarietal>> map = new HashMap<>();
        try {
            for (ProductType productType : wineTypes) {
                map.put(productType, daoProduct.getVarietalsByWineType(productType));
            }
        } catch (Exception e) {
            throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "no promoted item referenced in db");
        }
        return map;
    }

    @Override
    public Product findById(Integer id) throws WineException {
        Product prod = null;
        try {
            prod = daoProduct.findObj(id);
        } catch (Exception e) {
            throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "not referenced in db");
        }
        return prod;
    }

    @Override
    public List<ProductWine> categoryAccordingToObjectType(ProductType type, Object o,
    		Integer firstRow,Integer rowsPerPage, String sorting) throws WineException {
    	wines = new ArrayList<>();
        if (!type.getType().equalsIgnoreCase("")) {
            wines = getWinesParameters(type, o,firstRow,rowsPerPage, sorting);
            if (wines != null && wines.isEmpty()) {
                throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                        "Pas de produits trouves selon les parametres: "
                        + type.getType());
            }
        } else {
            throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "Pas de produits trouves selon les parametres: "
                    + type.getType());
        }
        return wines;
    }

    @Override
    public List<ProductWine> getWinesParameters(ProductType type, Object o,
    		Integer firstRow,Integer rowsPerPage, String sorting) throws WineException {
    	System.out.println("getWinesParameters");
    	wines = new ArrayList<>();
        if (o instanceof ProductVarietal) {
            ProductVarietal varietal = (ProductVarietal) o;
            wines = getWinesParameters(type, varietal,firstRow,rowsPerPage,sorting);
        } else if (o instanceof ProductVintage) {
            ProductVintage vintage = (ProductVintage) o;
            wines = getWinesParameters(type, vintage,firstRow,rowsPerPage);
        } else if(o instanceof String){    
                wines = getWinesParameters(type, (String)o,firstRow,rowsPerPage,sorting);
        } else if(o instanceof Integer){    
                Integer i = Integer.valueOf(o.toString());
                wines = getWinesParameters(type, i, firstRow, rowsPerPage, sorting);
        } else if (o==null){
        	 wines = getWinesParameters(type,firstRow,rowsPerPage,sorting);
        } else {
            throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "Pas de bouteille de type : " + type.getType());
        }
        return wines;
    }
    
    @Override
    public void flushCurrentHibernateSession()
    {
        daoProduct.getSf().getCurrentSession().flush();
    }
    
    @Override
    public void refreshLocalizedObject(Object o)
    {
        daoProduct.getSf().getCurrentSession().refresh(o);
    }
    
    public List<ProductWine> getWinesParameters(ProductType type, Integer firstRow,Integer rowsPerPage, String sorting)
            throws WineException {
        wines = new ArrayList<>();
        wines = daoProduct.findByType(type,firstRow,rowsPerPage,sorting.split("_")[0],sorting.split("_")[1]);
        return wines;
    }

    public List<ProductWine> getWinesParameters(ProductType type, ProductVarietal varietal, Integer firstRow,Integer rowsPerPage, String sorting)
            throws WineException {
        wines = new ArrayList<>();
        wines = daoProduct.findByVarietalAndType(type, varietal,firstRow,rowsPerPage,sorting.split("_")[0],sorting.split("_")[1]);
        return wines;
    }
    
    public List<ProductWine> getWinesParameters(ProductType type, String appellation ,Integer firstRow,Integer rowsPerPage, String sorting)
            throws WineException {
        wines = new ArrayList<>();
        wines = daoProduct.findByAppelationAndType(type, appellation,firstRow,rowsPerPage,sorting.split("_")[0],sorting.split("_")[1]);
        return wines;
    }
    

    public List<ProductWine> getWinesParameters(ProductType type, ProductVintage vintage ,Integer firstRow,Integer rowsPerPage)
            throws WineException {
        wines = new ArrayList<>();
        wines = daoProduct.findByVintageAndType(type, vintage,firstRow,rowsPerPage);
        return wines;
    }

    public List<ProductWine> getWinesParameters(ProductType type, Integer integ, Integer firstRow, Integer rowsPerPage, String sorting)
            throws WineException {
        wines = new ArrayList<>();
        switch (integ) {
            case 0:
                wines = daoProduct.findByMoneyAndType(type, integ, integ + MAX_SE,firstRow,rowsPerPage,sorting.split("_")[0],sorting.split("_")[1]);
                break;
            case MAX_SE:
                wines = daoProduct.findByMoneyAndType(type, integ, integ + MAX_SE,firstRow,rowsPerPage,sorting.split("_")[0],sorting.split("_")[1]);
                break;
            case 2*MAX_SE:
                wines = daoProduct.findByMoneyAndType(type, integ,firstRow,rowsPerPage,sorting.split("_")[0],sorting.split("_")[1]);
                break;
            default:
                break;
                
        }
        return wines;
    }

	@Override
	public Integer countCategoryAccordingToObjectType(ProductType type, Object o) 
                    throws WineException {
		Integer count = 0;
		if (o instanceof ProductVarietal) {
			ProductVarietal varietal = (ProductVarietal) o;
			count = daoProduct.countByVarietalAndType(type, varietal);
		} else if (o instanceof ProductVintage) {
			ProductVintage vintage = (ProductVintage) o;
			count = daoProduct.countByVintageAndType(type, vintage);
		} else if (o instanceof String) {
			count = daoProduct.countByAppellationAndType(type, (String) o);
		} else if (o instanceof Integer) {
			Integer i = Integer.valueOf(o.toString());
			if (i == 2 * MAX_SE)
				count = daoProduct.countByMoneyAndType(type, i);
			else
				count = daoProduct.countByMoneyAndType(type, i, i + MAX_SE);
		} else if (o == null) {
			count = daoProduct.countByType(type);
		} else {
			throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
					"Pas de bouteille de type : " + type.getType());
		}
		return count;
	}

	@Override
	public Map<ProductType, Map<Integer, Integer>> getPricesRepartitionByType(List<ProductType> wineTypes)
			throws WineException {
		Map<ProductType, Map<Integer, Integer>> map = new HashMap<>();
        try {
            for (ProductType productType : wineTypes) {
            	Map<Integer, Integer> pricemap = new HashMap<Integer, Integer>();
            	pricemap.put(0, daoProduct.countByMoneyAndType(productType, 0,50));
            	pricemap.put(50, daoProduct.countByMoneyAndType(productType, 50,100));
            	pricemap.put(100, daoProduct.countByMoneyAndType(productType, 100));
                map.put(productType, pricemap);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return map;
	}
        
        // point d'entree du design pattern visiteur
        
        public List<ProductWine> getWines(ProductType type, Object o,
                Integer firstRow,Integer rowsPerPage) throws WineException {
            wines = new ArrayList<>();
            Integer count = 0;
            ProductsVisitor productsVisitor = new ProductsVisitor();
            ProductBase produitGlobal = new ProductBase(type, firstRow, rowsPerPage);
            
            if (o instanceof IVisitable) { 
                ((IVisitable) o).accept(productsVisitor);
                wines = productsVisitor.getWines();
                count = productsVisitor.getCount();
            } else {
                throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
					"Pas de bouteille de type : " + type.getType());
            }
            return wines;
        }
}
