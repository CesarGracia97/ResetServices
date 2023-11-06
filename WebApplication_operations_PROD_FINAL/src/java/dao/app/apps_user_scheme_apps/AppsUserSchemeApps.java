/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.app.apps_user_scheme_apps;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jyacelga
 */
@Entity
@Table(name = "apps_user_scheme_apps", catalog = "aprovgrupotv", uniqueConstraints = {})
public class AppsUserSchemeApps implements Serializable {

    // Fields
    private Integer id;
    private String id_apps_scheme_apps;
    private String id_apps_user;

    public AppsUserSchemeApps() {
    }

    public AppsUserSchemeApps(Integer id,
            String id_apps_scheme_apps,
            String id_apps_user) {
        this.id = id;
        this.id_apps_scheme_apps = id_apps_scheme_apps;
        this.id_apps_user = id_apps_user;

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

    @Column(name = "id_apps_scheme_apps", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    public String getId_apps_scheme_apps() {
        return id_apps_scheme_apps;
    }

    public void setId_apps_scheme_apps(String id_apps_scheme_apps) {
        this.id_apps_scheme_apps = id_apps_scheme_apps;
    }

    @Column(name = "id_apps_user", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    public String getId_apps_user() {
        return id_apps_user;
    }

    public void setId_apps_user(String id_apps_user) {
        this.id_apps_user = id_apps_user;
    }

}
