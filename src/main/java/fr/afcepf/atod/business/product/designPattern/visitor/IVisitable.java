/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.designPattern.visitor;

import fr.afcepf.atod.wine.entity.ProductVarietal;
import fr.afcepf.atod.wine.entity.ProductVintage;

/**
 *
 * @author ronan
 */
public interface IVisitable {
    void accept(IVisitor visitor);
}
