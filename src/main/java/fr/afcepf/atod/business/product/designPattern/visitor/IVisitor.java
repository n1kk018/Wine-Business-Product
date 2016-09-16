/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.business.product.designPattern.visitor;

/**
 *
 * @author ronan
 */
public interface IVisitor {
    /**
     * 
     * @param winesTypeVintage 
     */
    void visit(ProductWinesByTypeAndVintage winesTypeVintage);
    /**
     * 
     * @param winesTypeVarietal 
     */
    void visit(ProductWinesByTypeVarietal winesTypeVarietal);
    /**
     * 
     * @param winesTypeMoney 
     */
    void visit(ProductWinesByTypeMoney winesTypeMoney);
    
}
