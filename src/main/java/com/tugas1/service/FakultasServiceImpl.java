package com.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugas1.dao.FakultasMapper;
import com.tugas1.model.FakultasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FakultasServiceImpl implements FakultasService{
	@Autowired
	FakultasMapper fakultasMapper;
	
	@Autowired
	UniversitasServiceImpl universitasSvc;
	
	@Override
	public FakultasModel selectFakultas(int id) {
		FakultasModel fakultasModel = fakultasMapper.selectFakultas(id);
		
		if(fakultasModel!=null) {
			fakultasModel.setUniversitasModel(universitasSvc.selectUniversitas(fakultasModel.getId_univ()));
		}
		return fakultasModel;
		
	}
	
	@Override
	public 	List<FakultasModel> selectFakultasByIdUniv(int id_univ){
		return fakultasMapper.selectFakultasByIdUniv(id_univ);
	}
}
