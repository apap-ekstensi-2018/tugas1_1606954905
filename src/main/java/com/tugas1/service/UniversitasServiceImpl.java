package com.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugas1.dao.UniversitasMapper;
import com.tugas1.model.UniversitasModel;

@Service
public class UniversitasServiceImpl implements UniversitasService{
	@Autowired
	UniversitasMapper universitasMapper;
	
	@Override
	public UniversitasModel selectUniversitas(int id) {
		return universitasMapper.selectUniversitas(id);
	}
	
	@Override
	public List<UniversitasModel> getUniversitas(){
		return universitasMapper.getUniversitas();
	}
	
}
