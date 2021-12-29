package cn.wsg.commons.internet.com.douban;

/**
 * @author Kingen
 */
class BasicIndex implements DoubanObject {

    private final long id;
    private final DoubanCatalog catalog;
    private final String name;

    BasicIndex(long id, DoubanCatalog catalog, String name) {
        this.id = id;
        this.catalog = catalog;
        this.name = name;
    }

    public long getDoubanId() {
        return id;
    }

    public DoubanCatalog getSubtype() {
        return catalog;
    }

    public String getName() {
        return name;
    }
}
