package com.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tugas1.model.ProgramStudiModel;

@Mapper
public interface ProgramStudiMapper {
	@Select("select id, kode_prodi, nama_prodi, id_fakultas from program_studi where id = #{id}")
	ProgramStudiModel selectProgramStudi(@Param("id") int id);
	
	@Select("select id, kode_prodi, nama_prodi, id_fakultas from program_studi")
	List<ProgramStudiModel> getAllProdi();
	
	@Select("select id, kode_prodi, nama_prodi, id_fakultas from program_studi where id_fakultas= #{id_fakultas}")
	List<ProgramStudiModel> getAllProdiByFakultas(@Param("id_fakultas") int id_fakultas);
}
