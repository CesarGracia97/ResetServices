/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.app.apps_scheme;

import dao.app.apps_user.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jyacelga
 */
@Entity
@Table(name = "apps_scheme", catalog = "aprovgrupotv", uniqueConstraints = {})
public class AppsScheme implements Serializable {

    // Fields
    private Integer id;
    private String description_scheme;
    private String name_scheme;

    
    public AppsScheme() {
    }

    public AppsScheme(Integer id,
            String description_scheme,
            String name_scheme) {
        this.id = id;
        this.description_scheme = description_scheme;
        this.name_scheme = name_scheme;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "description_scheme", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    public String getDescription_scheme() {
        return description_scheme;
    }

    public void setDescription_scheme(String description_scheme) {
        this.description_scheme = description_scheme;
    }

    @Column(name = "name_scheme", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    public String getName_scheme() {
        return name_scheme;
    }

    public void setName_scheme(String name_scheme) {
        this.name_scheme = name_scheme;
    }

}
