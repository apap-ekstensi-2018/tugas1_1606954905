package com.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tugas1.model.MahasiswaModel;

@Mapper
public interface MahasiswaMapper {

	@Select("select id, npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, status, tahun_masuk, jalur_masuk, id_prodi from mahasiswa where npm = #{npm}")
    MahasiswaModel selectMahasiswa(@Param("npm") String npm);
	
	@Select("select id, npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, status, tahun_masuk, jalur_masuk, id_prodi from mahasiswa where id_prodi = #{id_prodi}")
    List<MahasiswaModel> selectMahasiswaByProdi(@Param("id_prodi") int id_prodi);
	
	@Insert("Insert Into mahasiswa (npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, status, tahun_masuk, jalur_masuk, id_prodi) "
			+ " Values (#{npm}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{agama}, #{golongan_darah}, #{status}, #{tahun_masuk}, #{jalur_masuk}, #{id_prodi})")
	void addMahasiswa(MahasiswaModel model);
	
	
	@Select("select max(npm) as npm from mahasiswa where npm like #{npm}")
    String getMaxNpm(@Param("npm") String npm);
	
	@Update("Update mahasiswa set npm=#{npm} , nama=#{nama} , tempat_lahir=#{tempat_lahir}, tanggal_lahir=#{tanggal_lahir}, "
			+ "jenis_kelamin=#{jenis_kelamin}, agama=#{agama}, golongan_darah=#{golongan_darah}, status=#{status}, tahun_masuk=#{tahun_masuk}, jalur_masuk=#{jalur_masuk}, id_prodi=#{id_prodi}"
			+ " Where id=#{id} ")
	void updateMahasiswa(MahasiswaModel model);
	
	@Select("select count(*) as total_lulus, "
			+ " (select count(*) from mahasiswa where id_prodi=#{id_prodi} and tahun_masuk=#{tahun_masuk} ) as 'total_mahasiswa', "
			+ " (COUNT(*)/ (select count(*) from mahasiswa where id_prodi=#{id_prodi} and tahun_masuk=#{tahun_masuk} )) * 100 as 'percentage'"
			+ " from mahasiswa where id_prodi=#{id_prodi} and tahun_masuk=#{tahun_masuk} and status = 'Lulus'" + 
			"")
	MahasiswaModel getPercentageKelulusan(@Param("id_prodi") int id_prodi, @Param("tahun_masuk") String tahun_masuk);
}
