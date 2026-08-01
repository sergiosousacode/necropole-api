@Service
public class CemiterioService {

    private final CemiterioRepository repository;

    public CemiterioService(CemiterioRepository repository) {
        this.repository = repository;
    }
}