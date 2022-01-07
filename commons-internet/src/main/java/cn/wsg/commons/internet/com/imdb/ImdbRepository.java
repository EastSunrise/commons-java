package cn.wsg.commons.internet.com.imdb;

import cn.wsg.commons.internet.common.ReleaseInfo;
import cn.wsg.commons.internet.support.NotFoundException;
import cn.wsg.commons.internet.support.OtherResponseException;

import java.util.List;

/**
 * @author Kingen
 * @see <a href="https://www.imdb.com/">IMDb</a>
 * @see <a href="https://developer.imdb.com/documentation">Documentation & Data Dictionary</a>
 */
public interface ImdbRepository {

    /**
     * Retrieves the top 250 movies.
     *
     * @return the ids of the top 250 movies in rank
     * @throws OtherResponseException if an unexpected error occurs when requesting
     */
    List<String> top250() throws OtherResponseException;

    /**
     * Retrieves the top 250 TV shows.
     *
     * @return the ids of the top 250 TV shows in rank
     * @throws OtherResponseException if an unexpected error occurs when requesting
     */
    List<String> top250TV() throws OtherResponseException;

    /**
     * Retrieves the most popular movies.
     *
     * @return the ids of the most popular movies in rank
     * @throws OtherResponseException if an unexpected error occurs when requesting
     */
    List<String> mostPopular() throws OtherResponseException;

    /**
     * Retrieve a title by the given identifier.
     *
     * @param titleId identifier, starting with 'tt'
     * @return the title
     * @throws NullPointerException     if the specified identifier is null
     * @throws IllegalArgumentException if the specified identifier is not valid
     * @throws NotFoundException        if the item is not found
     * @throws OtherResponseException   if an unexpected error occurs when requesting
     */
    ImdbCreativeWork findTitleById(String titleId) throws NotFoundException, OtherResponseException;

    /**
     * Retrieves the release information the target video.
     *
     * @param titleId identifier, starting with 'tt'
     * @return the release information
     * @throws NullPointerException     if the specified identifier is null
     * @throws IllegalArgumentException if the specified identifier is not valid
     * @throws NotFoundException        if the item is not found
     * @throws OtherResponseException   if an unexpected error occurs when requesting
     */
    ReleaseInfo findReleaseInfo(String titleId) throws NotFoundException, OtherResponseException;

    /**
     * Retrieves all ids of episodes of the specified TV season of the TV series.
     *
     * @param seriesTitleId the id of the TV series
     * @param season        the target season
     * @return all ids of episodes of the season, or an empty array if no episodes are found. The index of episode i is [i] and any episode could be null if
     * not found.
     * @throws NotFoundException      if the series or the season is not found
     * @throws OtherResponseException if an unexpected error occurs when requesting
     */
    String[] findEpisodesOfSeries(String seriesTitleId, int season) throws NotFoundException, OtherResponseException;

    /**
     * Retrieve a person by the given identifier.
     *
     * @param nameId identifier, starting with 'nm'
     * @return the person with detailed info
     * @throws NullPointerException   if the specified identifier is null
     * @throws NotFoundException      if the person is not found
     * @throws OtherResponseException if an unexpected error occurs when requesting
     */
    ImdbPerson findPersonById(String nameId) throws NotFoundException, OtherResponseException;
}
