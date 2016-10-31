/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.api;

import java.util.List;
import java.util.Map;

import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;
import fr.afcepf.atod.wine.entity.ProductVintage;
import fr.afcepf.atod.wine.entity.ProductWine;

import java.util.List;

/**
 *
 * @author ronan
 */
public interface IBuProduct {

    /**
     * methode pour trouver un vin par son nom
     *
     * @param name
     * @return
     * @throws fr.afcepf.atod.vin.data.exception.WineException
     */
    Product findByName(String name) throws WineException;
    /**
     * 
     * @param id
     * @return
     * @throws WineException 
     */
    Product findById(Integer id) throws WineException;
    /**
     * 
     * @return
     * @throws WineException 
     */
    List<Product> getPromotedProductsSelection() throws WineException;
    /**
     * 
     * @param min
     * @return
     * @throws WineException 
     */
    List<Product> findExpensive(double min) throws WineException;
    /**
     * 
     * @return
     * @throws WineException 
     */
    List<ProductType> getWineTypes() throws WineException;
    /**
     * 
     * @param name
     * @return
     * @throws WineException 
     */
    List<Product> findProductsByName(String name) throws WineException;
    /**
     * 
     * @param appelation
     * @return
     * @throws WineException 
     */
    List<Product> findProductsByAppelation(String appelation) throws WineException;
    /**
     * 
     * @param vintage
     * @return
     * @throws WineException 
     */
    List<ProductVintage> findProductsByVintage(Integer vintage) throws WineException;
    /**
     * 
     * @param variatal
     * @return
     * @throws WineException 
     */
    List<ProductVarietal> findProductsBy(String variatal) throws WineException;
    /**
     * 
     * @param wineType
     * @return
     * @throws WineException 
     */
    List<ProductType> findProductsType(String wineType) throws WineException;
    /**
     * 
     * @param wineTypes
     * @return
     * @throws WineException 
     */
    Map<ProductType, List<String>> getAppellationsByType(List<ProductType> wineTypes) throws WineException;
    /**
     * 
     * @param wineTypes
     * @return
     * @throws WineException 
     */
    Map<ProductType, List<ProductVarietal>> getVarietalsByType(List<ProductType> wineTypes) throws WineException;
    /**
     * 
     * @param type
     * @param o
     * @return
     * @throws WineException 
     */
    List<Product> categoryAccordingToObjectType(ProductType type, Object o,Integer firstRow,Integer rowsPerPage, String sorting) throws WineException;
    
    Integer countCategoryAccordingToObjectType(ProductType type, Object o) throws WineException;
    
    Map<ProductType,Map<Integer, Integer>> getPricesRepartitionByType(List<ProductType> wineTypes) throws WineException;
}