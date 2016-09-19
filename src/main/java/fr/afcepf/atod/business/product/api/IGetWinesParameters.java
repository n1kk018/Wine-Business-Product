/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.api;

import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;
import fr.afcepf.atod.wine.entity.ProductVintage;
import fr.afcepf.atod.wine.entity.ProductWine;
import java.util.List;

/**
 *
 * @author ronan
 */
public interface IGetWinesParameters {
    /**
     * 
     * @param type
     * @param o
     * @return
     * @throws WineException 
     */
     List<ProductWine> getWinesParameters(ProductType type, Object o,Integer firstRow,Integer rowsPerPage)
            throws WineException;
    /**
     * 
     * @param type
     * @param varietal
     * @return
     * @throws WineException 
     */
    List<ProductWine> getWinesParameters(ProductType type, ProductVarietal varietal,Integer firstRow,Integer rowsPerPage)
            throws WineException;
    /**
     * 
     * @param type
     * @param vintage
     * @return
     * @throws WineException 
     */
    List<ProductWine> getWinesParameters(ProductType type, ProductVintage vintage,Integer firstRow,Integer rowsPerPage)
            throws WineException;
    /**
     * 
     * @param type
     * @param integ
     * @return
     * @throws WineException 
     */
    List<ProductWine> getWinesParameters(ProductType type, Integer integ,Integer firstRow,Integer rowsPerPage) 
            throws WineException;
            
}
