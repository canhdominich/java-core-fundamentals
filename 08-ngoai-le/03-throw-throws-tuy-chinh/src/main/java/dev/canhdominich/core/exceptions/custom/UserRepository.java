package dev.canhdominich.core.exceptions.custom;

public class UserRepository {

    public String load(String rawId) throws StorageException {
        try {
            int id = Integer.parseInt(rawId);
            if (id > 100) {
                throw new StorageException("no row for id " + id, null);
            }
            return "user-" + id;
        } catch (NumberFormatException e) {
            throw new StorageException("id column is not a number: " + rawId, e);
        }
    }
}
