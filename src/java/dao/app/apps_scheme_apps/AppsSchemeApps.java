/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.app.apps_scheme_apps;

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
@Table(name = "apps_scheme_apps", catalog = "aprovgrupotv", uniqueConstraints = {})
public class AppsSchemeApps implements Serializable {

    // Fields
    private Integer id;
    private String id_apps_scheme;
    private String id_apps_apps;

    public AppsSchemeApps() {
    }

    
    public AppsSchemeApps(Integer id,
            String id_apps_scheme,
            String id_apps_apps) {
        this.id = id;
        this.id_apps_scheme = id_apps_scheme;
        this.id_apps_apps = id_apps_apps;

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

    @Column(name = "id_apps_scheme", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    public String getId_apps_scheme() {
        return id_apps_scheme;
    }

    public void setId_apps_scheme(String id_apps_scheme) {
        this.id_apps_scheme = id_apps_scheme;
    }

    @Column(name = "id_apps_apps", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    public String getId_apps_apps() {
        return id_apps_apps;
    }

    public void setId_apps_apps(String id_apps_apps) {
        this.id_apps_apps = id_apps_apps;
    }


}
