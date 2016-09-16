/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.designPattern.visitor;

import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductWine;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronan
 */
public class ProductWinesByTypeMoney implements IVisitable {
    private int choicePrice;
    private ProductType type;
    private List<ProductWine> winesByTypeMoney = new ArrayList<ProductWine>();
    
    public ProductWinesByTypeMoney() {
        super(); 
    }
    public ProductWinesByTypeMoney(ProductType paramType,
            int paramPrice) {
        super();
        this.type = paramType;
        this.choicePrice = paramPrice;
    }
    
    @Override
    public void accept(IVisitor visitor) {
       visitor.visit(this);
    }
    
    public List<ProductWine> getWinesByTypeMoney(){
        // call daoProduct to get wines according
        // to the type / money
        return  winesByTypeMoney;
    }

    //------------ Getters && Setters ---------- //
    
    public int getChoicePrice() {
        return choicePrice;
    }

    public void setChoicePrice(int choicePrice) {
        this.choicePrice = choicePrice;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
    
}
