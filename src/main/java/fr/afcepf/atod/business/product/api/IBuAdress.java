package fr.afcepf.atod.business.product.api;

import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.Adress;

public interface IBuAdress {
    Adress addNewAdress(Adress adr);
    Boolean updateNewAddress(Adress adr) throws WineException;
    Adress findAddress(Integer idAddress)throws WineException;
}
