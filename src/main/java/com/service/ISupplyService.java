package com.service;

import com.model.Supply;

import java.util.List;

public interface ISupplyService extends ICrudService<Supply>{
    List<Supply> getByIdList(List<Supply> supply);
    List<Supply> getAllActive();
}
