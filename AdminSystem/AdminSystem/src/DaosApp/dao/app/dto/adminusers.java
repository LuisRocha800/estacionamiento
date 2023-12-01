package DaosApp.dao.app.dto;

public class adminusers {
    
    private String usuario;
    private String password;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "adminusers{" + "usuario=" + usuario + ", password=" + password + '}';
    }
    
}
