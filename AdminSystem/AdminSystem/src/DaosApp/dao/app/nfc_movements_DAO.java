package DaosApp.dao.app;

import java.util.List;

import DaosApp.dao.app.dto.nfc_movements;

public interface nfc_movements_DAO {

	List<nfc_movements> getAll();
	public nfc_movements create(nfc_movements mov);
	public void delete(nfc_movements mov);
	public nfc_movements update(nfc_movements mov);
	public nfc_movements get(nfc_movements mov);
}
