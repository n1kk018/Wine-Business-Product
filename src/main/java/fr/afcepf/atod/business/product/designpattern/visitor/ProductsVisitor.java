/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.designpattern.visitor;

import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.data.product.api.IDaoProduct;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductWine;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ronan
 */
public class ProductsVisitor implements
        IVisitor {
    @Autowired
    private IDaoProduct daoProduct;
    private Integer count;
    private List<Product> wines;
    
    public ProductsVisitor() {
        super();
        wines = new ArrayList<>();
    }
    
    @Override
    public void visit(IVisitable visitable) {
        // Not implemented yet
    }

    @Override
    public void visit(ProductTypeMoney typeAndMoney) throws WineException {        
        
    }

    @Override
    public void visit(ProductTypeVintage typeAndVintage) throws WineException {
        count = daoProduct.countByVintageAndType(typeAndVintage.getType(),
                typeAndVintage.getVintage());
        
        wines = daoProduct.findByVintageAndType(typeAndVintage.getType(),
                typeAndVintage.getVintage(), typeAndVintage.getFirstRow(),
                typeAndVintage.getRowsPerPage());
    }

    public IDaoProduct getDaoProduct() {
        return daoProduct;
    }

    public void setDaoProduct(IDaoProduct daoProduct) {
        this.daoProduct = daoProduct;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Product> getWines() {
        return wines;
    }

    public void setWines(List<Product> wines) {
        this.wines = wines;
    }    
}
