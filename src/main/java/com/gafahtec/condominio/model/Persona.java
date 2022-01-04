package com.gafahtec.condominio.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = { "departamentos", "cocheras", "vehiculos" })
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPersona;
	@NotEmpty(message = "Campo nombres no puede estar vacio")
	private String nombres;
	@NotEmpty(message = "Campo apellido paterno no puede estar vacio")
	private String apellido_paterno;
	@NotEmpty(message = "Campo apellido materno no puede estar vacio")
	private String apellido_materno;
	@NotEmpty(message = "Campo dni no puede estar vacio")
	private String dni;

	private String telefono;
	private String celular;
	private Integer idPersonaDaPermiso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_persona", nullable = false, foreignKey = @ForeignKey(name = "FK_tipo_persona"))
	private TipoPersona tipoPersona;

	
	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	@JoinTable(name = "persona_cochera", 
			   joinColumns = @JoinColumn(name = "id_persona", referencedColumnName = "idPersona"), 
			   inverseJoinColumns = @JoinColumn(name = "id_cochera", referencedColumnName = "idCochera"))
	private Set<Cochera> cocheras;

	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	@JoinTable(name = "persona_departamento", 
			   joinColumns = @JoinColumn(name = "id_persona", referencedColumnName = "idPersona"), 
			   inverseJoinColumns = @JoinColumn(name = "id_departamento", referencedColumnName = "idDepartamento"))
	private Set<Departamento> departamentos;

	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	@JoinTable(name = "persona_vehiculo", 
			   joinColumns = @JoinColumn(name = "id_persona", referencedColumnName = "idPersona"), 
			   inverseJoinColumns = @JoinColumn(name = "id_vehiculo", referencedColumnName = "idVehiculo"))
	private Set<Vehiculo> vehiculos;

}
