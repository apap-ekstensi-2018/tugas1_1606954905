package com.tugas1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FakultasModel {
	private int id;
	private String kode_fakultas;
	private String nama_fakultas;
	private int id_univ;
	private UniversitasModel universitasModel;
	public boolean isSelected(Integer fakId){
        if (fakId != null) {
            return fakId.equals(id);
        }
        return false;
    }
}
