/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afcepf.atod.business.product.api.IBuProduct;
import fr.afcepf.atod.vin.data.exception.WineErrorCode;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.data.product.api.IDaoProduct;
import fr.afcepf.atod.wine.data.product.api.IDaoProductType;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;
import fr.afcepf.atod.wine.entity.ProductVintage;

/**
 *
 * @author ronan
 */
@Service
public class BuProduct implements IBuProduct {

    @Autowired
    private IDaoProduct daoProduct;
    @Autowired
    private IDaoProductType daoProductType;
    private static final int MAX_SE = 10;

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
        } catch (Exception e)  {
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
    public List<Product> findExpensive(double min) throws WineException {
        List<Product> expensiveProds = null;
        if (min >= 0) {
            expensiveProds = daoProduct.findExpensiveProducts(min);
            if (expensiveProds.isEmpty()){
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
	public List<Product> getPromotedProductsSelection() throws WineException {
		List<Product> list = null;
		try {
            list = daoProduct.getPromotedProductsSortedByEndDate(MAX_SE);
        } catch (Exception e)  {
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
        } catch (Exception e)  {
            throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE, 
                    "no promoted item referenced in db");
        }
        return list;
	}

	@Override
	public List<Product> findProductsByName(String name) throws WineException {
		List<Product> list=null;
		try {
			list =daoProduct.findByNotCompleteName(name);
		} catch (Exception e) {
			throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE, 
                    "no promoted item referenced in db");
		}
		return list;
	}

	@Override
	public List<Product> findProductsByAppelation(String appelation) throws WineException {
		List<Product> list=null;
		try {
			list =daoProduct.findByAppelation(appelation);
		} catch (Exception e) {
			throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE, 
                    "no promoted item referenced in db");
		}
		return list;
	}

	@Override
	public List<ProductVintage> findProductsByVintage(Integer vintage) throws WineException {
		List<ProductVintage> list=null;
		try {
			list=daoProduct.findByVintage(vintage);
		} catch (Exception e) {
			throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE, 
                    "no promoted item referenced in db");
		}
		return list;
	}

	@Override
	public List<ProductVarietal> findProductsBy(String variatal) throws WineException {
		List<ProductVarietal> list=null;
		try {
			list=daoProduct.findByVariatal(variatal);
		} catch (Exception e) {
			throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE, 
                    "no promoted item referenced in db");
		}
		return list;
	}

	@Override
	public List<ProductType> findProductsByType(String wineType) throws WineException {
		List<ProductType> list=null;
		try {
			list=daoProduct.findByType(wineType);
		} catch (Exception e) {
			throw new WineException(
                    WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE, 
                    "no promoted item referenced in db");
		}
		return list;
	}

}
