package com.tugas1.service;


import java.util.List;


import com.tugas1.model.MahasiswaModel;

public interface MahasiswaService {
	
	MahasiswaModel selectMahasiswa(String npm);
	
	void addMahasiswa(MahasiswaModel model);
	
	String generateNPM(String tahunMasuk, String kodeUniversitas, String kodeProdi, String jalurMasuk);
	
	void updateMahasiswa(MahasiswaModel model);
	
	MahasiswaModel getPercentageKelulusan(int id_prodi, String tahun_masuk);
	
	List<MahasiswaModel> selectMahasiswaByProdi(int id_prodi);
}
