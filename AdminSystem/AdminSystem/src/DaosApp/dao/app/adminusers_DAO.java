package DaosApp.dao.app;
import DaosApp.dao.app.dto.adminusers;
import java.util.List;

public interface adminusers_DAO {
    
    List<adminusers> getAll();
    public adminusers create(adminusers adm);
    public void delete(adminusers adm);
    public adminusers update(adminusers adm);
    public adminusers get(adminusers adm);
    
}
