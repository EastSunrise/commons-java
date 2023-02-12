package cn.kingen.commons.internet;

/**
 * Indicates the site is loggable.
 *
 * @param <T> type of the users
 * @author Kingen
 */
public interface Loggable<T> {

    /**
     * Returns current logged-in user.
     *
     * @return current logged-in user, or {@literal null} if not logged in
     */
    T user();
}
