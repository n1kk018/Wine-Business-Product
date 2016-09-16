/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.designPattern;

import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVintage;
import fr.afcepf.atod.wine.entity.ProductWine;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronan
 */
public class ProductWinesByTypeAndVintage implements IVisitable {
    private ProductType type;
    private ProductVintage vintage;
    private List<ProductWine> winesByTypeVintage
            = new ArrayList<ProductWine>();
    
    public ProductWinesByTypeAndVintage() {
        super();
    }
    public ProductWinesByTypeAndVintage(ProductType paramType,
            ProductVintage paramVintage){
        super();
        this.type = paramType;
        this.vintage = paramVintage;
        
    }
    
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
    
    public List<ProductWine> GetWinesByTypeVintage() {
        // call dao ==> fill the list
        return winesByTypeVintage;
    }
    
    // --------- Getters && Setters ----------- //

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public ProductVintage getVintage() {
        return vintage;
    }

    public void setVintage(ProductVintage vintage) {
        this.vintage = vintage;
    }
    
    
}
