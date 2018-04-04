package com.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tugas1.model.UniversitasModel;

@Mapper
public interface UniversitasMapper {
	@Select("select id, kode_univ, nama_univ from universitas where id = #{id}")
	UniversitasModel selectUniversitas(@Param("id") int id);
	
	@Select("select id, kode_univ, nama_univ from universitas")
	List<UniversitasModel> getUniversitas();
}
