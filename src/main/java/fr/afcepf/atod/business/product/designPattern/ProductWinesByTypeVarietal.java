/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.designPattern;

import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;
import fr.afcepf.atod.wine.entity.ProductWine;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronan
 */
public class ProductWinesByTypeVarietal  implements IVisitable {
    private ProductType type;
    private ProductVarietal varietal;
    private List<ProductWine> winesByTypeVarietal = new ArrayList<ProductWine>();
    
    public ProductWinesByTypeVarietal(){
        super();
    }
    
    public ProductWinesByTypeVarietal(ProductType paramType, 
            ProductVarietal paramVarietal) {
        super();
        this.type = paramType;
        this.varietal = paramVarietal;
    }
    
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
    public List<ProductWine> getWinesByTypeVarietal(){
        // call daoProduct to get wines according
        // to the type / varietal
        return winesByTypeVarietal;
    }
    
    //------------- Getters && Setters --------//

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public ProductVarietal getVarietal() {
        return varietal;
    }

    public void setVarietal(ProductVarietal varietal) {
        this.varietal = varietal;
    }
}
