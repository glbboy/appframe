package com.cnframe.model;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

//JPA 基类的标识
@MappedSuperclass
public abstract class AbstractModel implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="IdGenerator")
	@GenericGenerator(name="IdGenerator", strategy = "com.cnframe.model.IdGenerator")
	@Column(name = "ID", unique=true,nullable = false)
	protected Long id;
	
	@Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public boolean equals(Object obj) {
		if (obj == null){              
			return false;         
		} else if (!(obj instanceof AbstractModel)){
			return false;         
		} else if (((AbstractModel) obj).id.equals(this.id)){
			return true;         
		} else{
			return false;         
		}     
	}  
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

