/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.api;

import java.util.List;

import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;
import fr.afcepf.atod.wine.entity.ProductVintage;

import java.util.List;

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
    List<Product> findExpensive(double min) throws WineException;
    List<ProductType> getWineTypes() throws WineException;
    
    List<Product> findProductsByName(String name) throws WineException;
    
	List<Product> findProductsByAppelation(String appelation) throws WineException;

	List<ProductVintage> findProductsByVintage(Integer vintage) throws WineException;

	List<ProductVarietal> findProductsBy(String variatal) throws WineException;
	
	List<ProductType> findProductsByType(String wineType) throws WineException;
}
