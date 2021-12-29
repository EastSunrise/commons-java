package cn.wsg.commons.internet.com.imdb;

import cn.wsg.commons.internet.support.NotFoundException;
import cn.wsg.commons.internet.support.OtherResponseException;

/**
 * @author Kingen
 * @see <a href="https://www.imdb.com/">IMDb</a>
 */
public interface ImdbRepository {

    /**
     * Retrieve a title by the given identifier.
     *
     * @param imdbId identifier, starting with 'tt'
     * @return the item with detailed info
     * @throws NullPointerException   if the specified identifier is null
     * @throws NotFoundException      if the item is not found
     * @throws OtherResponseException if an unexpected error occurs when requesting
     */
    ImdbTitle findTitleById(String imdbId) throws NotFoundException, OtherResponseException;

    /**
     * Retrieve a person by the given identifier.
     *
     * @param imdbId identifier, starting with 'nm'
     * @return the person with detailed info
     * @throws NullPointerException   if the specified identifier is null
     * @throws NotFoundException      if the person is not found
     * @throws OtherResponseException if an unexpected error occurs when requesting
     */
    ImdbPerson findPersonById(String imdbId) throws NotFoundException, OtherResponseException;
}
