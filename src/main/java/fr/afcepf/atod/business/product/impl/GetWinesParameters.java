/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.impl;

import fr.afcepf.atod.business.product.api.IGetWinesParameters;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;
import fr.afcepf.atod.wine.entity.ProductVintage;
import fr.afcepf.atod.wine.entity.ProductWine;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronan
 */
public class GetWinesParameters extends BuProduct implements IGetWinesParameters {
    @Override
    public List<ProductWine> getWinesParameters(ProductType type, Object o) throws WineException {
        return null;
    }
    @Override
    public List<ProductWine> getWinesParameters(ProductType type, ProductVarietal varietal) throws WineException {
        List<ProductWine> wines = new ArrayList<ProductWine>();
        wines = daoProduct.findByVarietalAndType(type, varietal);
        return wines;
    }

    @Override
    public List<ProductWine> getWinesParameters(ProductType type, ProductVintage vintage) throws WineException {
        List<ProductWine> wines = new ArrayList<ProductWine>();
        wines = daoProduct.findByVintageAndType(type, vintage);
        return wines;
    }

    @Override
    public List<ProductWine> getWinesParameters(ProductType type, Integer integ) throws WineException {
        List<ProductWine> wines = new ArrayList<ProductWine>();

        return wines;
    }

    

}
