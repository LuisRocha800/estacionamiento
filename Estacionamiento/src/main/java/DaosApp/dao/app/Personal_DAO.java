package DaosApp.dao.app;

import java.util.List;

import DaosApp.dao.app.dto.Personal;

public interface Personal_DAO {
	
	List<Personal> getAll();
	public Personal create(Personal per);
	public void delete(Personal per);
	public Personal update(Personal per);
	public Personal get(Personal per);

}
