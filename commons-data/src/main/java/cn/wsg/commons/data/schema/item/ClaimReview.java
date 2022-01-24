package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A fact-checking review of claims made (or reported) in some creative work (referenced via itemReviewed).
 *
 * @author Kingen
 */
@Source({"https://github.com/schemaorg/schemaorg/issues/1061"})
public interface ClaimReview extends Review {
}