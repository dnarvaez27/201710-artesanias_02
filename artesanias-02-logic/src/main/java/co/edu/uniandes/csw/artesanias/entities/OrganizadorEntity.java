/*
 * The MIT License
 *
 * Copyright 2017 IVAN.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IVAN
 */
@Entity
@XmlRootElement
public class OrganizadorEntity implements IUsuario, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String correo;
    private  String contrasena;
    private String foto;
    private String identificación;
    
    @OneToMany
    private List<FeriaEntity> ferias;
    
    public List<FeriaEntity> getFerias(){
        return ferias;
    }

    @Override
    public Long getId() {
        
        return id;
    }
    public String getIdentificacion(){
        return identificación;
    }

    @Override
    public String getCorreo() {
        return correo;
    }

    @Override
    public String getContrasena() {
        return contrasena;
    }

    @Override
    public String getFoto() {
        return foto;
    }

    @Override
    public void setId(Long id) {
       this.id=id;
    }

    @Override
    public void setCorreo(String correo) {
    this.correo=correo;
    }

    @Override
    public void setContrasena(String contrasena) {
        this.contrasena=contrasena;
    }

    @Override
    public void setFoto(String foto) {

     this.foto=foto;
    }
    public void setIdentificacion(String identificacion){
        this.identificación=identificacion;
    }
    

    public void setFerias(List<FeriaEntity> ferias){
        this.ferias=ferias;
    }
      @Override
    public int hashCode() {
       if( this.getId( ) != null )
		{
			return this.getId( ).hashCode( );
		}
		return super.hashCode( );
    }

    @Override
    public boolean equals(Object obj) {
       
		if( this.getId( ) != null )
		{
			
			return this.getId( ).equals( ( ( OrganizadorEntity ) obj ).getId( ) );
			
		}
		return super.equals( obj );
	}

    
    }
    
    


