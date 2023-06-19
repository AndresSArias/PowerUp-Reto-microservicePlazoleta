package com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper.request;

import com.pragma.powerup.usermicroservice.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {
    @Mapping(target = "restaurant.nit", source = "nitRestaurant")
    Order toOrder(String nitRestaurant, String idClient);

    /*
    @Mapping(source = "idPlates", target = "idPlates", qualifiedByName = "toSetLong")
    Set<Long> toIdPlates(String idPlates);
*/
    default Set<Long> toIdPlates(String idPlates) {
        Set<Long> idSet = new HashSet<>();
        if (idPlates != null && !idPlates.isEmpty()) {
            String[] idArray = idPlates.split(",");
            for (String id : idArray) {
                idSet.add(Long.parseLong(id));
            }
        }
        return idSet;
    }
    @Named("toSetLong")
    default Set<Long> toSetLong(String idPlates) {

        String[] elementos = idPlates.split(",");
        Set<Long> conjunto = new HashSet<>();
        for (String elemento : elementos) {
            conjunto.add(Long.parseLong(elemento));
        }
        return conjunto;
    }
/*
    @Mapping(source = "quantityPlates", target = "quantityPlates", qualifiedByName = "toListInteger")
    List<Integer> toQuantityPlates(String quantityPlates);
    */
    default List<Integer> toQuantityPlates(String quantityPlates) {
        List<Integer> quantityList = new ArrayList<>();
        if (quantityPlates != null && !quantityPlates.isEmpty()) {
            String[] quantityArray = quantityPlates.split(",");
            for (String quantity : quantityArray) {
                quantityList.add(Integer.parseInt(quantity));
            }
        }
        return quantityList;
    }


    @Named("toListInteger")
    default List<Integer> toListInteger(String quantityPlates) {

        String[] elementos = quantityPlates.split(",");
        List<Integer> lista = new ArrayList<>();
        for (String elemento : elementos) {
            lista.add(Integer.parseInt(elemento));
        }
        return lista;
    }
}
