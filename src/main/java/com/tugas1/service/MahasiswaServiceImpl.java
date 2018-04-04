package com.tugas1.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugas1.dao.MahasiswaMapper;
import com.tugas1.model.MahasiswaModel;
import com.tugas1.model.ProgramStudiModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MahasiswaServiceImpl implements MahasiswaService {
	 
		@Autowired
	    private MahasiswaMapper mahasiswaMapper;

		@Autowired
		private ProgramStudiServiceImpl serviceProdi;

	    @Override
	    public MahasiswaModel selectMahasiswa(String npm)
	    {
	        log.info ("select mahasiswa with npm {}", npm);
	        MahasiswaModel mhsModel = mahasiswaMapper.selectMahasiswa(npm);
	        
	        // set return program studi by mahasiswa prodi id
	        if(mhsModel!=null) {
	        		ProgramStudiModel prodiModel = serviceProdi.selectProgramStudi(mhsModel.getId_prodi());
	        		log.info("{}", prodiModel);
	        		mhsModel.setProgramStudiModel(prodiModel);
	        }
	        
	        return mhsModel;
	    }
	    
	    @Override
	    public void addMahasiswa(MahasiswaModel model) {
	    		// check condition to generate NPM
	    		 mahasiswaMapper.addMahasiswa(model);
	    }
	    
	    @Override
	    public String generateNPM(String tahunMasuk, String kodeUniversitas, String kodeProdi, String jalurMasuk) {
	    		String npm = "";
	    		String kodeJalurMasuk = "";
	    		
	    		if(jalurMasuk.equals("Undangan Olimpiade")) {
	    			kodeJalurMasuk = "53";
	    		}
	    		if(jalurMasuk.equals("Undangan Reguler/SNMPTN")) {
	    			kodeJalurMasuk = "54";
	    		}
	    		if(jalurMasuk.equals("Undangan Paralel/PPKB")) {
	    			kodeJalurMasuk = "55";
	    		}
	    		if(jalurMasuk.equals("Ujian Tulis Bersama/SBMPTN")) {
	    			kodeJalurMasuk = "57";
	    		}
	    		if(jalurMasuk.equals("Ujian Tulis Mandiri")) {
	    			kodeJalurMasuk = "62";
	    		}
	    		
    			String current_npm = tahunMasuk + kodeUniversitas + kodeProdi + kodeJalurMasuk;
    			log.info("{}", current_npm);
    			String maxNpm = mahasiswaMapper.getMaxNpm(current_npm + "%");
    			log.info("{}", maxNpm);
    			if(!maxNpm.equals("")) {
    				
    				// proceed to remerge with new npm
    				String lastNoUrut = "00" + String.valueOf(Integer.parseInt(maxNpm.substring(9, 12)) + 1);
    				log.info("{}", lastNoUrut);
    				
    				npm = current_npm + lastNoUrut.substring(lastNoUrut.length()-3);
    				log.info("{}", npm);
    			}
    			
	    		return npm;
	    }
	    
	    @Override
	    public void updateMahasiswa(MahasiswaModel model) {
	    		mahasiswaMapper.updateMahasiswa(model);
	    }
	    
	    @Override
	    public MahasiswaModel getPercentageKelulusan(int id_prodi, String tahun_masuk) {
	    		MahasiswaModel resultLulusan =  mahasiswaMapper.getPercentageKelulusan(id_prodi, tahun_masuk);
	    				
	    		ProgramStudiModel prodiModel = serviceProdi.selectProgramStudi(id_prodi);
        		log.info("{}", prodiModel);
        		resultLulusan.setProgramStudiModel(prodiModel);
	    		return resultLulusan;
	    }
	    
	    @Override
	    public List<MahasiswaModel> selectMahasiswaByProdi(int id_prodi){
	    		return mahasiswaMapper.selectMahasiswaByProdi(id_prodi);
	    }
	    
}
