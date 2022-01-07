package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.Source;

/**
 * A word, name, acronym, phrase, etc. with a formal definition. Often used in the context of category or subject classification, glossaries or dictionaries,
 * product or creative work types, etc. Use the name property for the term being defined, use termCode if the term has an alpha-numeric code allocated, use
 * description to provide the definition of the term.
 *
 * @author Kingen
 */
@Source("https://github.com/schemaorg/schemaorg/issues/894")
public interface DefinedTerm extends Intangible {
}
