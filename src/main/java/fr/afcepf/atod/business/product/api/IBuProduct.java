/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.aop.ThrowsAdvice;

import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;

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
    Map<ProductType, List<String>> getAppellationsByType(List<ProductType> wineTypes) throws WineException;
    Map<ProductType, List<ProductVarietal>> getVarietalsByType(List<ProductType> wineTypes) throws WineException;
}
