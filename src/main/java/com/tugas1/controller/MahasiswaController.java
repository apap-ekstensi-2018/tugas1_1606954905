package com.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tugas1.model.FakultasModel;
import com.tugas1.model.MahasiswaModel;
import com.tugas1.model.ProgramStudiModel;
import com.tugas1.model.UniversitasModel;
import com.tugas1.service.FakultasService;
import com.tugas1.service.MahasiswaService;
import com.tugas1.service.ProgramStudiService;
import com.tugas1.service.UniversitasService;

 
@Controller
public class MahasiswaController {
	
	@Autowired
	MahasiswaService mahasiswaService;
	
	@Autowired
	ProgramStudiService prodiSvc;
	

	@Autowired
	FakultasService fakultasSvc;
	

	@Autowired
	UniversitasService universitasSvc;
	
	 @RequestMapping("/")
	    public String index (Model model)
	    {
	        model.addAttribute("title", "Home");
	        return "index";
	    }
	 
	 @RequestMapping("/mahasiswa")
	    public String viewSelectMahasiswa (Model model,
	            @RequestParam(value = "npm", required = true) String npm)
	    {
		 	MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswa(npm);
		 	model.addAttribute("title", "View Detail Mahasiswa");
		 	System.out.println(mahasiswa);
		 	if (mahasiswa != null) {
		 		model.addAttribute("mahasiswa", mahasiswa);
		        return "detail-mahasiswa";
		 	}
		 	else {
		 		model.addAttribute("npm", npm);
		        return "not-found";
		 	}
	        
	    }
	 
	 @RequestMapping("/kelulusan/result")
	    public String viewKelulusan (Model model,
	            @RequestParam(value = "thn", required = true) String thn,
	            @RequestParam(value = "prodi", required = true) int prodi)
	    {
		 	MahasiswaModel mahasiswa = mahasiswaService.getPercentageKelulusan(prodi, thn);
		 	model.addAttribute("title", "View Kelulusan");
		 	System.out.println(mahasiswa);
		 	if (mahasiswa != null) {
		 		model.addAttribute("mahasiswa", mahasiswa);
		 		model.addAttribute("tahunkelulusan", thn);
		        return "kelulusan-result";
		 	}
		 	else { 
		        return "not-found-kelulusan";
		 	}
	        
	    }
	 
	 @RequestMapping("/mahasiswa/cari")
	    public String cariMahasiswa (Model model,
	            @RequestParam(value = "univ", required = false) Integer univ,
	            @RequestParam(value = "fakultas", required = false) Integer fakultas,
	            @RequestParam(value = "prodi", required = false) Integer prodi)
	    {
		 List<UniversitasModel> univModel = universitasSvc.getUniversitas();
		 	//MahasiswaModel mahasiswa = mahasiswaService.getPercentageKelulusan(prodi, thn);
		 	//model.addAttribute("title", "View Kelulusan");
		 	//System.out.println(mahasiswa); 
	 		model.addAttribute("listuniv", univModel);
	 		model.addAttribute("listProdi", null);
	 		model.addAttribute("listfakultas", null);
	 		model.addAttribute("listmhs", null);
		 	if (univ != null) {
		 		List<FakultasModel> fakultasModel = fakultasSvc.selectFakultasByIdUniv(univ);  
				 
				 model.addAttribute("listfakultas", fakultasModel);
				 model.addAttribute("univ", univ);
		 	}
		 	
		 	if (fakultas != null) {
		 		 List<ProgramStudiModel> prodiModel = prodiSvc.getAllProdiByFakultas(fakultas);
				 
				 model.addAttribute("listProdi", prodiModel);
				 
				 model.addAttribute("fakultas", fakultas);
		 	}
		 	
		 	if(univ != null && fakultas != null && prodi != null) {
		 		// execute search filter
		 		List<MahasiswaModel> listMhs = mahasiswaService.selectMahasiswaByProdi(prodi);
		 		model.addAttribute("listuniv", null);
		 		model.addAttribute("listProdi", null);
		 		model.addAttribute("listfakultas", null);
		 		model.addAttribute("listmhs", listMhs);
		 		
		 	}
		 	 
		 	return "cari-mahasiswa";
	    }
	 
	 @RequestMapping("/mahasiswa/tambah")
	 public String tambahMahasiswa(Model model) {
		 List<ProgramStudiModel> prodiModel = prodiSvc.getAllProdi();
		 
		 model.addAttribute("listProdi", prodiModel);
		 return "form-entry";
	 }
	 
	 @RequestMapping("/kelulusan")
	 public String kelulusanMahasiswa(Model model) {
		 List<ProgramStudiModel> prodiModel = prodiSvc.getAllProdi();
		 
		 model.addAttribute("listProdi", prodiModel);
		 return "kelulusan";
	 }
	 
	 @RequestMapping("/mahasiswa/ubah/{npm}")
	    public String updatePath (Model model,
	            @PathVariable(value = "npm") String npm)
	    {
	        MahasiswaModel mhs = mahasiswaService.selectMahasiswa(npm);

	        if (mhs != null) {
	        	List<ProgramStudiModel> prodiModel = prodiSvc.getAllProdi();
	   		 
	   		 model.addAttribute("listProdi", prodiModel);
	             model.addAttribute ("mahasiswa", mhs);
	            
	            return "form-update-object";
	        } else {
	            model.addAttribute ("npm", npm);
	            return "not-found";
	        }
	    }
	 
	 @PostMapping(value = "/mahasiswa/tambah")
	    public String submitTambahMahasiswa(@ModelAttribute MahasiswaModel mahasiswa, Model model)
	    {
	    		if (mahasiswa != null)
	    		{
	    			String tahun_masuk = mahasiswa.getTahun_masuk().substring(2);
	    		 	ProgramStudiModel prodiModel = prodiSvc.selectProgramStudi(mahasiswa.getId_prodi());  
	    		 	
	    		 	String npm_new = mahasiswaService.generateNPM(tahun_masuk, prodiModel.getFakultasModel().getUniversitasModel().getKode_univ(), prodiModel.getKode_prodi(),
	    		 			mahasiswa.getJalur_masuk());
	    		 	  
	    		 	
	    			mahasiswa.setNpm(npm_new);
	    			mahasiswa.setStatus("Hidup");
	    			mahasiswaService.addMahasiswa(mahasiswa);
	    		 }
	    		
	         model.addAttribute("npm", mahasiswa.getNpm());
	         return "success-add";
	    }
	 
	 @PostMapping(value = "/mahasiswa/ubah")
	    public String submitUbahMahasiswa(@ModelAttribute MahasiswaModel mahasiswa, Model model)
	    {
		 	model.addAttribute("npm", mahasiswa.getNpm());
	    		if (mahasiswa != null)
	    		{
	    			Boolean boolRegenerateNpm = false;
	    			// get model from db to compare whether have modify value impact to NPM
	    			MahasiswaModel mhsModelDb = mahasiswaService.selectMahasiswa(mahasiswa.getNpm());
	    			if(mhsModelDb.getId_prodi() != mahasiswa.getId_prodi())
	    			{
	    				System.out.println("beda id prodi");
	    				boolRegenerateNpm=true;
	    			}
	    			
	    			if(!mhsModelDb.getTahun_masuk().equals(mahasiswa.getTahun_masuk()))
	    			{
	    				System.out.println("beda tahun masuk " + mhsModelDb.getTahun_masuk() + " - " + mahasiswa.getTahun_masuk());
	    				boolRegenerateNpm=true;
	    			}
	    			
	    			if(!mhsModelDb.getJalur_masuk().equals(mahasiswa.getJalur_masuk()))
	    			{
	    				System.out.println("Beda jalur masuk " + mhsModelDb.getJalur_masuk() + " - " + mahasiswa.getJalur_masuk());
	    				boolRegenerateNpm=true;
	    			}
	    			
	    			if(boolRegenerateNpm) {
	    				String tahun_masuk = mahasiswa.getTahun_masuk().substring(2);
		    		 	ProgramStudiModel prodiModel = prodiSvc.selectProgramStudi(mahasiswa.getId_prodi());  
		    		 	
		    		 	String npm_new = mahasiswaService.generateNPM(tahun_masuk, prodiModel.getFakultasModel().getUniversitasModel().getKode_univ(), prodiModel.getKode_prodi(),
		    		 			mahasiswa.getJalur_masuk());
		    		 	  
		    		 	
		    			mahasiswa.setNpm(npm_new);
		    			
	    			}
	    			mahasiswa.setStatus(mhsModelDb.getStatus());
	    			mahasiswaService.updateMahasiswa(mahasiswa);
	    		 }
	    		
	        
	         return "success-update";
	    }
}
