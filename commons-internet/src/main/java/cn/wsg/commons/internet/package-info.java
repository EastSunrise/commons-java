/**
 * This package provides fundamental interfaces and their skeletal implementations to retrieve data
 * or information from the Internet.
 * <p>
 * A website from the Internet, known as a collection of webpages, is defined as a <strong>site</strong> (implementing
 * {@link cn.wsg.commons.internet.BaseSite}) which is optionally annotated by {@code ConcreteSite}.
 * <p>
 * The data retrieved from the webpages of a website are grouped according to certain rules. Each group of data is
 * treat as a <strong>repository</strong> (implementing {@link cn.wsg.commons.internet.repository.Repository}) which
 * usually contains functions to retrieve and handle data.
 *
 * @author Kingen
 * @see cn.wsg.commons.internet.BaseSite
 * @see cn.wsg.commons.internet.ConcreteSite
 * @see cn.wsg.commons.internet.SiteClient
 * @see cn.wsg.commons.internet.repository.Repository
 */
package cn.wsg.commons.internet;