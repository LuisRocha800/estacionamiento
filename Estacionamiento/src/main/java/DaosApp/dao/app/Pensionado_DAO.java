package DaosApp.dao.app;

import java.util.List;

import DaosApp.dao.app.dto.Pensionados;

public interface Pensionado_DAO {

	public List<Pensionados> getAll();
	public Pensionados create(Pensionados pen);
	public void delete(Pensionados pen);
	public Pensionados update(Pensionados pen);
	public Pensionados get(Pensionados pen);
}
