/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.designpattern.visitor;

import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.ProductWine;

/**
 *
 * @author ronan
 */
public class ProductTypeMoney extends ProductWine
        implements IVisitable {

    @Override
    public void accept(IVisitor visitor) throws WineException {
        visitor.visit(this);
    }
    
}
