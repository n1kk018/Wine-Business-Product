/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.designpattern.visitor;

import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVintage;

/**
 * Produit selon le Type Vintage
 *
 * @author ronan
 */
public class ProductTypeVintage extends ProductBase
        implements IVisitable {
    // millesime

    private ProductVintage vintage;

    public ProductTypeVintage(ProductType type, Integer firstRow, Integer rowsPerPage) {
        super(type, firstRow, rowsPerPage);
    }

    public ProductTypeVintage(ProductType type, ProductVintage vintage,
            Integer firstRow, Integer rowsPerPage) {
        super(type, firstRow, rowsPerPage);
        this.vintage = vintage;
    }

    @Override
    public void accept(IVisitor visitor) throws WineException {
        visitor.visit(this);
    }

    public ProductVintage getVintage() {
        return vintage;
    }

    public void setVintage(ProductVintage vintage) {
        this.vintage = vintage;
    }

}
