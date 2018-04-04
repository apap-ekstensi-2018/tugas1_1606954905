package com.tugas1.service;

import java.util.List;


import com.tugas1.model.ProgramStudiModel;

public interface ProgramStudiService {
	ProgramStudiModel selectProgramStudi(int id);
	List<ProgramStudiModel> getAllProdi();
	List<ProgramStudiModel> getAllProdiByFakultas(int id_fakultas);
}
