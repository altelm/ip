package esm;

/**
 * Checked exception for storage-related failures.
 */
public class StorageException extends EsmException {
    public StorageException(String message) {
        super(message);
    }
}
