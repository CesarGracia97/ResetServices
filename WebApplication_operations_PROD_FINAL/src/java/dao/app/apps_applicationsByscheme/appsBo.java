package dao.app.apps_applicationsByscheme;

import ent.querys.Querys;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nachxs
 */
public class appsBo {

    public ArrayList getAplicaciones(String id_user, String id_esque) {
        List lst_apps = new ArrayList();
        List lst_desc = new ArrayList();
        try {
            Querys all_apps = new Querys();
            // Querys lista = new Querys();
            lst_apps = all_apps.findUsersAppsScheme(id_user, id_esque);
            for (int y = 0; y < lst_apps.size(); y++) {
                String[] only1 = lst_apps.get(y).toString().split(",");
                System.out.println("Esquema "+only1[3]+"--"+only1[4].trim().replaceAll("[^\\w\\s]",""));
                lst_desc.add(only1[4].replaceAll("[^\\w\\s]","").trim());
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return (ArrayList) lst_desc;
    }
}
