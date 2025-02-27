package io.kestra.core.runners;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Service interface for accessing a dedicated working directory.
 * <p>
 * A working directory is a local and temporary directory associated to a task-run context.
 *
 * @see RunContext#workingDir()
 */
public interface WorkingDir {

    /**
     * Gets the working directory path.
     *
     * @return The path.
     */
    Path path();

    /**
     * Gets the working directory path.
     *
     * @param create specifies if the directory must be created if not exists.
     * @return The path.
     */
    Path path(boolean create);

    /**
     * Resolve a path inside the working directory (a.k.a. the tempDir).
     * <p>
     * This method is null-friendly: it will return the working directory (a.k.a. the tempDir) if called with a null path.
     *
     * @return The resolved path or {@code null}.
     * @throws IllegalArgumentException If the resolved path escapes the working directory, this is to protect against path traversal security issue.
     */
    Path resolve(Path path);

    /**
     * Creates a new empty temporary file in the working directory.
     *
     * <p>
     * This method should be equivalent to {@code createTempFile(null)}
     *
     * @return The {@link Path} of created file.
     * @throws IOException if an error happen while creating the file.
     */
    Path createTempFile() throws IOException;

    /**
     * Creates a new empty temporary file in the working directory with the given extension.
     *
     * <p>
     * This method should be equivalent to {@code createTempFile(null, null)}
     *
     * @param extension The file extension - may be {@code null}, in which case ".tmp" is used.
     * @return The {@link Path} of created file.
     * @throws IOException if an error happen while creating the file.
     */
    Path createTempFile(String extension) throws IOException;

    /**
     * Creates a new temporary file in the working directory with the given content.
     *
     * @param content The file content - may be {@code null}.
     * @return The {@link Path} of created file.
     * @throws IOException if an error happen while creating the file.
     */
    Path createTempFile(byte[] content) throws IOException;

    /**
     * Creates a new temporary file in the working directory with the given content and extension.
     *
     * @param content   The file content - may be {@code null}.
     * @param extension The file extension - may be {@code null}, in which case ".tmp" is used.
     * @return The {@link Path} of created file.
     * @throws IOException if an error happen while creating the file.
     */
    Path createTempFile(byte[] content, String extension) throws IOException;

    /**
     * Creates a new empty file in the working directory with the given name.
     *
     * @param filename The file name.
     * @throws IOException              if an error happen while creating the file.
     * @throws IllegalArgumentException if the given filename is {@code null} or empty.
     */
    Path createFile(String filename) throws IOException;

    /**
     * Creates a new empty file in the working directory with the given name and content.
     *
     * @param filename The file name.
     * @param content  The file content - may be {@code null}.
     * @throws IOException              if an error happen while creating the file.
     * @throws IllegalArgumentException if the given filename is {@code null} or empty.
     */
    Path createFile(String filename, byte[] content) throws IOException;

    /**
     * Finds all files  in the working directory that matches one of the given patterns.
     *
     * @param patterns the patterns to match.
     * @return The list of matched files.
     * @throws IOException if an error happen while creating the file.
     */
    List<Path> findAllFilesMatching(final List<String> patterns) throws IOException;

    /**
     * Cleanup the working directory.
     *
     * <p>
     * A call to this method will remove all existing files from the working-directory.
     * After the cleanup, the working directory can continue to be used safely.
     *
     * @throws IOException if an error happen while cleaning the working directory.
     */
    void cleanup() throws IOException;

}
