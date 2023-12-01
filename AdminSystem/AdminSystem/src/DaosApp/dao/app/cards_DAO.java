package DaosApp.dao.app;

import java.util.List;
import DaosApp.dao.app.dto.cards;

public interface cards_DAO {

	public List<cards> getAll();
	public cards create(cards crds);
	public void delete(cards crds);
	public cards update(cards crds);
	public cards get(cards crds);
	
}
