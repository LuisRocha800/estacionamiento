package DaosApp.dao.app;

import java.util.List;
import DaosApp.dao.app.dto.pagos;

public interface pagos_DAO {

	public List<pagos> getAll();
	public pagos create(pagos pag);
	public void delete(pagos pag);
	public pagos update(pagos pag);
	public pagos get(pagos pag);
}
