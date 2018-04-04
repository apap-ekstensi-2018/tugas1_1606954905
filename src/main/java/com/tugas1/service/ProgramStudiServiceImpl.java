package com.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugas1.dao.ProgramStudiMapper;
import com.tugas1.model.ProgramStudiModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProgramStudiServiceImpl implements ProgramStudiService{
	@Autowired
	ProgramStudiMapper programStudiMapper;
	
	@Autowired
	FakultasServiceImpl fakultasSvc;
	
	@Override
	public ProgramStudiModel selectProgramStudi(int id) {
		log.info("get program studi by id={}", id);
		ProgramStudiModel prodiModel = programStudiMapper.selectProgramStudi(id);
		
		if(prodiModel != null) {
			 
			
			prodiModel.setFakultasModel(fakultasSvc.selectFakultas(prodiModel.getId_fakultas()));
			log.info("{}", prodiModel);
		}
		
		return prodiModel;
	}
	
	@Override
	public List<ProgramStudiModel> getAllProdi(){
		return programStudiMapper.getAllProdi();
		
	}
	
	@Override
	public List<ProgramStudiModel> getAllProdiByFakultas(int id_fakultas){
		return programStudiMapper.getAllProdiByFakultas(id_fakultas);
	}
}
