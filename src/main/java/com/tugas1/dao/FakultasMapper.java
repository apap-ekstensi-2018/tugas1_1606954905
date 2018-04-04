package com.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tugas1.model.FakultasModel;

@Mapper
public interface FakultasMapper {
	@Select("select id, kode_fakultas, nama_fakultas, id_univ from fakultas where id = #{id}")
	FakultasModel selectFakultas(@Param("id") int id);
	
	@Select("select id, kode_fakultas, nama_fakultas, id_univ from fakultas where id_univ = #{id_univ}")
	List<FakultasModel> selectFakultasByIdUniv(@Param("id_univ") int id_univ);
}
