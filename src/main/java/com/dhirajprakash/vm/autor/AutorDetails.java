package com.dhirajprakash.vm.autor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTOR_DETAILS")
public class AutorDetails {
	
	 	@Id
	    @Column(name = "ID")
	    private Integer id;

	    @Column(name = "LAST_NAME")
	    private String lastName;
	    
	    @Column(name = "AUTOR_ID")
	    private Integer autorId;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public Integer getAutorId() {
			return autorId;
		}

		public void setAutorId(Integer autorId) {
			this.autorId = autorId;
		}

		@Override
		public String toString() {
			return "AutorDetails [id=" + id + ", lastName=" + lastName + ", autorId=" + autorId + "]";
		}
	    
	    

}
