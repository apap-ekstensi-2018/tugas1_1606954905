package com.tugas1.service;

import java.util.List;

import com.tugas1.model.UniversitasModel;

public interface UniversitasService {
	UniversitasModel selectUniversitas(int id);
	List<UniversitasModel> getUniversitas();
}
