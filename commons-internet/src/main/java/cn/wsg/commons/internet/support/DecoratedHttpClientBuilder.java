package cn.wsg.commons.internet.support;

import org.apache.http.impl.client.cache.CachingHttpClientBuilder;
import org.apache.http.impl.execchain.ClientExecChain;

import java.util.function.Function;

/**
 * The builder for {@link org.apache.http.impl.client.CloseableHttpClient} instances capable of
 * decorating the main executor.
 *
 * @author Kingen
 */
public class DecoratedHttpClientBuilder extends CachingHttpClientBuilder {

    private Function<ClientExecChain, ClientExecChain> decorator;

    protected DecoratedHttpClientBuilder() {
    }

    public static DecoratedHttpClientBuilder create() {
        return new DecoratedHttpClientBuilder();
    }

    public DecoratedHttpClientBuilder setDecorator(Function<ClientExecChain, ClientExecChain> decorator) {
        this.decorator = decorator;
        return this;
    }

    @Override
    protected ClientExecChain decorateMainExec(ClientExecChain mainExec) {
        if (decorator != null) {
            ClientExecChain decoratedExec = decorator.apply(mainExec);
            if (decoratedExec != null) {
                return super.decorateMainExec(decoratedExec);
            }
        }
        return super.decorateMainExec(mainExec);
    }
}
