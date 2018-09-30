package com.piratebrook.apksever.service;

import com.piratebrook.apksever.entity.Zoning;

import java.io.IOException;
import java.util.List;

public interface ZoneService {

    Zoning findOne(Integer id);

    List<Zoning> findAll() throws IOException;

    void insertOne(Zoning zoning);
}
