package com.tugas1.service;

import java.util.List;

 
import com.tugas1.model.FakultasModel;

public interface FakultasService {
	FakultasModel selectFakultas(int id);
	
	List<FakultasModel> selectFakultasByIdUniv(int id_univ);
}
