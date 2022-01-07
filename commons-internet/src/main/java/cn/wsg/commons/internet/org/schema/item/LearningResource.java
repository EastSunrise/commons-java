package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.Source;

/**
 * The LearningResource type can be used to indicate [[CreativeWork]]s (whether physical or digital) that have a particular and explicit orientation towards
 * learning, education, skill acquisition, and other educational purposes.
 * <p>
 * [[LearningResource]] is expected to be used as an addition to a primary type such as [[Book]], [[Video]], [[Product]] etc.
 * <p>
 * [[EducationEvent]] serves a similar purpose for event-like things (e.g. a [[Trip]]). A [[LearningResource]] may be created as a result of an
 * [[EducationEvent]], for example by recording one.
 *
 * @author Kingen
 */
@Source("https://github.com/schemaorg/schemaorg/issues/1401")
public interface LearningResource extends CreativeWork {
}
