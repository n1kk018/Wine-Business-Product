package fr.afcepf.atod.business.product.api;

import java.util.List;

import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.Country;

public interface IBuCountry {
    List<Country> listAllCountries() throws WineException;

}
