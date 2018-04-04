package com.tugas1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversitasModel {
	private int id;
	private String kode_univ;
	private String nama_univ;
	public boolean isSelected(Integer univId){
        if (univId != null) {
            return univId.equals(id);
        }
        return false;
    }
}
