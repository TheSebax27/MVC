
package Model;


public class Tab {
    int id,idAprendiz;
    String Tabnum,Tabname,Site,City;
    
    public Tab(){
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAprendiz() {
        return idAprendiz;
    }

    public void setIdAprendiz(int idAprendiz) {
        this.idAprendiz = idAprendiz;
    }

    public String getTabnum() {
        return Tabnum;
    }

    public void setTabnum(String Tabnum) {
        this.Tabnum = Tabnum;
    }

    public String getTabname() {
        return Tabname;
    }

    public void setTabname(String Tabname) {
        this.Tabname = Tabname;
    }

    public String getSite() {
        return Site;
    }

    public void setSite(String Site) {
        this.Site = Site;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }
   
}
