package fr.afcepf.atod.business.product.api;

import java.util.List;

import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.City;

public interface IBuCity {
	List<City> findAllCities() throws WineException;
	
	City addNewCity(City city) throws WineException;
}
