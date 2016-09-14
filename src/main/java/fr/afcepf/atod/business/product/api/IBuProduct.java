/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.api;

import java.util.List;

import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.Product;

/**
 *
 * @author ronan
 */
public interface IBuProduct {
    /**
     * methode pour trouver un vin par
     * son nom
     * @param name
     * @return 
     * @throws fr.afcepf.atod.vin.data.exception.WineException 
     */
    Product findByName(String name) throws WineException;
    
    List<Product> getPromotedProductsSelection() throws WineException;
}
