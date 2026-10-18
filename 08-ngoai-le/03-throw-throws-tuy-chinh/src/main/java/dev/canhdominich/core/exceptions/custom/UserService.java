package dev.canhdominich.core.exceptions.custom;

public class UserService {

    private final UserRepository repository = new UserRepository();

    public String describe(String rawId) {
        try {
            return "found " + repository.load(rawId);
        } catch (StorageException e) {
            throw new ServiceException("cannot describe user " + rawId, e);
        }
    }
}
