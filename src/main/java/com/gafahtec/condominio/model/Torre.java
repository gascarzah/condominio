package com.gafahtec.condominio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = { "departamentos" })
public class Torre {

    @Id
    @GeneratedValue
    private Integer idTorre;
    private Integer numero;
    
    @JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "torre", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<Departamento> departamentos = new ArrayList<>(); 
}
