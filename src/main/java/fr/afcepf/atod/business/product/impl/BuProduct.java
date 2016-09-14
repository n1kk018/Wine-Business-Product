/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.impl;

import fr.afcepf.atod.business.product.api.IBuProduct;
import fr.afcepf.atod.vin.data.exception.WineErrorCode;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.data.product.api.IDaoProduct;
import fr.afcepf.atod.wine.entity.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ronan
 */
@Service
public class BuProduct implements IBuProduct {

    @Autowired
    private IDaoProduct daoProduct;
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

}
