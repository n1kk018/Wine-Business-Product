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
import fr.afcepf.atod.vin.data.exception.WineErrorCode;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.data.product.api.IDaoProduct;
import fr.afcepf.atod.wine.data.product.api.IDaoProductType;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;
import fr.afcepf.atod.wine.entity.ProductVintage;
import fr.afcepf.atod.wine.entity.ProductWine;
import java.io.Serializable;
import java.util.ArrayList;
import org.springframework.util.ClassUtils;

/**
 *
 * @author ronan
 */
@Service
public class BuProduct implements IBuProduct, IGetWinesParameters,  Serializable {

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
            list = daoProduct.getPromotedProductsSortedByEndDate(MAX_SE);
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
    		Integer firstRow,Integer rowsPerPage) throws WineException {
        System.out.println("categoryAccordingToObjectType");
    	wines = new ArrayList<>();
        if (!type.getType().equalsIgnoreCase("")) {
        	System.out.println("chuila");
            wines = getWinesParameters(type, o,firstRow,rowsPerPage);
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
    		Integer firstRow,Integer rowsPerPage) throws WineException {
    	System.out.println("getWinesParameters");
    	wines = new ArrayList<>();
        if (o instanceof ProductVarietal) {
            ProductVarietal varietal = (ProductVarietal) o;
            wines = getWinesParameters(type, varietal,firstRow,rowsPerPage);
        } else if (o instanceof ProductVintage) {
            ProductVintage vintage = (ProductVintage) o;
            wines = getWinesParameters(type, vintage,firstRow,rowsPerPage);
        } else if (ClassUtils.isPrimitiveOrWrapper(o.getClass())) {
            if(o.getClass().isPrimitive()) {
                wines = getWinesParameters(type,o,firstRow,rowsPerPage);
            } else {    
                Integer i = Integer.valueOf(o.toString());
                wines = getWinesParameters(type, i,firstRow,rowsPerPage);
            }
        } else {
            throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "Pas de bouteille de type : " + type.getType());
        }
    	/*if (o instanceof ProductVarietal) {
            ProductVarietal varietal = (ProductVarietal) o;
            wines = getWinesParameters(type, varietal,firstRow,rowsPerPage);
        } else if (o instanceof ProductVintage) {
            ProductVintage vintage = (ProductVintage) o;
            wines = getWinesParameters(type, vintage,firstRow,rowsPerPage);
        } else if (o instanceof String) {
            wines = getWinesParameters(type,o,firstRow,rowsPerPage);
         
        }else if (o instanceof Integer) {
            wines = getWinesParameters(type,o,firstRow,rowsPerPage);
         
        }else {
            throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "Pas de bouteille de type : " + type.getType());
        }*/
        return wines;
    }

    public List<ProductWine> getWinesParameters(ProductType type, ProductVarietal varietal, Integer firstRow,Integer rowsPerPage)
            throws WineException {
        wines = new ArrayList<>();
        wines = daoProduct.findByVarietalAndType(type, varietal,firstRow,rowsPerPage);
        return wines;
    }
    

    public List<ProductWine> getWinesParameters(ProductType type, ProductVintage vintage ,Integer firstRow,Integer rowsPerPage)
            throws WineException {
        wines = new ArrayList<>();
        wines = daoProduct.findByVintageAndType(type, vintage,firstRow,rowsPerPage);
        return wines;
    }

    public List<ProductWine> getWinesParameters(ProductType type, Integer integ,Integer firstRow,Integer rowsPerPage)
            throws WineException {
        wines = new ArrayList<>();
        switch (integ) {
            case 0:
                wines = daoProduct.findByMoneyAndType(type, integ, integ + MAX_SE,firstRow,rowsPerPage);
                break;
            case MAX_SE:
                wines = daoProduct.findByMoneyAndType(type, integ,
                        integ + MAX_SE,firstRow,rowsPerPage);
                break;
            case 2*MAX_SE:
                wines = daoProduct.findByMoneyAndType(type, integ,firstRow,rowsPerPage);
                break;
            default:
                break;
                
        }
        return wines;
    }

	@Override
	public Integer countCategoryAccordingToObjectType(ProductType type, Object o) throws WineException {
		Integer count = 0;
		if (o instanceof ProductVarietal) {
            ProductVarietal varietal = (ProductVarietal) o;
            count = daoProduct.countByVarietalAndType(type, varietal);
        } else if (o instanceof ProductVintage) {
            ProductVintage vintage = (ProductVintage) o;
            count = daoProduct.countByVintageAndType(type, vintage);
        } else if (ClassUtils.isPrimitiveOrWrapper(o.getClass())) {
            if(o.getClass().isPrimitive()) {
            	count = daoProduct.countByAppellation(type,o);
            } else {    
                Integer i = Integer.valueOf(o.toString());
                if(i==2*MAX_SE)
                	count = daoProduct.countByMoneyAndType(type, i);
                else
                	count = daoProduct.countByMoneyAndType(type, i,i+MAX_SE);
            }
        } else {
            throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "Pas de bouteille de type : " + type.getType());
        }
		return count;
	}
}
