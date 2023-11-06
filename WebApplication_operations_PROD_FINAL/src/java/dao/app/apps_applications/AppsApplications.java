/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.app.apps_applications;

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
@Table(name = "apps_applications", catalog = "aprovgrupotv", uniqueConstraints = {})
public class AppsApplications implements Serializable {

  // Fields
    private Integer id;
    private String name_application;
    private String description_application;

    public AppsApplications() {
    }
 
    
    public AppsApplications(Integer id,
            String name_application,
            String description_application) {
        this.id = id;
        this.name_application = name_application;
        this.description_application = description_application;
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

    @Column(name = "name_application", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    public String getName_application() {
        return name_application;
    }

    public void setName_application(String name_application) {
        this.name_application = name_application;
    }


    @Column(name = "description_application", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    public String getDescription_application() {
        return description_application;
    }

    public void setDescription_application(String description_application) {
        this.description_application = description_application;
    }   
}
